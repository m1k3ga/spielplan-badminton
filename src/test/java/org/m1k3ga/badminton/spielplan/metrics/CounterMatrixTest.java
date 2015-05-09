package org.m1k3ga.badminton.spielplan.metrics;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by m1k3ga on 09.05.15.
 */
public class CounterMatrixTest {

  @Test
  public void commutativeKey() {
    CounterMatrix cm = new CounterMatrix();
    final int id1=1,id2=2;

    // First increment
    cm.incrementPair(id1, id2);
    int count = cm.getCount(id1,id2);
    assertEquals(1, count);

    // Second increment and get with switched ids
    cm.incrementPair(id1, id2);
    count = cm.getCount(id2,id1);
    assertEquals(2, count);
  }

  @Test
  public void sameKey() {
    CounterMatrix cm = new CounterMatrix();
    final int id1=1;

    cm.incrementPair(id1, id1);
    int count = cm.getCount(id1,id1);
    assertEquals(1, count);

  }
}