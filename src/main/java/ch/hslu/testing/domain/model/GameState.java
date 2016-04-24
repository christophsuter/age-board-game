package ch.hslu.testing.domain.model;

import ch.hslu.testing.domain.model.unit.Position;
import ch.hslu.testing.domain.model.unit.Unit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.util.List;

/**
 * Created by Christoph on 23.04.2016.
 */
public class GameState {
    private final GameField gameField;

    private final Map<Player, List<Unit>> units;

    public GameState(GameField gameField, Map<Player, List<Unit>> units) {
        this.gameField = gameField;
        this.units = units;
    }

    /**
     * A changed unit is updated. If the unit is dead, it will be removed.
     *
     * @param updatedUnit
     */
    public void updateUnit(Unit updatedUnit) {
        for (List<Unit> playerUnits : units.values()) {
            int unitIndex = playerUnits.indexOf(updatedUnit);

            if (unitIndex >= 0) { //Add error here > 0
                playerUnits.remove(unitIndex);

                if (!updatedUnit.isDead()) {
                    playerUnits.add(unitIndex, updatedUnit);
                }
            }
        }
    }

    @JsonIgnore
    public final Set<Player> getPlayers() {
        return units.keySet();
    }

    /**
     * Get EnemyUnit which is in range of the given Units.
     * If Several Units are found, the unit with the lowest Health is returned.
     * @param player Player which is searching.
     * @param unit Unit which is searching.
     * @return EnemyUnit or empty.
     */
    public Optional<Unit> getEnemyInSight(Player player, Unit unit) {

        Position position = unit.getPosition();
        int range = unit.getAttackDistance();

        return getEnemyUnits(player).stream()
                .filter(enemyUnit -> {
                    Position enemyPosition = enemyUnit.getPosition();
                    int distance = Math.abs(position.x - enemyPosition.x) +
                            Math.abs(position.y - enemyPosition.y);
                    return distance <= range;
                })
                .filter(enemyUnit -> enemyUnit.getHealth() > 0)
                .sorted((l, r) -> l.getHealth() - r.getHealth())
                .findFirst();
    }

    public List<Unit> getEnemyUnits(Player friendlyPlayer) {
        List<Unit> enemyUnits = new ArrayList<>();

        for (Player player : getPlayers()) {
            if (player != friendlyPlayer) {
                enemyUnits.addAll(units.get(player));
            }
        }
        return enemyUnits;
    }


    public GameField getGameField() {
        return gameField;
    }

    public Map<Player, List<Unit>> getUnits() {
        return units;
    }

    public List getUnits(Player player) {
        return units.get(player);
    }

    /**
     * If there are no units left the game is finished.
     * @return true if there are no more units.
     */
    @JsonIgnore
    public boolean isFinished() {
        for (Player player : getPlayers()) {
            List<Unit> playerUnits = units.get(player);
            if (!playerUnits.isEmpty()) {
                return  false;
            }
        }

        return true;
    }

    /**
     * If there is only one Player with alive units. Then he/she is the winner.
     * @return Winner player or empty: game is not finished or draw.
     */
    @JsonIgnore
    public Optional<Player> getWinner() {
        Player winner = null;

        for (Player player : getPlayers()) {
            List<Unit> playerUnits = units.get(player);
            if (playerUnits.isEmpty()) {
                if (winner == null) {
                    winner = player;
                } else {
                    return Optional.empty();
                }
            }
        }
        return Optional.of(winner);
    }

    public Unit getUnit(int unitId) {
        for(List<Unit> playerUnits: units.values()) {
            for(Unit unit: playerUnits) {
                if(unit.getUnitId() == unitId) {
                    return unit;
                }
            }
        }
        return null;
    }
}
