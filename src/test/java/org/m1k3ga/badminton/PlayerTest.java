package org.m1k3ga.badminton;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by m1k3ga on 11.04.15.
 */
public class PlayerTest {

  @Test
  public void isEqual() {
    Player p1 = new Player("Eins");
    Player p2 = new Player("Eins");
    assertTrue(p1.isEqual(p2));
  }

  @Test
  public void gamePlayed() {
    Player p1 = new Player("Eins");
    p1.gamePlayed();
    int games = p1.getGamesPlayedToday();
    assertEquals(1,games);
  }

}