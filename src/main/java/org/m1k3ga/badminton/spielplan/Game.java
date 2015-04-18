package org.m1k3ga.badminton.spielplan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.exception.GameException;

/**
 * Created by m1k3ga on 11.04.15.
 */
public class Game {
  private static final Logger log = LogManager.getLogger(Game.class);
  private int scoreTeamA = 0;
  private int scoreTeamB = 0;

  private GamePairing gamePairing;

  public Game(GamePairing gamePairing) throws GameException {
    this.gamePairing = gamePairing;
  }

  public Team getTeamA() {
    return gamePairing.getTeamA();
  }

  public Team getTeamB() {
    return gamePairing.getTeamB();
  }

  public void setScoreTeamA(int scoreTeamA) {
    this.scoreTeamA = scoreTeamA;
    log.info("Set score for team A: "+scoreTeamA);
  }

  public void setScoreTeamB(int scoreTeamB) {
    this.scoreTeamB = scoreTeamB;
    log.info("Set score for team B: "+scoreTeamB);
  }

  public int getScoreTeamA() {
    return scoreTeamA;
  }

  public int getScoreTeamB() {
    return scoreTeamB;
  }

  public boolean isValidResult(){
    final int abs = Math.abs(scoreTeamA - scoreTeamB);
    final int max = Math.max(scoreTeamA, scoreTeamB);
    if (abs >= 2 && max >= 21 && max <= 30) {
      return true;
    }

    return false;
  }

}
