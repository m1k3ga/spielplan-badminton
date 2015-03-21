package org.m1k3ga.badminton.spielplan;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.PlayerName;

public class TeamTest {

	private Team team;
	private Player bue;
	private Player david;
	
	
	@Before
	public void setup() {
		bue = new Player(PlayerName.BUE);
		david = new Player(PlayerName.DAVID);
	}
	
	@Test
	public void invalidTeam_NoPlayersInTeam() {
		team = new Team(null,null);
		assertFalse(team.isValid());
	}

	@Test
	public void invalidTeam_OnePlayerInTeam() {
		Team team = new Team(bue, null);
		assertFalse(team.isValid());
	}

	@Test
	public void invalidTeam_SamePlayerTwice() {
		Team team = new Team(bue, bue);
		assertFalse(team.isValid());
	}

	@Test
	public void validTeam() {
		Team team = new Team(bue,david);
		assertTrue(team.isValid());
	}

}
