package ch.hslu.testing.domain;

import ch.hslu.testing.domain.model.GameState;
import ch.hslu.testing.domain.model.actions.PlayerAction;
import ch.hslu.testing.domain.model.unit.Unit;

/**
 * Created by Christoph on 23.04.2016.
 */
public interface BoardGameEngine {

    /**
     * Initializes the game.
     */
    void initGame();


    /**
     * Get the current state of the Game.
     * @return GameState
     */
    GameState getGameState();

    /**
     * In each turn, every player has to preparePlayerAction. When every player has acted, the game state is recalculated.
     */
    void preparePlayerAction(PlayerAction action) throws IllegalAcionException;

    /**
     * Get a unit by its id.
     */
    Unit getUnit(int unitId);

    /**
     * Execute the prepared player actions.
     */
    void executePlayerActions() throws IllegalAcionException;
}
