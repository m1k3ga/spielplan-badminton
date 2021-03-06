package org.m1k3ga.badminton.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Calculate a key for a hash map which based on id's from paramater
 * The key for (id1,id2) is the same as for (id2,id1).
 * Therefor mixed id's will always result in the same hash map key
 */
public class KeyHandling {
  private static final Logger log = LogManager.getLogger(KeyHandling.class);

  /**
   * Create an unique key for the hash map.
   * The key evaluation is commutative:
   * (id1,id2) == (id2,id1)
   *
   * @param id1
   * @param id2
   * @return evaluated key
   */
  public static String getKey(int id1, int id2) {
    if (id1 > id2) {
      int tmp = id1;
      id1 = id2;
      id2 = tmp;
      log.trace("Switched ids (" + id1 + "," + id2 + ")");
    }

    StringBuilder sb = new StringBuilder();
    sb.append(String.valueOf(id1));
    sb.append("_");
    sb.append(String.valueOf(id2));

    log.debug("Key: (" + sb.toString() + ")");
    return sb.toString();
  }


}
