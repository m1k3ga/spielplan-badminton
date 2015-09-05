package org.m1k3ga.badminton.spielplan.metrics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.util.KeyHandling;

import java.util.HashMap;
import java.util.Map;

/**
 * The team pairing matrix holds the number of games a player has played with another player
 * The x coordinate is the first player,
 * the y coordinate the second player
 * the value of the (x,y) combination is the number of games the two players have played together
 * <p>
 * The coordinates are the ids of the player
 */
public class TeamsPlayedTogetherCountMatrixImpl implements TeamsPlayedTogetherCountMatrix {

  private static final Logger log = LogManager.getLogger(TeamsPlayedTogetherCountMatrixImpl.class);

  private final Map<String, Integer> matrix = new HashMap<>();


  /**
   * Increment the value of the key (id1,id2)
   *
   * @param id1
   * @param id2
   */
  public void incrementPair(int id1, int id2) {
    String key = KeyHandling.getKey(id1, id2);

    if (null == matrix.get(key)) {
      matrix.put(key, 1);
      log.info("Init (" + key + ") : '1'");
    } else {
      int value = matrix.get(key);
      matrix.put(key, value + 1);
      log.info("Update (" + key + ") : '" + matrix.get(key) + "'");
    }

  }


  /**
   * Get the count for the given key parts
   *
   * @param id1
   * @param id2
   * @return
   */
  public int getCount(int id1, int id2) {
    String key = KeyHandling.getKey(id1, id2);

    if (null == matrix.get(key)) {
      log.info("No entry found for (" + key + ")");
      return 0;
    }

    return matrix.get(key);
  }


}
