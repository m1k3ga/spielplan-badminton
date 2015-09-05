package org.m1k3ga.badminton.spielplan;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;

import static org.junit.Assert.assertEquals;
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
    TournamentDay td = initTournamentDayWithFourPlayers();

    assertTrue(td.getNumberOfPlayersForToday() == 4);
    assertTrue(td.isValidTournamentDay());
    assertTrue(td.getNumberOfGamesPlayedToday() == 0);
  }

  @Test
  public void samePlayerTwice() {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("David"));
    Player addedDouble = new Player("Ingo");
    td.addPlayer(addedDouble);
    td.addPlayer(addedDouble);
    td.addPlayer(new Player("Thomas"));

    assertTrue(td.getNumberOfPlayersForToday() == 3);
    assertFalse(td.isValidTournamentDay());
  }

  @Test
  public void FourPlayerWithSamePickPointsAndOnePlayerWithNoGame() throws GameException {
    // GIVEN :
    //    A tournament day with five players
    TournamentDay td = initTournamentDayWithFourPlayers();
    td.addPlayer(new Player("David"));

    // Pick the teams for the first game
    Team teamA = new Team(td.getPlayer(0), td.getPlayer(1));
    Team teamB = new Team(td.getPlayer(2), td.getPlayer(3));

    GamePairing gp = new GamePairing(teamA, teamB);
    Game game = new Game(gp);

    // WHEN :
    //    One game is played
    td.gamePlayed(game);

    // THEN :
    //    Each player should have the same pickPoints
    //    Except the fifth player, which should have zero pickPoints

    // FIXME
    // Check for the number of games each player has played
  }

  @Test
  public void SixPlayerTournamentTwoGamesPlayed() throws GameException {
    // GIVEN :
    //    A tournament day with five players
    TournamentDay td = initTournamentDayWithFourPlayers();
    td.addPlayer(new Player("David"));       // => 4
    td.addPlayer(new Player("Thomas"));      // => 5

    // FIRST GAME
    // Pick the teams for the first game
    Team teamA = new Team(td.getPlayer(0), td.getPlayer(1));
    Team teamB = new Team(td.getPlayer(2), td.getPlayer(3));

    GamePairing gp = new GamePairing(teamA, teamB);
    Game game = new Game(gp);
    td.gamePlayed(game);

    // SECOND GAME
    // Pick the teams for the second game
    teamA = new Team(td.getPlayer(0), td.getPlayer(2));
    teamB = new Team(td.getPlayer(3), td.getPlayer(4));

    gp = new GamePairing(teamA, teamB);
    game = new Game(gp);
    td.gamePlayed(game);

    // THEN :
    // Check for the number of games each player has played
    // FIXME
  }

  private TournamentDay initTournamentDayWithFourPlayers() {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));       // => 0
    td.addPlayer(new Player("Ingo"));     // => 1
    td.addPlayer(new Player("Zaheed"));   // => 2
    td.addPlayer(new Player("Thomas"));   // => 3

    return td;
  }
}
