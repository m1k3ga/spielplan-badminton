package org.m1k3ga.badminton.spielplan;

import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by m1k3ga on 11.04.15.
 */
public class GameTest {

  private final Player player1 = new Player("Bü");
  private final Player player2 = new Player("David");
  private final Player player3 = new Player("Ingo");
  private final Player player4 = new Player("Mike");

  @Test
  public void validGameScore() throws GameException {
    Team teamA = new Team(player1,player2);
    Team teamB = new Team(player3,player4);
    GamePairing gamePairing = new GamePairing(teamA,teamB);
    Game game = new Game(gamePairing);
    game.setScoreTeamA(21);
    game.setScoreTeamB(18);
    assertTrue(game.isValidResult());
  }

  @Test
  public void validGameScoreOvertime() throws GameException {
    Team teamA = new Team(player1,player2);
    Team teamB = new Team(player3,player4);
    GamePairing gamePairing = new GamePairing(teamA,teamB);
    Game game = new Game(gamePairing);
    game.setScoreTeamA(26);
    game.setScoreTeamB(28);
    assertTrue(game.isValidResult());
  }

  @Test
  public void invalidGameScore() throws GameException {
    Team teamA = new Team(player1,player2);
    Team teamB = new Team(player3,player4);
    GamePairing gamePairing = new GamePairing(teamA,teamB);
    Game game = new Game(gamePairing);
    game.setScoreTeamA(19);
    game.setScoreTeamB(14);
    assertFalse(game.isValidResult());
  }
}
