package org.m1k3ga.badminton;

import org.m1k3ga.badminton.exception.GameException;
import org.m1k3ga.badminton.spielplan.CalculateGame;
import org.m1k3ga.badminton.spielplan.Game;
import org.m1k3ga.badminton.spielplan.GamePairing;
import org.m1k3ga.badminton.spielplan.TournamentDay;
import org.m1k3ga.badminton.spielplan.metrics.TeamPairingsMatrix;

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
//    td.addPlayer(new Player("Sandro"));
//    td.addPlayer(new Player("Thomas"));
//    td.addPlayer(new Player("Zaheed"));


    TeamPairingsMatrix tpm = new TeamPairingsMatrix(td.getPlayers());
    System.out.println(tpm.toString());
System.exit(0);



    CalculateGame calc = new CalculateGame(td);
    GamePairing gp = calc.getNewGamePairing();
    System.out.println("Calculated game pairing: " + gp.toString());
    Game game = new Game(gp);
    game.setScoreTeamA(21);
    game.setScoreTeamB(18);
    td.gamePlayed(game);
  }
}
