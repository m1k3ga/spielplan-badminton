package org.m1k3ga.badminton.spielplan.metrics;

/**
 * Created by m1k3ga on 09.05.15.
 */
public interface TeamsPlayedTogetherCountMatrix {
  public void incrementPair(int id1, int id2);
  public int getCount(int id1, int id2);
}
