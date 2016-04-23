package ch.hslu.testing.model;

import ch.hslu.testing.model.unit.Crossbow;
import ch.hslu.testing.model.unit.Knight;
import ch.hslu.testing.model.unit.Pikeman;
import ch.hslu.testing.model.unit.Unit;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Christoph on 23.04.2016.
 */
public class GameState {
    private final int width;
    private final int height;

    private final Map<Player, List<Unit>> units;

    public GameState(int width, int height, Map<Player, List<Unit>> units) {
        this.width = width;
        this.height = height;
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

    public final Set<Player> getPlayers() {
        return units.keySet();
    }

    //TODO: What if both are dead??
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

    public Optional<Unit> getEnemyInSight(Player player, Point position, int range) {
        return getEnemyUnits(player).stream()
                .filter(unit -> {
                    Point enemyPosition = unit.getPostion();
                    int distance = Math.abs(position.x - enemyPosition.x) +
                            Math.abs(position.y - enemyPosition.y);
                    return distance <= range;
                }).sorted((l, r) -> l.getHealth() - r.getHealth())
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
