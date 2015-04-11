package org.m1k3ga.badminton.spielplan;

import org.junit.Before;
import org.junit.Test;
import org.m1k3ga.badminton.Player;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by m1k3ga on 11.04.15.
 */
public class TournamentDayTest {

  @Before
  public void setUp() {
  }

  @Test
  public void OnlyOnePlayerForADay() {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));
    assertFalse(td.isValidTournamentDay());
  }

  @Test
  public void FourPlayersForADay() {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));
    td.addPlayer(new Player("Ingo"));
    td.addPlayer(new Player("Zaheed"));
    td.addPlayer(new Player("Thomas"));

    assertTrue(td.isValidTournamentDay());
  }

  @Test
  public void samePlayerTwice() {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("David"));
    Player addedDouble = new Player("Ingo");
    td.addPlayer(addedDouble );
    td.addPlayer(addedDouble );
    td.addPlayer(new Player("Thomas"));

    assertTrue(td.getNumberOfPlayersForToday() == 3);
    assertFalse(td.isValidTournamentDay());
  }

}
