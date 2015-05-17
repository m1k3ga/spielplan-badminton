package org.m1k3ga.badminton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.spielplan.metrics.Metrics;

import java.util.Comparator;

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

  private final Metrics metrics;


  public Player(String name) {
    this.metrics  = new Metrics();
    this.playerName = name;
    // TODO This is only as long as there is no persistance
    this.id = ++counter;
  }

  /**
   * Initialize a player with predefined (preloaded) metrics (e.g. from db)
   *
   * @param name
   * @param metrics
   */
  public Player(String name, Metrics metrics) {
    this.metrics = metrics;
    this.playerName = name;
    // TODO This is only as long as there is no persistance
    this.id = ++counter;
  }

  /**
   * This method is necessary for tests unless we can the metrics mock
   * @return
   */
  public Metrics getMetrics() {
    return metrics;
  }

  public void gamePlayed() {
    metrics.gamePlayed();
    log.info("Player '"+playerName+"' games played: "+metrics.getGamesPlayedToday());
  }

  public int getGamesPlayedToday() {
    return metrics.getGamesPlayedToday();
  }

  /**
   * Get the current pickPoints from the Metrics
   * @return
   */
  public int getPickPoints() {
    return metrics.getPickPoints();
  }

  public void addPickPoints(int points) {
    metrics.addPickPoints(points);
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

//  @Override
//  public int compare(Player player1, Player player2) {
//    int player1Played = player1.metrics.getGamesPlayedToday();
//    int player2Played = player2.metrics.getGamesPlayedToday();
//
//    if (player1Played < player2Played) {
//      return -1;
//    } else if (player1Played == player2Played) {
//      return 0;
//    }
//
//    return 1;
//  }

  @Override
  public int compareTo(Player player) {
    int player1Played = this.metrics.getGamesPlayedToday();
    int player2Played = player.metrics.getGamesPlayedToday();

    if (player1Played < player2Played) {
      return -1;
    } else if (player1Played == player2Played) {
      return 0;
    }

    return 1;
  }
}