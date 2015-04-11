package org.m1k3ga.badminton.spielplan;

import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GamePairingTest {

	private final Player player1 = new Player("Bü");
	private final Player player2 = new Player("David");
	private final Player player3 = new Player("Ingo");
	private final Player player4 = new Player("Mike");


	@Test
	public void validGamePairing() throws GameException {
		Team teamA = new Team(player1,player2);
		Team teamB = new Team(player3,player4);
		GamePairing game = new GamePairing(teamA,teamB);
		assertTrue(true);
	}

	@Test(expected = GameException.class)
	public void inValidGamePairing_NoSecondTeam() throws GameException {
		Team teamA = new Team(player1,player2);
		GamePairing game = new GamePairing(teamA,null);
		assertFalse(false);
	}


}