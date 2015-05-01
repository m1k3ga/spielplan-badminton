package org.m1k3ga.badminton.spielplan;

import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;

import static org.junit.Assert.assertEquals;

/**
 * Created by m1k3ga on 01.05.15.
 */
public class FivePlayerTournamentTest {

  @Test
  public void playOneGame() throws GameException {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));
    td.addPlayer(new Player("David"));
    td.addPlayer(new Player("Ingo"));
    td.addPlayer(new Player("Zaheed"));
    td.addPlayer(new Player("Thomas"));

    CalculateGame cg = new CalculateGame(td);
    GamePairing gp1 = cg.getNewGamePairing();
    Game game = new Game(gp1);
    game.setScoreTeamA(21);
    game.setScoreTeamB(13);
    td.gamePlayed(game);

    assertEquals(td.getLastGame().getTeamA().getPlayer_1().getGamesPlayedToday(),1);
    assertEquals(td.getLastGame().getTeamA().getPlayer_2().getGamesPlayedToday(),1);
    assertEquals(td.getLastGame().getTeamB().getPlayer_1().getGamesPlayedToday(),1);
    assertEquals(td.getLastGame().getTeamB().getPlayer_2().getGamesPlayedToday(),1);
  }

  @Test
  public void FivePlayersWithFiveGames() throws GameException {
    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));
    td.addPlayer(new Player("David"));
    td.addPlayer(new Player("Ingo"));
    td.addPlayer(new Player("Zaheed"));
    td.addPlayer(new Player("Thomas"));

    CalculateGame cg = new CalculateGame(td);
    GamePairing gp1 = cg.getNewGamePairing();

    // play games
    Game game = new Game(gp1);
    game.setScoreTeamA(21);
    game.setScoreTeamB(13);
    td.gamePlayed(game);

    gp1 = cg.getNewGamePairing();
    game = new Game(gp1);
    game.setScoreTeamA(21);
    game.setScoreTeamB(15);
    td.gamePlayed(game);

    gp1 = cg.getNewGamePairing();
    game = new Game(gp1);
    game.setScoreTeamA(21);
    game.setScoreTeamB(17);
    td.gamePlayed(game);

    gp1 = cg.getNewGamePairing();
    game = new Game(gp1);
    game.setScoreTeamA(10);
    game.setScoreTeamB(21);
    td.gamePlayed(game);

    gp1 = cg.getNewGamePairing();
    game = new Game(gp1);
    game.setScoreTeamA(10);
    game.setScoreTeamB(21);
    td.gamePlayed(game);

    // Now, each player should have played 4 games
    Player p;
    for (int i=0;i<td.getNumberOfPlayers();i++) {
      p = td.getPlayer(i);
      assertEquals(p.getGamesPlayedToday(),4);
    }
  }
}
