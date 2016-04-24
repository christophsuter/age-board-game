package ch.hslu.testing.model;

import java.awt.*;

/**
 * Created by Christoph on 24.04.2016.
 */
public class GameField {

    private final int width;
    private final int height;

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInField(Point position) {
        if((position.x >= 0) && (position.x <= width)) {
            if((position.y >= 0) && (position.y <= height)) {
                return true;
            }
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
