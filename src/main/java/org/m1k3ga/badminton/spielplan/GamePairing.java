package org.m1k3ga.badminton.spielplan;

import org.m1k3ga.badminton.exception.GameException;

/**
 * A game of two teams with score
 */
public class GamePairing {

  private final Team teamA;
  private final Team teamB;

  public GamePairing(Team teamA, Team teamB) throws GameException {
    if (teamA == null || teamB == null) {
      throw new GameException("Both teams have to be set");
    }
    this.teamA = teamA;
    this.teamB = teamB;
  }

  public Team getTeamA() {
    return teamA;
  }

  public Team getTeamB() {
    return teamB;
  }

  public String toString() {
    String teamAStr = (teamA != null) ? teamA.toString() : "";
    String teamBStr = (teamB != null) ? teamB.toString() : "";

    return "[ " + teamAStr + " - " + teamBStr + " ]";
  }
}