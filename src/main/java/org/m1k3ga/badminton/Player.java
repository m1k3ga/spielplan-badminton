package org.m1k3ga.badminton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A single player with statistics (metrics)
 * - games played today
 * - metrics object
 */
public class Player implements Comparable<Player>{
  private static final Logger log = LogManager.getLogger(Player.class);

  private static int counter = 0;
  private final int id;

  private final String playerName;

  /** How many games has the player played today */
  private int gamesPlayedToday = 0;

  /** This value is used to pick a player based on the number of games played */
  private int pickPoints = 0;


  public Player(String name) {
    this.playerName = name;
    // TODO This is only as long as there is no persistance
    this.id = ++counter;
  }


  public void gamePlayed() {
    gamesPlayedToday++;
    log.info("Player '" + playerName + "' games played: " + gamesPlayedToday);
  }

  public int getGamesPlayedToday() {
    return gamesPlayedToday;
  }

  public int getPickPoints() {
    return pickPoints;
  }

  public void resetPickPoints() {
    pickPoints = 0;
  }

  public void addPickPoints(int points) {
    pickPoints += points;
  }

  public String getName() {
    return playerName;
  }

  public boolean isEqual(Player player) {
    // FIXME Change this to id of the database when we use persistence
    if (  player.getName().equalsIgnoreCase(playerName) ) {
      return true;
    }

    return false;
  }

  public int getId() {
    return id;
  }



  @Override
  public int compareTo(Player player) {
    int playedGamesPlayer1 = gamesPlayedToday;
    int playedGamesPlayer2 = player.getGamesPlayedToday();

    if (playedGamesPlayer1 < playedGamesPlayer2) {
      return -1;
    } else if (playedGamesPlayer1 == playedGamesPlayer2) {
      return 0;
    }

    return 1;
  }
}