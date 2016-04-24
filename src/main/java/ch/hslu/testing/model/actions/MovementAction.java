package ch.hslu.testing.model.actions;

import ch.hslu.testing.boundry.PlayerMovement;
import ch.hslu.testing.domain.IllegalAcionException;
import ch.hslu.testing.model.GameState;
import ch.hslu.testing.model.Player;
import ch.hslu.testing.model.unit.Position;
import ch.hslu.testing.model.unit.Unit;

import java.util.List;

/**
 * Created by Christoph on 24.04.2016.
 */
public class MovementAction extends PlayerAction {

    private final int xMovement;
    private final int yMovement;

    public MovementAction(Unit actingUnit, int xMovement, int yMovement) {
        super(actingUnit);
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }

    public static PlayerAction from(PlayerMovement playerMovement, GameState gameState) throws IllegalAcionException {

        Unit unit = gameState.getUnit(playerMovement.getUnitId());

        if (unit.getMovementSpeed() < Math.abs(playerMovement.getxMovement()) + Math.abs(playerMovement.getyMovement())) {
            throw new IllegalAcionException("Movement is to high. Only " + unit.getMovementSpeed() + " is allowed");
        }

        return new MovementAction(unit, playerMovement.getxMovement(), playerMovement.getyMovement());
    }

    @Override
    public GameState act(GameState gameState) {
        int xNow = actingUnit.getPosition().x;
        int xNext = xNow + xMovement;
        int xMax = actingUnit.getGameField().getWidth();

        if(xNext > xMax) {
            xNext = xMax;
        }

        if(xNext < 0) {
            xNext = 0;
        }

        int yNow = actingUnit.getPosition().y;
        int yNext = yNow + yMovement;
        int yMax = actingUnit.getGameField().getHeight();

        if(yNext > yMax) {
            yNext = yMax;
        }

        if(yNext < 0) {
            yNext = 0;
        }

        actingUnit.setPosition(new Position(xNext, yNext));

        return gameState;
    }
}
