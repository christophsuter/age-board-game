package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;
import ch.hslu.testing.domain.model.Player;
import ch.hslu.testing.domain.model.Position;

/**
 * Created by Christoph on 23.04.2016.
 */
public class Knight extends Unit {

    private static final int KNIGHT_HEALTH = 100;
    private static final int KNIGHT_MOVEMENT_SPEED = 2;
    private static final int KNIGHT_ATTACK_RANGE = 1;
    public static final int KNIGHT_XBOW_ATTACK = 12;
    public static final int KNIGHT_NORMAL_ATTACK = 10;

    public Knight(Player player, Position startingPosition, GameField gameField) {
        super(player, KNIGHT_HEALTH, startingPosition, gameField, KNIGHT_MOVEMENT_SPEED, KNIGHT_ATTACK_RANGE, true, false);
    }

    public int calculateDamage(Unit enemyUnit) {
        if (enemyUnit.isArcher()) {
            return KNIGHT_XBOW_ATTACK;
        } else {
            return KNIGHT_NORMAL_ATTACK;
        }
    }

    @Override
    public String getName() {
        return "Knight";
    }
}
