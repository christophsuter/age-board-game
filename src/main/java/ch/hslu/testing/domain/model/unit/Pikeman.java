package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;

/**
 * Created by Christoph on 23.04.2016.
 */
public class Pikeman extends Unit {

    private static final int PIKEMAN_HEALTH = 55;
    private static final int PIKEMAN_MOVEMENT_SPEED = 1;
    private static final int PIKEMAN_ATTACK_RANGE = 2;
    private static final int PIKEMAN_HORSE_ATTACK = 25;
    private static final int PIKEMAN_ATTACK = 4;

    public Pikeman(Position startingPosition, GameField gameField) {
        super(startingPosition, gameField, "Pikeman", PIKEMAN_HEALTH, PIKEMAN_MOVEMENT_SPEED, PIKEMAN_ATTACK_RANGE, false, false);
    }

    public int calculateDamage(Unit enemyUnit) {
        if (enemyUnit.isHorse()) {
            return PIKEMAN_HORSE_ATTACK;
        } else {
            return PIKEMAN_ATTACK;
        }
    }
}