package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;
import ch.hslu.testing.domain.model.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Created by Christoph on 23.04.2016.
 */
public abstract class Unit {

    private static int UNIT_ID;

    private final int unitId;

    private final Player player;
    private int health;
    private Position position;

    private final GameField gameField;
    private final int movementSpeed;
    private final int attackDistance;

    private final boolean horse;
    private final boolean archer;

    public Unit(Player player, int health, Position position, GameField gameField, int movementSpeed, int attackDistance, boolean horse, boolean archer) {
        unitId = UNIT_ID++;
        this.player = player;
        this.health = health;
        this.position = position;
        this.gameField = gameField;
        this.movementSpeed = movementSpeed;
        this.attackDistance = attackDistance;
        this.horse = horse;
        this.archer = archer;
    }

    public void attack(int damage) {
        this.health -= damage;
    }

    @JsonIgnore
    public boolean isDead() {
        return health <= 0;
    }

    public int getUnitId() {
        return unitId;
    }

    public Player getPlayer() { return player; }

    public GameField getGameField() {
        return gameField;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) { this.position = position; }

    public boolean isHorse() {
        return horse;
    }

    public boolean isArcher() {
        return archer;
    }

    public abstract int calculateDamage(Unit enemyUnit);

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        return unitId == unit.unitId;
    }


    @Override
    public int hashCode() {
        return unitId;
    }

}
