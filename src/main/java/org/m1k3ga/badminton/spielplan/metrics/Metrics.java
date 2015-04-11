package org.m1k3ga.badminton.spielplan.metrics;

/**
 * Metrics for a player
 *   - number of games played on a day
 *   - number of days the player played games
 */
public class Metrics {

	private int gamesPlayedSeason = 0;
	private int daysPlayedSeason = 0;

	private int gamesPlayedToday = 0;
	private int score = 0;
	
	public int getGamesPlayedSeason() {
		return gamesPlayedSeason;
	}
	public int getGamesPlayedToday() {
		return gamesPlayedToday;
	}
	public int daysPlayedSeson() {
		return daysPlayedSeason;
	}

	public void gamePlayed() {
		this.gamesPlayedToday += 1;
		this.gamesPlayedSeason += 1;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}