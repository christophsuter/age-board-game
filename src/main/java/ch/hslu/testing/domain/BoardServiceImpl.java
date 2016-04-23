package ch.hslu.testing.domain;

import ch.hslu.testing.model.GameState;
import ch.hslu.testing.model.GameStateFactory;
import ch.hslu.testing.model.Player;
import ch.hslu.testing.model.actions.GameAction;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christoph on 23.04.2016.
 */
public class BoardServiceImpl implements GameService {

    private GameState gameState;

    @Override
    public void initGame() {
        Map<Player, Point> startingPositions = new HashMap<>();

        startingPositions.put(Player.Blue, new Point(0,0));
        startingPositions.put(Player.Red, new Point(GameStateFactory.MAP_SIZE,GameStateFactory.MAP_SIZE));

        gameState = GameStateFactory.createInitialGameState(startingPositions, 1, 2, 2);
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void act(Player player, GameAction action) throws IllegalAcionException {
        //if(gameState.playerAction)
        //gameState = action.act(gameState);
    }
}
