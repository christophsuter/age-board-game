package ch.hslu.testing.boundry;

/**
 * Created by Christoph on 24.04.2016.
 */
public class PlayerMovement {

    private final int unitId;
    private final int xMovement;
    private final int yMovement;

    PlayerMovement() {
        //Serialisation
        this(0, 0, 0);
    }

    public PlayerMovement(int unitId, int xMovement, int yMovement) {
        this.unitId = unitId;
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }

    public int getUnitId() {
        return unitId;
    }

    public int getxMovement() {
        return xMovement;
    }

    public int getyMovement() {
        return yMovement;
    }
}
