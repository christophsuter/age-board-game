package ch.hslu.testing.internal;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Christoph on 23.04.2016.
 */
@Path("/age-board-game")
@Produces(MediaType.APPLICATION_JSON)
public class AgeBoardGameResource {

    private static int GAME_ID_COUNTER = 0;

    /**
     * Creates a new game.
     * @return id of the created game.
     */
    @PUT
    @Path("/")
    public Integer createGame() {
        return GAME_ID_COUNTER++;
    }
}
