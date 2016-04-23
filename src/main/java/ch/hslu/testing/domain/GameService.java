package ch.hslu.testing.domain;

import ch.hslu.testing.model.GameState;
import ch.hslu.testing.model.Player;
import ch.hslu.testing.model.actions.GameAction;

/**
 * Created by Christoph on 23.04.2016.
 */
public interface GameService {

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
     * In each turn, every player has to act. When every player has acted, the game state is recalculated.
     */
    void act(Player player, GameAction action) throws IllegalAcionException;
}
