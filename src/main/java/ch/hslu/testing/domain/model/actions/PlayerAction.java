package ch.hslu.testing.domain.model.actions;

import ch.hslu.testing.domain.model.GameState;
import ch.hslu.testing.domain.model.unit.Unit;

/**
 * Created by Christoph on 23.04.2016.
 */
public abstract class PlayerAction {

    protected final Unit actingUnit;

    public PlayerAction(Unit actingUnit) {
        this.actingUnit = actingUnit;
    }

    public abstract GameState act(GameState gameState);

}
