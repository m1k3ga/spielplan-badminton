package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for calculating team pairings
 * Here we initialize a tournament day with the list of players for that day
 * Then the teams and the game pairings are calculated
 *
 * m1k3ga
 * 11.04.15.
 */
public class TournamentDay {

  private static final Logger log = LogManager.getLogger(Team.class);

  private List<Player> playersForToday = new ArrayList<>();

  /**
   * Add a player for this tournament day
   * It will be checked for double entries
   *
   * @param player
   */
  public void addPlayer(Player player) {
    if ( isValidPlayerForToday(player) == false ) {
      log.warn("Player already added. Ignoring");
    } else {
      playersForToday.add(player);
      log.info("Added player '"+player.getPlayerName()+"'");
    }
  }


  public int getNumberOfPlayersForToday() {
    return playersForToday.size();
  }


  /**
   * At least four players should participate in a tournament day
   *
   * @return
   */
  public boolean isValidTournamentDay() {
    if ( playersForToday.size() < 4 ) {
      return false;
    }

    return true;
  }

  private boolean isValidPlayerForToday(Player player) {
    if (playersForToday.isEmpty()) {
      return true;
    }

    if (playersForToday.contains(player)) {
      return false;
    }

    return true;
  }

}
