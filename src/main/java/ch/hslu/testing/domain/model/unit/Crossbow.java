package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;
import ch.hslu.testing.domain.model.Player;
import ch.hslu.testing.domain.model.Position;

/**
 * Created by Christoph on 23.04.2016.
 */
public class Crossbow extends Unit {

    private static final int XBOW_HEALTH = 35;
    private static final int XBOW_MOVEMENT_SPEED = 1;
    private static final int XBOW_ATTACK_DISTANCE = 5;
    private static final int XBOW_ATTACK = 5;

    public Crossbow(Player player, Position startingPosition, GameField gameField) {
        super(player, XBOW_HEALTH, startingPosition, gameField, XBOW_MOVEMENT_SPEED, XBOW_ATTACK_DISTANCE, false, true);
    }

    public int calculateDamage(Unit enemyUnit) {
        return XBOW_ATTACK;
    }

    @Override
    public String getName() {
        return "Crossbow";
    }
}
