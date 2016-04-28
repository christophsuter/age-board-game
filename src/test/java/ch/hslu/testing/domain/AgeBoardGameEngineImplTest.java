package ch.hslu.testing.domain;

        import ch.hslu.testing.domain.model.Player;
        import ch.hslu.testing.domain.model.actions.MovementAction;
        import ch.hslu.testing.domain.model.unit.Unit;
        import org.junit.Before;
        import org.junit.Test;

        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.fail;

/**
 * Created by christoph.suter on 28.04.2016.
 */
public class AgeBoardGameEngineImplTest {

    private AgeBoardGameEngine testee;

    @Before
    public void setUp() throws Exception {
        testee = new AgeBoardGameEngineImpl();
    }

    @Test
    public void preparePlayerAction() throws Exception {
        //Arrange
        testee.initGame();
        Unit unit = testee.getGameState().getUnits(Player.Blue).get(0);
        testee.preparePlayerAction(new MovementAction(unit, 0, 1));

        //Act
        try {
            testee.executePlayerActions();
            fail("Not all players are having an action.");
        } catch (IllegalAcionException ex) {
            //Assert
            assertEquals(ex.getMessage(), "Not every player has a staged action.");
        }
    }

}