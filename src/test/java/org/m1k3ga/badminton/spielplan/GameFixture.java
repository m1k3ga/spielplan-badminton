package org.m1k3ga.badminton.spielplan;

import static org.junit.Assert.*;

import org.junit.Test;
import org.m1k3ga.badminton.spielplan.Game;

public class GameFixture {

	@Test
	public void getAValidGameFixture() {
		Game game = new Game();
		game.getGame();
	}

}