package org.m1k3ga.badminton;

import org.m1k3ga.badminton.exception.GameException;
import org.m1k3ga.badminton.spielplan.GamePairingCalculator;
import org.m1k3ga.badminton.spielplan.Game;
import org.m1k3ga.badminton.spielplan.GamePairing;
import org.m1k3ga.badminton.spielplan.TournamentDay;
import org.m1k3ga.badminton.spielplan.metrics.CounterMatrix;
import org.m1k3ga.badminton.spielplan.metrics.TeamPairingMatrix;

/**
 * Created by m1k3ga on 18.04.15.
 */
public class Main {
  public static void main(String args[]) throws GameException {

    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));
    td.addPlayer(new Player("David"));
    td.addPlayer(new Player("Ingo"));
    td.addPlayer(new Player("Mike"));
    td.addPlayer(new Player("Sandro"));
//    td.addPlayer(new Player("Thomas"));
//    td.addPlayer(new Player("Zaheed"));


//    TeamPairingMatrix tpm = new CounterMatrix();
//    System.out.println(tpm.toString());

    GamePairingCalculator calc;
    GamePairing gp;
    Game game;

    // 1st game
    calc = new GamePairingCalculator(td.getPlayersForToday(), td.getNumberOfGamesPlayedToday());
    gp = calc.getNewGamePairing();
    System.out.println("Calculated game pairing: " + gp.toString());
    game = new Game(gp);
    game.setScoreTeamA(21);
    game.setScoreTeamB(18);
    td.gamePlayed(game);

    // 2nd game
    calc = new GamePairingCalculator(td.getPlayersForToday(), td.getNumberOfGamesPlayedToday());
    gp = calc.getNewGamePairing();
    System.out.println("Calculated game pairing: " + gp.toString());
    game = new Game(gp);
    game.setScoreTeamA(10);
    game.setScoreTeamB(21);
    td.gamePlayed(game);

    // 3rd game
    calc = new GamePairingCalculator(td.getPlayersForToday(), td.getNumberOfGamesPlayedToday());
    gp = calc.getNewGamePairing();
    System.out.println("Calculated game pairing: " + gp.toString());
    game = new Game(gp);
    game.setScoreTeamA(21);
    game.setScoreTeamB(17);
    td.gamePlayed(game);

    // 4th game
    calc = new GamePairingCalculator(td.getPlayersForToday(), td.getNumberOfGamesPlayedToday());
    gp = calc.getNewGamePairing();
    System.out.println("Calculated game pairing: " + gp.toString());
  }

}