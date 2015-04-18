package org.m1k3ga.badminton;

import org.m1k3ga.badminton.spielplan.CalculateGame;
import org.m1k3ga.badminton.spielplan.GamePairing;
import org.m1k3ga.badminton.spielplan.TournamentDay;

/**
 * Created by m1k3ga on 18.04.15.
 */
public class Main {
  public static void main(String args[]) {

    TournamentDay td = new TournamentDay();
    td.addPlayer(new Player("Bü"));
    td.addPlayer(new Player("David"));
    td.addPlayer(new Player("Ingo"));
    td.addPlayer(new Player("Mike"));
    td.addPlayer(new Player("Sandro"));
//    td.addPlayer(new Player("Thomas"));
//    td.addPlayer(new Player("Zaheed"));
    CalculateGame calc = new CalculateGame(td);
    GamePairing gp = calc.getNewGamePairing();
    System.out.println(gp.toString());
  }
}
