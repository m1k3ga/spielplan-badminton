package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create a new game pairing by rules of
 *   - Each player should have played nearly the same number of games
 *   - Each player should stay in intermission as little as possible
 *   - Team pairings with the lowest number of games should be preferred
 *
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

  private List<Team> possibleTeams;

  public CalculateGame(TournamentDay td) {
    log.info("Calculate new game");
    this.td = td;
  }

  /**
   * Main function for evaluating a new game pairings
   * The two teams are picked by separate methods
   *   - pickTeamA
   *   - pickTeamB
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
    possibleTeams = new ArrayList<>();
    Player player1, player2;
    Team team;
    for (int i = 0; i < td.getNumberOfPlayers(); i++) {
      player1 = td.getPlayer(i);
      for (int j = i + 1; j < td.getNumberOfPlayers(); j++) {
        player2 = td.getPlayer(j);
        team = new Team(player1, player2);
        possibleTeams.add(team);
      }
    }
    printAllTeams();
  }



  public void printAllTeams() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < possibleTeams.size(); i++) {
      Team team = possibleTeams.get(i);
      sb.append("(" + team.getPlayer_1().getPlayerName() + "/" + team.getPlayer_2().getPlayerName() + "),");
    }

    String logEvent = (possibleTeams.size() == 0) ? "Keine Teams übring" : "All Teams: " + sb.toString();
    log.info(logEvent);
  }


  /**
   * The business logic applied here is
   *
   * @return
   */
  private Team pickTeamA() {
    log.info("Pick team A");
    Random random = new Random();
    int teamNumber = random.nextInt(possibleTeams.size());
    Team team = possibleTeams.get(teamNumber);
    log.info( "Picked team A: " + team.toString());
    possibleTeams.remove(teamNumber);
    cleanUpRemainingPairings(team);

    return team;
  }


  private Team pickTeamB() {
    log.info("Pick team B");
    Random random = new Random();
    int teamNumber = random.nextInt(possibleTeams.size());
    Team team = possibleTeams.get(teamNumber);
    log.info("Picked team B: " + team.toString());
    possibleTeams.remove(teamNumber);

    // TODO Is the second cleanup necessary?
    cleanUpRemainingPairings(team);

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
    List<Team> remainingPossibleTeams = new ArrayList<>();
    Player playerA1 = team.getPlayer_1();
    Player playerA2 = team.getPlayer_2();

    Player playerB1, playerB2;
    Team teamToCheck;

    for (int i = 0; i < possibleTeams.size(); i++) {
      teamToCheck = possibleTeams.get(i);
      playerB1 = teamToCheck.getPlayer_1();
      playerB2 = teamToCheck.getPlayer_2();

      if (!(playerA1.isEqual(playerB1) || playerA1.isEqual(playerB2) || playerA2.isEqual(playerB1) || playerA2.isEqual(playerB2))) {
        log.debug("Adding valid team: ("+playerB1+" + "+playerB2);
        remainingPossibleTeams.add(teamToCheck);
      }
    }

    possibleTeams = remainingPossibleTeams;
    printAllTeams();
  }

}
