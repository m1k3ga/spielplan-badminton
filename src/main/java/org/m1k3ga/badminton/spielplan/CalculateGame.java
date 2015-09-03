package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;
import org.m1k3ga.badminton.util.KeyHandling;

import java.util.*;

/**
 * Create a new game pairing by rules of
 * - Each player should have played nearly the same number of games
 * - Each player should stay in intermission as little as possible
 * - Team pairings with the lowest number of games should be preferred
 * <p>
 * Usage:
 * - Create a tournament day
 * - add players
 * - calculate game with getNewGamePairing()
 * <p>
 * Created by m1k3ga on 11.04.15.
 */
public class CalculateGame {
  private static final Logger log = LogManager.getLogger(CalculateGame.class);

  private TournamentDay td;

  private Map<String, Team> possibleTeams;


  public CalculateGame(TournamentDay td) {
    log.info("Calculate new game");
    this.td = td;
  }



  /**
   * Main function for evaluating a new game pairings
   * The two teams are picked by separate methods
   * - pickTeamA
   * - pickTeamB
   *
   * @return a valid game pairing
   */
  public GamePairing getNewGamePairing() {
    log.info("New game pairing. New pairing list");
    calculateAllTeamPairings();
    GamePairing gamePairing = null;
    Team teamA = pickTeamA();
    Team teamB = pickTeamB();

    try {
      gamePairing = new GamePairing(teamA, teamB);
    } catch (GameException e) {
      e.printStackTrace();
    }

    return gamePairing;
  }



  private void calculateAllTeamPairings() {
    log.info("Calculate all team pairings");
    possibleTeams = new HashMap<>();
    Player player1, player2;
    Team team;

    for (int i = 0; i < td.getNumberOfPlayers(); i++) {
      player1 = td.getPlayer(i);

      for (int j = i + 1; j < td.getNumberOfPlayers(); j++) {
        player2 = td.getPlayer(j);
        team = new Team(player1, player2);
        String key = KeyHandling.getKey(player1.getId(), player2.getId());

        possibleTeams.put(key,team);
      }
    }
    printAllTeams();
  }


  /**
   * @return
   */
  private Team pickTeamA() {
    log.info("Pick team A");

    td.calculatePickPointsForNumberOfGamesForEachPlayer();
    // Pick the first two players with the highes pickPoints
    List<Player> players = td.getPlayersForToday();
    Collections.sort(players);

    Player player1 = players.get(0);
    log.info("Player 1: '"+player1.getName()+"'");
    Player player2 = players.get(1);
    log.info("Player 2: '"+player2.getName()+"'");
    Team team = new Team(player1,player2);
    log.info("Picked team A: " + team.toString());
    cleanUpRemainingPairings(team);

   return team;
  }


  private Team pickTeamB() {
    log.info("Pick team B");
    final Set<String> keySet = possibleTeams.keySet();
    // TODO Get not just the first in the list
    final String key = keySet.iterator().next();

    Team team = possibleTeams.get(key);
    log.info("Picked team B: " + team.toString());

    return team;
  }


  /**
   * When a team is picked,
   * remove the impossible teams for the game :
   * If a player could be picked for the second team as well,
   * the team will be disposed
   *
   * @param team A already picked team
   */
  private void cleanUpRemainingPairings(Team team) {
    log.info("Cleaning up...");
    final Map<String,Team> remainingPossibleTeams = new HashMap<>();
    final Player playerA1 = team.getPlayer_1();
    final Player playerA2 = team.getPlayer_2();

    Player playerB1, playerB2;
    Team teamToCheck;
    final Set<String> teamKeys = possibleTeams.keySet();

    for (String key : teamKeys) {
      teamToCheck = possibleTeams.get(key);
      playerB1 = teamToCheck.getPlayer_1();
      playerB2 = teamToCheck.getPlayer_2();

      if (!(playerA1.isEqual(playerB1) || playerA1.isEqual(playerB2) || playerA2.isEqual(playerB1) || playerA2.isEqual(playerB2))) {
        log.debug("Adding valid team: (" + playerB1.getName() + " + " + playerB2.getName());
        String keyToAdd = KeyHandling.getKey(playerB1.getId(),playerB2.getId());
        remainingPossibleTeams.put(keyToAdd,teamToCheck);
      }
    }

    possibleTeams = remainingPossibleTeams;
    printAllTeams();
  }


  public void printAllTeams() {
    StringBuffer sb = new StringBuffer();
    Set<String> keySet = possibleTeams.keySet();

    sb.append("#  -  -  -  All remaining teams  -  -  -");
    for (String key : keySet) {
      Team team = possibleTeams.get(key);
      sb.append("(" + team.getPlayer_1().getName() + "/" + team.getPlayer_2().getName() + "),");
    }

    String logEvent = (possibleTeams.size() == 0) ? "Keine Teams übring" : "All Teams: " + sb.toString();
    log.info(logEvent);
  }


}
