package ch.hslu.testing.boundry;

import ch.hslu.testing.domain.BoardGameEngine;
import ch.hslu.testing.domain.IllegalAcionException;
import ch.hslu.testing.domain.model.GameState;
import ch.hslu.testing.domain.model.actions.MovementAction;
import ch.hslu.testing.domain.model.actions.PlayerAction;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Christoph on 23.04.2016.
 */
@Path("/age-board-game")
@Produces(MediaType.APPLICATION_JSON)
public class AgeBoardGameResource {

    @Inject
    private BoardGameEngine boardGameEngine;


    /**
     * Init a new game.
     */
    @PUT
    public void createGame() {
        boardGameEngine.initGame();
    }

    /**
     * Get the current GameState.
     * This state contains the players and their units.
     *
     * @return
     */
    @GET
    @Path("game-state")
    public GameState getGameState() {
        return boardGameEngine.getGameState();
    }

    /**
     * Return a status text containing the winning Player.
     * If the game is not finished or drawn, then this is returned.
     *
     * @return Text with the winner, draw or not finished.
     */
    @GET
    @Path("winner")
    @Produces("text/plain")
    public String getWinner() {
        GameState gameState = boardGameEngine.getGameState();
        if (gameState.isFinished()) {
            return gameState.getWinner()
                    .map(player -> "Player " + player.name() + " won the game.")
                    .orElse("It's a draw");

        } else {
            return "The game is not finished yet.";
        }
    }

    @POST
    @Path("player-movement")
    public void playerMoves(
            @QueryParam("unitId") int unitId,
            @QueryParam("xMovement") int xMovement,
            @QueryParam("yMovement") int yMovement) {

        try {
            PlayerAction action = MovementAction.from(new PlayerMovement(unitId, xMovement, yMovement), getGameState());
            boardGameEngine.preparePlayerAction(action);
        } catch (IllegalAcionException ex) {
            throw new RuntimeException(ex);
        }

    }

    @POST
    @Path("attack")
    public void doAttack() {
        try {
            boardGameEngine.executePlayerActions();
        } catch (IllegalAcionException ex) {
            throw new RuntimeException(ex);
        }
    }

}
