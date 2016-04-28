package ch.hslu.testing.domain.model;

import ch.hslu.testing.domain.model.unit.Unit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * State of the Age-Board-Game.
 * Containing following fields:
 * <ul>
 * <li>GameField</li>
 * <li>List of Units</li>
 * </ul>
 * Created by Christoph on 23.04.2016.
 */
public class GameState {
    private final GameField gameField;

    private final List<Unit> units;

    protected GameState(GameField gameField, List<Unit> units) {
        this.gameField = gameField;
        this.units = units;
    }

    /**
     * Get EnemyUnit which is in range of the given Units.
     * If Several Units are found, the unit with the lowest health is returned.
     *
     * @param player Player which is searching.
     * @param unit   Unit which is searching.
     * @return EnemyUnit or empty.
     */
    public Optional<Unit> getEnemyInSight(Player player, Unit unit) {
        Position position = unit.getPosition();
        int range = unit.getAttackDistance();

        return getEnemyUnits(player).stream()
                .filter(Unit::isAlive)
                .filter(enemyUnit -> unit.isEnemyInRange(enemyUnit))
                .sorted((l, r) -> l.getHealth() - r.getHealth())
                .findFirst();
    }

    public List<Unit> getEnemyUnits(Player friendlyPlayer) {
        return units.stream()
                .filter(u -> u.getPlayer() != friendlyPlayer)
                .collect(Collectors.toList());
    }

    public List<Unit> getUnits(Player player) {
        return units.stream()
                .filter(u -> u.getPlayer() == player)
                .collect(Collectors.toList());
    }

    /**
     * If there are no units left the game is finished.
     *
     * @return true if there are no more units.
     */
    @JsonIgnore
    public boolean isFinished() {
        return getAlivePlayers().size() <= 0;
    }

    /**
     * If there is only one Player with alive units. Then he/she is the winner.
     *
     * @return Winner player or empty: game is not finished or draw.
     */
    @JsonIgnore
    public Optional<Player> getWinner() {
        Set<Player> alivePlayers = getAlivePlayers();

        if (alivePlayers.size() == 1) {
            return Optional.of(alivePlayers.iterator().next());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Unit> getUnit(int unitId) {
        return units.stream().
                filter(u -> u.getUnitId() == unitId)
                .findFirst();
    }

    public GameField getGameField() {
        return gameField;
    }

    public List<Unit> getUnits() {
        return units;
    }

    @JsonIgnore
    private final Set<Player> getAlivePlayers() {
        return units.stream()
                .collect(Collectors.groupingBy(unit -> unit.getPlayer()))
                .keySet();
    }
}
