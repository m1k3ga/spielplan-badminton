package org.m1k3ga.badminton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.spielplan.metrics.Metrics;

import java.util.HashMap;
import java.util.Map;

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
    this.id = ++counter;
  }

  public void gamePlayed() {
    metrics.gamePlayed();
    log.info("Player '"+playerName+"' games played: "+metrics.getGamesPlayedToday());
  }

  public int getGamesPlayedToday() {
    return metrics.getGamesPlayedToday();
  }


  public String getPlayerName() {
    return playerName;
  }

  public boolean isEqual(Player player) {
    if (  player.getPlayerName().equalsIgnoreCase(playerName) ) {
      return true;
    }

    return false;
  }
}