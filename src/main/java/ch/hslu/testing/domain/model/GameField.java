package ch.hslu.testing.domain.model;

import java.awt.*;

/**
 * Container for GameField.
 * Created by Christoph on 24.04.2016.
 */
public class GameField {

    private final int startX;
    private final int startY;

    private final int width;
    private final int height;

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;

        startX = 0;
        startY = 0;
    }

    public boolean isInField(Point position) {

        if((position.x >= startX) && (position.x <= width)) {
            if((position.y >= startY) && (position.y <= height)) {
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

    public int getStartX() { return startX; }

    public int getStartY() { return startY; }
}
