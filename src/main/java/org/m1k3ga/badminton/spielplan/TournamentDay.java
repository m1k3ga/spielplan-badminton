package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The central entry for a day of badminton
 * We govern the metrics for the games, players and teams.
 * All information are gathered here
 *
 * Here we initialize a tournament day with the list of players for that day
 * Then the teams and the game pairings are calculated
 *
 * Usage:
 *   - add players for a day
 *   - play game(s)
 *   - getting a new game pairing is outside the scope of this class
 * @author m1k3ga
 */
public class TournamentDay {
  private static final Logger log = LogManager.getLogger(TournamentDay.class);

  public static final int PICK_POINT_WEIGHT_FOR_NUMBER_OF_GAMES = 10;

  private final List<Player> playersForToday = new ArrayList<>();
  private final List<Game> gamesToday = new ArrayList<>();

  private final TournamentDayEvaluation eval = new TournamentDayEvaluation();

  /**
   * Add a player for this tournament day
   * It will be checked for double entries
   *
   * @param player
   */
  public void addPlayer(Player player) {
    if ( isValidPlayerForToday(player) == false ) {
      log.warn("Player already added. Ignoring");
    } else {
      playersForToday.add(player);
      log.info("Added player '"+player.getName()+"'");
    }
  }


  public void calculatePickPointsForNumberOfGamesForEachPlayer() {
    Player player;
    int addPoints = 0;

    for ( int i=0;i<playersForToday.size();i++) {
      player = getPlayer(i);
      addPoints = (getNumberOfGamesPlayedToday()-player.getGamesPlayedToday()) * PICK_POINT_WEIGHT_FOR_NUMBER_OF_GAMES;
      log.info("Player '"+player.getName()+"' gets '"+addPoints+"' pick points for the next game");
      player.addPickPoints(addPoints);
    }
  }


  public int getNumberOfPlayers() {
    return playersForToday.size();
  }

  /**
   * When a game is finished,
   * the result needs to be included
   * in the metrics of the players and the teams
   *
   * @param game
   */
  public void gamePlayed(Game game) {
    log.info("game played");

    gamesToday.add(game);

    Team teamA = game.getTeamA();

    // Add the team to the TeamPairingMatrix
    eval.gamePlayed(teamA);

    // Increment counter for games played today/season
    teamA.getPlayer_1().gamePlayed();
    teamA.getPlayer_2().gamePlayed();


    Team teamB = game.getTeamB();

    // Add the team to the TeamPairingMatrix
    eval.gamePlayed(teamB);

    // Increment counter for games played today/season
    teamB.getPlayer_1().gamePlayed();
    teamB.getPlayer_2().gamePlayed();
  }

  /**
   * Get a player from the enlisted ones of today
   *
   * @param index
   * @return
   */
  public Player getPlayer(int index){
    if (index < 0 || index >= playersForToday.size()) {
      return null;
    }

    return playersForToday.get(index);
  }

  public Game getLastGame(){
    int gamesTodayPlayed = gamesToday.size();
    if (gamesTodayPlayed > 0) {
      return gamesToday.get(gamesToday.size() - 1);
    }

    return null;
  }

  public int getNumberOfPlayersForToday() {
    return playersForToday.size();
  }

  public int getNumberOfGamesPlayedToday() {
    return gamesToday.size();
  }

  /**
   * At least four players should participate in a tournament day
   *
   * @return
   */
  public boolean isValidTournamentDay() {
    if ( playersForToday.size() < 4 ) {
      return false;
    }

    return true;
  }

  /**
   * Integrity check for a new player
   * Is the player already enlisted?
   *
   * @param player
   * @return
   */
  private boolean isValidPlayerForToday(Player player) {
    if (playersForToday.isEmpty()) {
      return true;
    }

    if (playersForToday.contains(player)) {
      return false;
    }

    return true;
  }

  public List<Player> getPlayers() {
    return playersForToday;
  }
}
