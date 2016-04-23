package ch.hslu.testing.model.unit;

import java.awt.Point;

/**
 * Created by Christoph on 23.04.2016.
 */
public abstract class Unit {

    private static int UNIT_ID;
    private final int unitId;
    private final int movementSpeed;
    private final int attackDistance;
    private final boolean horse;
    private final boolean archer;
    private int health;
    private Point postion;

    public Unit(Point startingPosition, int health, int movementSpeed, int attackDistance, boolean horse, boolean archer) {
        unitId = UNIT_ID++;

        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDistance = attackDistance;
        this.horse = horse;
        this.archer = archer;
        this.postion = startingPosition;
    }


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

    public Point getPostion() {
        return postion;
    }

    public boolean isHorse() {
        return horse;
    }

    public boolean isArcher() {
        return archer;
    }

    public boolean isDead() {
        return health <= 0;
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
