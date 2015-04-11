package org.m1k3ga.badminton.spielplan;

import org.m1k3ga.badminton.exception.GameException;

/**
 * A game of two teams with score
 *
 */
public class Game {

	private final Team teamA;
	private final Team teamB;
	private int scoreTeamA = 0;
	private int scoreTeamB = 0;

	public Game(Team teamA, Team teamB) throws GameException {
		if (teamA == null || teamB == null){
			throw new GameException("Both teams have to be set");
		}
		this.teamA = teamA;
		this.teamB = teamB;
	}

	public Team getTeamA() {
		return teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public void setScoreTeamA(int scoreTeamA) {
		this.scoreTeamA = scoreTeamA;
	}

	public void setScoreTeamB(int scoreTeamB) {
		this.scoreTeamB = scoreTeamB;
	}

	public int getScoreTeamA() {
		return scoreTeamA;
	}

	public int getScoreTeamB() {
		return scoreTeamB;
	}

	public boolean isValidResult(){
		final int abs = Math.abs(scoreTeamA - scoreTeamB);
		final int max = Math.max(scoreTeamA, scoreTeamB);
		if (abs >= 2 && max >= 21 && max <= 30) {
			return true;
		}

		return false;
	}
}