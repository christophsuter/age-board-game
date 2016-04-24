package ch.hslu.testing.domain;

import ch.hslu.testing.model.GameState;
import ch.hslu.testing.model.Player;
import ch.hslu.testing.model.actions.PlayerAction;

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
    void preparePlayerAction(Player player, PlayerAction action) throws IllegalAcionException;

    /**
     * Execute the prepared player actions.
     */
    void executePlayerActions() throws IllegalAcionException;
}
