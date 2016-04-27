package ch.hslu.testing.domain;

import ch.hslu.testing.domain.model.GameState;
import ch.hslu.testing.domain.model.GameStateFactory;
import ch.hslu.testing.domain.model.Player;
import ch.hslu.testing.domain.model.actions.PlayerAction;
import ch.hslu.testing.domain.model.Position;
import ch.hslu.testing.domain.model.unit.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Christoph on 23.04.2016.
 */
public class BoardGameEngineImpl implements BoardGameEngine {

    private final Map<Player, Position> startingPositions;
    private GameState gameState;
    private Map<Player, PlayerAction> stagedActions;

    public BoardGameEngineImpl() {
        startingPositions = new HashMap<>();

        startingPositions.put(Player.Blue, new Position(0, 0));
        startingPositions.put(Player.Red, new Position(GameStateFactory.MAP_SIZE, GameStateFactory.MAP_SIZE));
    }

    @Override
    public void initGame() {
        stagedActions = new HashMap<>();

        gameState = GameStateFactory.createInitialGameState(startingPositions, 1, 2, 2);
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void preparePlayerAction(PlayerAction action) throws IllegalAcionException {

        if (gameState.isFinished()) {
            throw new IllegalAcionException("Game is finished");
        }

        stagedActions.put(action.getPlayer(), action);
    }

    @Override
    public Unit getUnit(int unitId) {
        return gameState.getUnit(unitId)
                .orElse(null);
    }

    @Override
    public void executePlayerActions() throws IllegalAcionException {

        if (!hasEveryPlayerAStagedAction()) {
            throw new IllegalAcionException("Not every player has a staged action.");
        }

        doExecutePlayerActions();

        doExecuteAttack();

        removeStagedActions();
    }

    private void doExecutePlayerActions() {
        stagedActions.values().forEach(
                action -> gameState = action.act(gameState));
    }

    private void doExecuteAttack() {
        for (Player player : startingPositions.keySet()) {
            List<Unit> units = gameState.getUnits(player);

            for (Unit unit : units) {
                Optional<Unit> enemyInSight = gameState.getEnemyInSight(player, unit);

                enemyInSight.ifPresent(enemy -> enemy.attack(unit.calculateDamage(enemy)));
            }
        }

        removeDeadUnits();
    }

    private void removeDeadUnits() {
        for (Player player : startingPositions.keySet()) {
            List<Unit> units = gameState.getUnits(player);

            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).isDead()) {
                    units.remove(i);
                    i--;
                }
            }
        }
    }

    private boolean hasEveryPlayerAStagedAction() {
        for (Player player : startingPositions.keySet()) {
            if (stagedActions.get(player) == null) {
                return false;
            }
        }

        return true;
    }

    private void removeStagedActions() {
        stagedActions.clear();
    }

}
