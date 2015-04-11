package org.m1k3ga.badminton.spielplan;

import org.m1k3ga.badminton.Player;
import org.m1k3ga.badminton.exception.GameException;

/**
 * Created by m1k3ga on 11.04.15.
 */
public class CalculateGame {

  private TournamentDay td;

  public CalculateGame(TournamentDay td) {
    this.td = td;
  }

  public Game calculateNewGame() {
    Game game = null;

    Player player1_teamA;
    Player player2_teamA;
    Player player1_teamB;
    Player player2_teamB;

    player1_teamA = getFirstPlayer();
    player2_teamA = getSecondPlayer();
    player1_teamB = getThirdPlayer();
    player2_teamB = getFourthPlayer();

    Team teamA = new Team(player1_teamA, player2_teamA);
    Team teamB = new Team(player1_teamB, player2_teamB);

    try {
      game = new Game(teamA,teamB);
    } catch (GameException e) {
      e.printStackTrace();
    }

    return game;
  }

  private Player getFourthPlayer() {
    return null;
  }

  private Player getThirdPlayer() {
    return null;
  }


  private Player getSecondPlayer() {
    return null;
  }

  private Player getFirstPlayer() {

    return null;
  }

}
