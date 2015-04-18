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

        when: "One game is played"
        CalculateGame cg = new CalculateGame(td);
        GamePairing gp1 = cg.getNewGamePairing();
        Game game = new Game(gp1);
        game.setScoreTeamA(21);
        game.setScoreTeamB(13);
        td.gamePlayed(game);

        then: "Each player should have played one game"
        td.getLastGame().getTeamA().getPlayer_1().getGamesPlayedToday() == 1;
        td.getLastGame().getTeamA().getPlayer_2().getGamesPlayedToday() == 1;
        td.getLastGame().getTeamB().getPlayer_1().getGamesPlayedToday() == 1;
        td.getLastGame().getTeamB().getPlayer_2().getGamesPlayedToday() == 1;
    }

    def "A new tournament starts with two games"() {
        given:
        TournamentDay td = new TournamentDay();
        td.addPlayer(new Player("Bü"));
        td.addPlayer(new Player("Ingo"));
        td.addPlayer(new Player("Zaheed"));
        td.addPlayer(new Player("Thomas"));

        when: "Two games are played"
        CalculateGame cg = new CalculateGame(td);
        GamePairing gp1 = cg.getNewGamePairing();
        Game game = new Game(gp1);
        game.setScoreTeamA(21);
        game.setScoreTeamB(13);
        td.gamePlayed(game);

        gp1 = cg.getNewGamePairing();
        game = new Game(gp1);
        game.setScoreTeamA(21);
        game.setScoreTeamB(13);
        td.gamePlayed(game);

        then: "Each player should have played two games"
        td.getLastGame().getTeamA().getPlayer_1().getGamesPlayedToday() == 2;
        td.getLastGame().getTeamA().getPlayer_2().getGamesPlayedToday() == 2;
        td.getLastGame().getTeamB().getPlayer_1().getGamesPlayedToday() == 2;
        td.getLastGame().getTeamB().getPlayer_2().getGamesPlayedToday() == 2;
    }
}