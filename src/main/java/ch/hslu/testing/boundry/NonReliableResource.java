package ch.hslu.testing.boundry;

import ch.hslu.testing.AgeBoardGameApp;
import ch.hslu.testing.domain.model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.MAX_VALUE;


/**
 * Created by Christoph on 04.04.2017.
 */
@Path("/dojo/ninjas")
@Produces(MediaType.APPLICATION_JSON)
public class NonReliableResource {


    private static final Logger LOG = LoggerFactory.getLogger(NonReliableResource.class);

    private static final int TEN_SECONDS = 10 * 1000;
    private final List<Ninja> ninjas;

    public NonReliableResource() {
        ninjas = new ArrayList<>();

        ninjas.add(new Ninja("hattori", "Hattori Hanzo Sword"));
        ninjas.add(new Ninja("gogo", "Meteor hammer"));
        ninjas.add(new Ninja("batman", "Shuriken"));
        ninjas.add(new Ninja("bruce", "nun chucksr"));
    }

    /**
     * Get all known Ninjas.
     *
     * @return List of Ninjas.
     */
    @GET
    public List<Ninja> getNinjas() {
        doSomeWeirdStuff();
        return ninjas;
    }

    /**
     * Get a ninja by Name. This operation is case sensitive.
     *
     * @param name Name to filter for.
     * @return a single Ninja.
     */
    @GET
    @Path("/{name}")
    public Ninja getNinjaByName(@PathParam("name") String name) {
        doSomeWeirdStuff();
        Optional<Ninja> first = ninjas.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        return first.orElseThrow(() -> new IllegalArgumentException("Ninja " + name + " not found"));
    }

    private void doSomeWeirdStuff() {
        double random = Math.random();
        int sleepTime = 0;

        if (random < 0.4) {
            sleepTime = (int) (TEN_SECONDS * Math.random());
        } else if (random < 0.6) {
            sleepTime = MAX_VALUE;
        } else if (random < 0.8) {
            throw new RuntimeException("Just why not!");
        }

        try {
            LOG.info("Sleep for: {} ms", sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            LOG.warn("Interrupted");
        }
    }


}
