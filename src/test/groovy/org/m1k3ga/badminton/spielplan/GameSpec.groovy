package org.m1k3ga.badminton.spielplan

import org.m1k3ga.badminton.Player
import org.m1k3ga.badminton.PlayerName
import spock.lang.Specification

/**
 * Created by m1k3ga on 11.04.15.
 */
class GameSpec extends Specification {
    def "valid game with two teams"() {
        given:
        Player player1 = new Player("Bü");
        Player player2 = new Player("David");
        Player player3 = new Player("Ingo");
        Player player4 = new Player("Mike");

        Team teamA = new Team(player1,player2);
        Team teamB = new Team(player3,player4);
        Game game = new Game(teamA,teamB);

        when:
        game.setScoreTeamA(21);
        game.setScoreTeamB(18);

        then:
        game.isValidResult();
    }
}
