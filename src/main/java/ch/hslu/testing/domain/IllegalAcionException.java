package ch.hslu.testing.domain;

/**
 * Exception which is used for if there is an IllegalAction in the board game.
 * Created by Christoph on 23.04.2016.
 */
public class IllegalAcionException extends Exception {

    public IllegalAcionException(String message) {
        super(message);
    }
}
