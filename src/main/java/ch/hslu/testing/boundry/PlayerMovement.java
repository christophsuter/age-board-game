package ch.hslu.testing.boundry;

import ch.hslu.testing.domain.model.Player;

/**
 * Created by Christoph on 24.04.2016.
 */
public class PlayerMovement {

    private final Player player;
    private final int unitId;
    private final int xMovement;
    private final int yMovement;

    PlayerMovement() {
        //Serialisation
        this(null, 0, 0, 0);
    }

    public PlayerMovement(Player player, int unitId, int xMovement, int yMovement) {
        this.player = player;
        this.unitId = unitId;
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }

    public Player getPlayer() {
        return player;
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
