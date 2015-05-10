package org.m1k3ga.badminton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.spielplan.metrics.Metrics;

/**
 * A single player with statistics (metrics)
 * - games played today
 * - metrics object
 */
public class Player {
  private static final Logger log = LogManager.getLogger(Player.class);

  private static int counter = 0;
  private final int id;

  private final String playerName;

  private final Metrics metrics = new Metrics();


  public Player(String name) {
    this.playerName = name;
    // TODO This is only as long as there is no persistance
    this.id = ++counter;
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
    if (  player.getName().equalsIgnoreCase(playerName) ) {
      return true;
    }

    return false;
  }

  public int getId() {
    return id;
  }
}