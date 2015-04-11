package org.m1k3ga.badminton;

import org.m1k3ga.badminton.spielplan.metrics.Metrics;

import java.util.HashMap;
import java.util.Map;

/**
 * A single player with statistics (metrics)
 * - games played today
 * - metrics object
 */
public class Player {

  private static int counter = 0;
  private final int id;

  private final String playerName;

  private final Metrics metrics = new Metrics();


  public Player(String name) {
    this.playerName = name;
    this.id = ++counter;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getPlayerId() {
    return id;
  }

  public int getGamesPlayedToday() {
    return metrics.getGamesPlayedToday();
  }

}