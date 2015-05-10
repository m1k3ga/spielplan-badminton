package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;

/**
 * A team consisting of two players
 *
 */
public class Team {
	private static final Logger log = LogManager.getLogger(Team.class);
	
	private final Player player_1;
	private final Player player_2;

	
	public Team(Player player1, Player player2) {
		this.player_1 = player1;
		this.player_2 = player2;
	}
	
	public Player getPlayer_1() {
		return player_1;
	}

	public Player getPlayer_2() {
		return player_2;
	}

	/**
	 * Validation for the team.
	 * Are there two players on the team?
	 *
	 * @return boolean
	 */
	public boolean isValid() {
		if (null == player_1 || null == player_2) {
			log.debug("Team is not filled");
			return false;
		}

		if (player_1 == player_2) {
			log.debug("Same player twice on team");
			return false;
		}

		return true;
	}
	
	public String toString() {
		return "(" + player_1.getName() + " / " + player_2.getName() + ")";
	}
	
}