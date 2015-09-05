package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;
import org.m1k3ga.badminton.util.KeyHandling;

import java.util.*;

/**
 * Create a new game pairing by rules of
 * - Favor players with the least played games
 *   (Each player should stay in intermission as little as possible)
 * - Favor teams with the least played games
 * <p>
 *
 * To calculate a new game the class needs the context :
 * From the tournament day: All played games and all players for that day
 *
 * First, we build up a list of all game pairings
 * Pick a team based on the pick points
 * From the remaining teams pick the ones with the best matched selection criteria
 *
 * Usage:
 * <ul>
 *   <li>Create a tournament day</li>
 *   <li>add players</li>
 *   <li>calculate game with getNewGamePairing()</li>
 * </ul>
 * <p>
 * Created by m1k3ga on 11.04.15.
 */
public class GamePairingCalculator {

  private static final Logger log = LogManager.getLogger(GamePairingCalculator.class);

  public static final int PICK_POINT_FOR_NUMBER_OF_GAMES_FOR_A_PLAYER = 10;


  private List<Player> listOfPlayers;
  private int numberOfGamesPlayedToday;

  /** Teams to pick from for a game pairing (teamA, teamB) */
  private Map<String, Team> teamsToPickFrom;


  public GamePairingCalculator(List<Player> players, int count) {
    log.info("GamePairingCalculator()");
    this.listOfPlayers = players;
    this.numberOfGamesPlayedToday = count;
  }



  /**
   * Main function for evaluating a new game pairings
   *
   * The two teams are picked by separate methods
   * - pickTeamA
   * - pickTeamB
   *
   * @return a valid game pairing
   */
  public GamePairing getNewGamePairing() {
    log.info("getNewGamePairing(): (with a new pairing list)");

    calculateAllTeamPairings();

    calculatePickPointsForEachPlayer();
    calculatePickPointsForEachTeam();

    // For picking a team the balanced list must be available
    GamePairing gamePairing = null;
    Team teamA = pickTeamA();
    calculateRemainingPossibleTeams(teamA);
    Team teamB = pickTeamB();

    try {
      gamePairing = new GamePairing(teamA, teamB);
    } catch (GameException e) {
      log.error("Team A:" + teamA.toString() + " - Team B: " + teamB.toString());
      e.printStackTrace();
    }

    return gamePairing;
  }


  /**
   * For a given list of players (along with their id's)
   * calculate all possible game pairings
   * with respect to:
   * game pairing (id1,id2) == game pairing (id2, id1)
   */
  private void calculateAllTeamPairings() {
    log.info("Calculate all team pairings");
    teamsToPickFrom = new HashMap<>();
    Player player1, player2;
    Team team;

    for (int i = 0; i < listOfPlayers.size(); i++) {
      player1 = listOfPlayers.get(i);

      for (int j = i + 1; j < listOfPlayers.size(); j++) {
        player2 = listOfPlayers.get(j);
        team = new Team(player1, player2);
        String key = KeyHandling.getKey(player1.getId(), player2.getId());

        teamsToPickFrom.put(key, team);
      }
    }
    log.info(this.toString());
  }


  /**
   * Each player gets pick points based on the number of games he/she has played today
   * The least played games get the highest pick points.
   */
  private void calculatePickPointsForEachPlayer() {
    for( Player pl : listOfPlayers) {
      pl.resetPickPoints();

      int points = (numberOfGamesPlayedToday - pl.getGamesPlayedToday()) * PICK_POINT_FOR_NUMBER_OF_GAMES_FOR_A_PLAYER;
      log.info("Player '" + pl.getName() + "' gets '" + points + "' pick points.");
      pl.addPickPoints(points);
    }

  }


  /**
   * Each team gets pick points based on the number of games the players of the team have played together
   * The least played games get the highest pick points.
   */
  private void calculatePickPointsForEachTeam() {
    Set<String> teamKeys = teamsToPickFrom.keySet();
    for(String key : teamKeys) {
      Team t = teamsToPickFrom.get(key);
      t.setPickPoints(0);
      int pickPointsPlayer1 = t.getPlayer_1().getPickPoints();
      int pickPointsPlayer2 = t.getPlayer_2().getPickPoints();
      t.setPickPoints(pickPointsPlayer1 + pickPointsPlayer2);
    }
    log.info("calculatePickPointsForEachTeam() - done.");
  }



  /**
   * @return
   */
  private Team pickTeamA() {
    log.info("Pick team A");

    // Pick the two players with the highest pickPoints
    Collections.sort(listOfPlayers);

    // TODO get the second player by a team with the first player with highest pick points

    Player player1 = listOfPlayers.get(0);
    log.info("Team A, Player 1: '"+player1.getName()+"'");
    Player player2 = listOfPlayers.get(1);
    log.info("Team A, Player 2: '"+player2.getName()+"'");

    Team team = new Team(player1, player2);
    log.info("Team A: '" + team.toString() + "'");

    return team;
  }


  private Team pickTeamB() {
    log.info("Pick team B");

    // Pick the team with the highest pickPoints
    Team pickedTeam = null;
    final Set<String> keySet = teamsToPickFrom.keySet();

    for (String key : keySet){
      Team t = teamsToPickFrom.get(key);

      if (pickedTeam == null || t.getPickPoints() > pickedTeam.getPickPoints()) {
        pickedTeam = t;
      }
    }
    log.info("Picked team B: " + pickedTeam.toString() + " with pickPoints: " + pickedTeam.getPickPoints());

    return pickedTeam;
  }


  /**
   * When a team is picked,
   * remove the following teams from further choosing:
   * If a player could be picked for the second team as well,
   * the team will be disposed
   *
   * @param team An already picked team
   */
  private void calculateRemainingPossibleTeams(Team team) {
    log.info("calculateRemainingPossibleTeams()");
    final Map<String,Team> remainingPossibleTeams = new HashMap<>();
    final Player playerA1 = team.getPlayer_1();
    final Player playerA2 = team.getPlayer_2();

    Player playerB1, playerB2;
    Team teamToCheck;
    final Set<String> teamKeys = teamsToPickFrom.keySet();

    for (String key : teamKeys) {
      teamToCheck = teamsToPickFrom.get(key);
      playerB1 = teamToCheck.getPlayer_1();
      playerB2 = teamToCheck.getPlayer_2();

      if (!(playerA1.isEqual(playerB1) || playerA1.isEqual(playerB2) || playerA2.isEqual(playerB1) || playerA2.isEqual(playerB2))) {
        log.debug("Adding valid team: (" + playerB1.getName() + " / " + playerB2.getName() + ")");
        String keyToAdd = KeyHandling.getKey(playerB1.getId(),playerB2.getId());
        remainingPossibleTeams.put(keyToAdd,teamToCheck);
      }
    }

    teamsToPickFrom = remainingPossibleTeams;
    log.info(this.toString());
  }


  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    Set<String> keySet = teamsToPickFrom.keySet();

    sb.append("\n#  -  -  -  All calculated teams  -  -  -\n");
    for (String key : keySet) {
      Team team = teamsToPickFrom.get(key);
      sb.append("(" + team.getPlayer_1().getName() + "/" + team.getPlayer_2().getName() + ")\n");
    }

    sb.append((teamsToPickFrom.size() == 0) ? "No teams left\n" : "");
    sb.append("#  -  -  -  -  -  -  -  -  -  -  -  -\n");

    return (sb.toString());
  }


}
