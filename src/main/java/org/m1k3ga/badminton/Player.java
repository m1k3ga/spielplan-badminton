package org.m1k3ga.badminton;

import org.m1k3ga.badminton.spielplan.metrics.Metrics;

public class Player {

	private final PlayerName name;
	
	private final Metrics metrics = new Metrics();

	
	
	public Player(PlayerName name) {
		this.name = name;
	}

	public PlayerName getPlayerName() {
		return name;
	}
	
	public int getGamesPlayedToday() {
		return metrics.getGamesPlayedToday();
	}

}