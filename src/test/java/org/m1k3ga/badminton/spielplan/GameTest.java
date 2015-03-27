package org.m1k3ga.badminton.spielplan;

import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.PlayerName;
import org.m1k3ga.badminton.exception.GameException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

	private final Player player1 = new Player(PlayerName.BUE);
	private final Player player2 = new Player(PlayerName.DAVID);
	private final Player player3 = new Player(PlayerName.INGO);
	private final Player player4 = new Player(PlayerName.MIKE);


	@Test
	public void validGame() throws GameException {
		Team teamA = new Team(player1,player2);
		Team teamB = new Team(player3,player4);
		Game game = new Game(teamA,teamB);
		assertTrue(true);
	}

	@Test(expected = GameException.class)
	public void inValidGame() throws GameException {
		Team teamA = new Team(player1,player2);
		Game game = new Game(teamA,null);
		assertFalse(false);
	}

	@Test
	public void validGameScore() throws GameException {
		Team teamA = new Team(player1,player2);
		Team teamB = new Team(player3,player4);
		Game game = new Game(teamA,teamB);
		game.setScoreTeamA(21);
		game.setScoreTeamB(18);
		assertTrue(game.isValidResult());
	}

	@Test
	public void validGameScoreOvertime() throws GameException {
		Team teamA = new Team(player1,player2);
		Team teamB = new Team(player3,player4);
		Game game = new Game(teamA,teamB);
		game.setScoreTeamA(26);
		game.setScoreTeamB(28);
		assertTrue(game.isValidResult());
	}

	@Test
	public void invalidGameScore() throws GameException {
		Team teamA = new Team(player1,player2);
		Team teamB = new Team(player3,player4);
		Game game = new Game(teamA,teamB);
		game.setScoreTeamA(19);
		game.setScoreTeamB(14);
		assertFalse(game.isValidResult());
	}
}