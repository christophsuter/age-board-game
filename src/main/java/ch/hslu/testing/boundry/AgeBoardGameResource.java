package ch.hslu.testing.boundry;

import ch.hslu.testing.domain.BoardServiceImpl;
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
    private BoardServiceImpl boardService;

    /**
     * Creates a new game.
     * @return id of the created game.
     */
    @PUT
    @Path("/")
    public Integer createGame() {
        return 0;
        //return GAME_ID_COUNTER++;
    }

    /**
     * Get newest ID;
     * @return id of the created game.
     */
    @GET
    @Path("/")
    public Integer getGameId() {
        return 0;
        //return GAME_ID_COUNTER;
    }
}
