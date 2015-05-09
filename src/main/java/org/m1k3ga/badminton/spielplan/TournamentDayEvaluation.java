package org.m1k3ga.badminton.spielplan;

import org.m1k3ga.badminton.spielplan.metrics.CounterMatrix;
import org.m1k3ga.badminton.spielplan.metrics.TeamPairingMatrix;

/**
 * Created by m1k3ga on 09.05.15.
 */
public class TournamentDayEvaluation {

  final private TeamPairingMatrix tpm = new CounterMatrix();

  public void gamePlayed(Team team) {
    final int idPlayer1 = team.getPlayer_1().getId();
    final int idPlayer2 = team.getPlayer_2().getId();
    tpm.incrementPair(idPlayer1,idPlayer2);
  }

}