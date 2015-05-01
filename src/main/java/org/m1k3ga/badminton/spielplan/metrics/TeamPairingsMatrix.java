package org.m1k3ga.badminton.spielplan.metrics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;

import java.util.List;

/**
 * The team pairing matrix holds the number of games a player has played with another player
 * The x coordinate is the first player,
 * the y coordinate the second player
 * the value of the (x,y) combination is the number of games the two players have played together
 *
 * The coordinates are the ids of the player
 */
public class TeamPairingsMatrix {
  private static final Logger log = LogManager.getLogger(TeamPairingsMatrix.class);

  private String[][] teamPairings;
  private final int size;

  public TeamPairingsMatrix(List<Player> playersForToday) {
    size = playersForToday.size();
    buildMatrix(playersForToday);
  }

  private void buildMatrix(List<Player> players) {
    teamPairings = new String[size][size];
    log.info("Build team pairings matrix with '" + size + "' players");

    for (int i=0; i<size;i++) {
      String playerNameX = (players.get(i)).getPlayerName();
      teamPairings[i][i] = playerNameX;

      for (int j=1;j<size;j++) {
        String playerNameY = (players.get(j)).getPlayerName();
        teamPairings[i][j] = playerNameY;
        log.info("Adding ["+i+"]["+j+"] := " + playerNameX + " / " + playerNameY);
      }
    }
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    log.info("Logging teams:");;

    for (int i=0; i<size;i++) {
      for (int j=1;j<size;j++) {
        sb.append("Team [" + i + "][" + j + "]" + teamPairings[i][j]);;
      }
    }
    return sb.toString();
  }
}
