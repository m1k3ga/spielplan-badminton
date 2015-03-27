package org.m1k3ga.badminton.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.m1k3ga.badminton.spielplan.Team;

/**
 * Created by m1k3ga on 26.03.15.
 */
public class GameException extends Throwable {
    private static final Logger log = LogManager.getLogger(Team.class);

    public GameException(String message) {
        log.warn(message);
    }
}
