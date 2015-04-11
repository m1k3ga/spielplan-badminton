package org.m1k3ga.badminton.spielplan

import org.m1k3ga.badminton.Player
import spock.lang.Specification

/**
 * Created by m1k3ga on 11.04.15.
 */
class FourPlayerTournamentSpec extends Specification {
    def "A new tournament starts"() {
        given:
        TournamentDay td = new TournamentDay();
        td.addPlayer(new Player("Bü"));
        td.addPlayer(new Player("Ingo"));
        td.addPlayer(new Player("Zaheed"));
        td.addPlayer(new Player("Thomas"));

        when: "two games are played"
        CalculateGame cg = new CalculateGame(td);
        GamePairing gp1 = cg.getNewGamePairing();
        Game game = new Game(gp1);
        td.gamePlayed(game);

        then: "each player should have played two games"
        Player player1 = td.getPlayer(0);
        player1.gamesPlayedToday == 2;

    }
}