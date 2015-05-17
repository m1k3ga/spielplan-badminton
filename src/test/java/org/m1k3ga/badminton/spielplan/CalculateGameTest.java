package org.m1k3ga.badminton.spielplan;

import org.junit.Test;
import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;
import org.m1k3ga.badminton.spielplan.metrics.Metrics;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by m1k3ga on 16.05.15.
 */
public class CalculateGameTest {

  @Test
  public void getBalancedGamePairing() throws GameException {

    // GIVEN :
    // A tournament day with five players
    final TournamentDay td = init();
    final CalculateGame cg = new CalculateGame(td);

    // WHEN :
    // We want a new game pairing
    final GamePairing gp = cg.getNewGamePairing();

    // THEN :
    final Team teamA = gp.getTeamA();
    final Player playerA1 = teamA.getPlayer_1();
    final Player playerA2 = teamA.getPlayer_2();
    assertTrue(playerA1.isEqual(new Player("Mike")));
    assertTrue(playerA2.isEqual(new Player("Zaheed")));

    // TODO: At the moment the first team of the remaing teams is picked, not due to pickPoints
    final Team teamB = gp.getTeamB();
    final Player playerB1 = teamB.getPlayer_1();
    final Player playerB2 = teamB.getPlayer_2();
    assertTrue(playerB1.isEqual(new Player("Bü")));
    assertTrue(playerB2.isEqual(new Player("David")));

  }


  /**
   * Prepare a tournament day for a specific configuration
   * Play a few games with pre-defined games
   *
   * @return
   */
  private TournamentDay init() throws GameException {
    final TournamentDay td = new TournamentDay();

    final Player bue = new Player("Bü");
    final Player david = new Player("David");
    final Player ingo = new Player("Ingo");
    final Player mike = new Player("Mike");
    final Player sandro = new Player("Sandro");
    final Player thomas = new Player("Thomas");
    final Player zaheed = new Player("Zaheed");

    td.addPlayer(bue);
    td.addPlayer(david);
    td.addPlayer(ingo);
    td.addPlayer(mike);
    td.addPlayer(sandro);
    td.addPlayer(thomas);
    td.addPlayer(zaheed);

    Team teamA = new Team(bue, david);
    Team teamB = new Team(ingo,sandro);
    GamePairing gp = new GamePairing(teamA, teamB);
    Game game = new Game(gp);
    td.gamePlayed(game);
    // bue, david, ingo, sandro : 1
    // mike, thomas, zaheed : 0

    teamA = new Team(bue, ingo);
    teamB = new Team(david,thomas);
    gp = new GamePairing(teamA, teamB);
    game = new Game(gp);
    td.gamePlayed(game);
    // bue: 2, david: 2, ingo:2, mike: 0, sandro: 1, thomas: 1, zaheed: 0

    teamA = new Team(bue, sandro);
    teamB = new Team(david, ingo);
    gp = new GamePairing(teamA, teamB);
    game = new Game(gp);
    td.gamePlayed(game);
    // bue: 3, david: 3, ingo: 3, mike: 0, sandro:2, thomas: 1, zaheed: 0

    return td;
  }


  private TournamentDay initTournamentDayWithFivePlayers() {
    TournamentDay td = new TournamentDay();

    // Player 0 : Played already 4 games
    Metrics m5 = new Metrics();
    m5.gamePlayed();
    m5.gamePlayed();
    m5.gamePlayed();
    m5.gamePlayed();
    td.addPlayer(new Player("Sandro",m5));

    // Player 1 : Played no games so far
    Metrics m1 = new Metrics();
    td.addPlayer(new Player("Bü",m1));

    // Player 2 : Played 3 games so far
    Metrics m4 = new Metrics();
    m4.gamePlayed();
    m4.gamePlayed();
    m4.gamePlayed();
    td.addPlayer(new Player("Thomas",m4));

    // Player 3 : Played 1 game so far
    Metrics m2 = new Metrics();
    m2.gamePlayed();
    td.addPlayer(new Player("Ingo", m2));

    // Player 4 : Played 2 games so far
    Metrics m3 = new Metrics();
    m3.gamePlayed();
    m3.gamePlayed();
    td.addPlayer(new Player("Zaheed", m3));

    return td;
  }

}
