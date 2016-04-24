package ch.hslu.testing.model.unit;

import ch.hslu.testing.model.GameField;

import java.awt.*;

/**
 * Created by Christoph on 23.04.2016.
 */
public class Crossbow extends Unit {

    private static final int XBOW_HEALTH = 35;
    private static final int XBOW_MOVEMENT_SPEED = 1;
    private static final int XBOW_ATTACK_DISTANCE = 5;
    private static final int XBOW_ATTACK = 5;

    public Crossbow(Position startingPosition, GameField gameField) {
        super(startingPosition, gameField, "Crossbow", XBOW_HEALTH, XBOW_MOVEMENT_SPEED, XBOW_ATTACK_DISTANCE, false, true);
    }

    public int calculateDamage(Unit enemyUnit) {
        return XBOW_ATTACK;
    }
}
