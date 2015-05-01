package org.m1k3ga.badminton.spielplan.metrics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.Player;

import java.util.List;

/**
 * Created by m1k3ga on 21.04.15.
 */
public class TeamPairingsMatrix {
  private static final Logger log = LogManager.getLogger(TeamPairingsMatrix.class);

  private String[][] teamPairings;
  private final int size;

  public TeamPairingsMatrix(List<Player> playersForToday) {
    buildMatrix(playersForToday);
    size = playersForToday.size();
  }

  private void buildMatrix(List<Player> players) {
    teamPairings = new String[size][size];
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
