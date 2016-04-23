package ch.hslu.testing.model.actions;

import ch.hslu.testing.model.GameState;
import ch.hslu.testing.model.unit.Unit;

/**
 * Created by Christoph on 23.04.2016.
 */
public abstract class GameAction {

    private Unit actingUnit;

    public abstract GameState act(GameState gameState);


}
