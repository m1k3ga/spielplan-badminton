package org.m1k3ga.badminton.spielplan

import org.m1k3ga.badminton.Player
import spock.lang.Specification

/**
 * Created by m1k3ga on 11.04.15.
 */
class FivePlayerTournamentSpec extends Specification {

    def "A new tournament starts"() {
        given:
        TournamentDay td = new TournamentDay();
        td.addPlayer(new Player("Bü"));
        td.addPlayer(new Player("David"));
        td.addPlayer(new Player("Ingo"));
        td.addPlayer(new Player("Zaheed"));
        td.addPlayer(new Player("Thomas"));

        when: "One game is played"
        GamePairingCalculator cg = new GamePairingCalculator(td.getPlayersForToday(), td.getNumberOfGamesPlayedToday());
        GamePairing gp1 = cg.getNewGamePairing();
        Game game = new Game(gp1);
        game.setScoreTeamA(21);
        game.setScoreTeamB(13);
        td.gamePlayed(game);

        then: "Four of five players should have played one game"
        td.getLastGame().getTeamA().getPlayer_1().getGamesPlayedToday() == 1;
        td.getLastGame().getTeamA().getPlayer_2().getGamesPlayedToday() == 1;
        td.getLastGame().getTeamB().getPlayer_1().getGamesPlayedToday() == 1;
        td.getLastGame().getTeamB().getPlayer_2().getGamesPlayedToday() == 1;
    }

    def "A new tournament starts with five games"() {
        given:
        TournamentDay td = new TournamentDay();
        td.addPlayer(new Player("Bü"));
        td.addPlayer(new Player("David"));
        td.addPlayer(new Player("Ingo"));
        td.addPlayer(new Player("Zaheed"));
        td.addPlayer(new Player("Thomas"));

        when: "play five games"
        GamePairingCalculator cg = new GamePairingCalculator(td.getPlayersForToday(), td.getNumberOfGamesPlayedToday());
        GamePairing gp1 = cg.getNewGamePairing();

        // TODO make for loop
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

        then: "Each of the five players should have played four games out of five"
        // TODO iterate through the players in the tournament
        // Check if each has played 4 games
    }
}