package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for calculating team pairings
 * Here we initialize a tournament day with the list of players for that day
 * Then the teams and the game pairings are calculated
 *
 * @author m1k3ga
 */
public class TournamentDay {

  private static final Logger log = LogManager.getLogger(Team.class);

  private List<Player> playersForToday = new ArrayList<>();

  private List<GamePairing> gamesPlayedToday = new ArrayList<>();

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
      log.info("Added player '"+player.getPlayerName()+"'");
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
    // TODO add the game to the metrics of the players and the teams
    Team teamA = game.getTeamA();
//    Team teamB = game.getTeamB();
    Player player = teamA.getPlayer_1();
    player.gamePlayed();
  }

  public Player getPlayer(int index){
    // TODO check for valid values
    return playersForToday.get(index);
  }

  public int getNumberOfPlayersForToday() {
    return playersForToday.size();
  }

  public int getNumberOfGamesPlayedToday() {
    return gamesPlayedToday.size();
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

}
