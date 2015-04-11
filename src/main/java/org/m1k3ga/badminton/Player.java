package org.m1k3ga.badminton;

import org.m1k3ga.badminton.spielplan.metrics.Metrics;

import java.util.HashMap;
import java.util.Map;

/**
 * A single player with statistics (metrics)
 *   - games played today
 *   - metrics object
 */
public class Player {

	private final String playerName;

	private final Metrics metrics = new Metrics();

	
	
	public Player(String name) {
		this.playerName = name;

	}

	public String getPlayerName() {
		return playerName;
	}
	
	public int getGamesPlayedToday() {
		return metrics.getGamesPlayedToday();
	}

}