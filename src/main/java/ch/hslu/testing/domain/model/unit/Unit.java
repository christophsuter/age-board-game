package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;


/**
 * Created by Christoph on 23.04.2016.
 */
public abstract class Unit {

    private static int UNIT_ID;
    private final int unitId;
    private final String name;
    private final int movementSpeed;
    private final int attackDistance;
    private final boolean horse;
    private final boolean archer;
    private final GameField gameField;
    private int health;
    private Position position;


    public Unit(Position startingPosition, GameField gameField, String name, int health, int movementSpeed, int attackDistance, boolean horse, boolean archer) {
        unitId = UNIT_ID++;
        this.name = name;
        this.health = health;
        this.gameField = gameField;
        this.movementSpeed = movementSpeed;
        this.attackDistance = attackDistance;
        this.horse = horse;
        this.archer = archer;
        this.position = startingPosition;
    }


    public int getUnitId() {
        return unitId;
    }

    public String getName() { return name; }

    public int getHealth() {
        return health;
    }

    public void attack(int damage) {
        this.health -= damage;
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

    public boolean isDead() {
        return health <= 0;
    }

    public GameField getGameField() {
        return gameField;
    }

    abstract public int calculateDamage(Unit enemyUnit);

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
