package ch.hslu.testing.model.unit;

import ch.hslu.testing.model.GameField;

import java.awt.*;

/**
 * Created by Christoph on 23.04.2016.
 */
public class Knight extends Unit {

    private static final int KNIGHT_HEALTH = 100;
    private static final int KNIGHT_MOVEMENT_SPEED = 2;
    private static final int KNIGHT_ATTACK_RANGE = 1;
    public static final int KNIGHT_XBOW_ATTACK = 12;
    public static final int KNIGHT_NORMAL_ATTACK = 10;

    public Knight(Position startingPosition, GameField gameField) {
        super(startingPosition, gameField, "Knight", KNIGHT_HEALTH, KNIGHT_MOVEMENT_SPEED, KNIGHT_ATTACK_RANGE, true, false);
    }

    public int calculateDamage(Unit enemyUnit) {
        if (enemyUnit.isArcher()) {
            return KNIGHT_XBOW_ATTACK;
        } else {
            return KNIGHT_NORMAL_ATTACK;
        }
    }
}
