package ch.hslu.testing.domain;

import ch.hslu.testing.domain.model.GameField;
import ch.hslu.testing.domain.model.GameState;
import ch.hslu.testing.domain.model.Player;
import ch.hslu.testing.domain.model.Position;
import ch.hslu.testing.domain.model.actions.MovementAction;
import ch.hslu.testing.domain.model.unit.Unit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by christoph.suter on 28.04.2016.
 */
public class MockedAgeBoardGameEngineImplTest {

    public static final int START_POS_Y = 1;
    public static final int START_POS_X = 1;

    private AgeBoardGameEngineImpl testee;

    @Before
    public void setUp() throws Exception {
        testee = new AgeBoardGameEngineImpl();
    }

    @Test
    public void preparePlayerAction() throws Exception {
        //Arrange
        testee.initGame();

        Unit unitBlue = mockUnit(Player.Blue, new Position(START_POS_X, START_POS_Y));
        Unit unitRed = mockUnit(Player.Red, new Position(START_POS_X, START_POS_Y));

        GameState mockedGameState = mock(GameState.class);

        testee.gameState = mockedGameState;

        //Act
        testee.preparePlayerAction(new MovementAction(unitBlue, 1, 1));
        testee.preparePlayerAction(new MovementAction(unitRed, 1, -1));

        testee.executePlayerActions();

        //Assert
        assertPosition(unitBlue, START_POS_X + 1, START_POS_Y + 1);
        assertPosition(unitRed, START_POS_X + 1, START_POS_Y - 1);
    }

    private void assertPosition(Unit unitBlue, int newX, int newY) {
        ArgumentCaptor<Position> arg = ArgumentCaptor.forClass(Position.class);
        verify(unitBlue).setPosition(arg.capture());

        assertEquals(arg.getValue().x, newX);
        assertEquals(arg.getValue().y, newY);
    }

    private Unit mockUnit(Player player, Position position) {
        Unit unit = mock(Unit.class);
        when(unit.getPlayer()).thenReturn(player);
        when(unit.getMovementSpeed()).thenReturn(1);
        when(unit.calculateDamage(any(Unit.class))).thenReturn(5);
        when(unit.getPosition()).thenReturn(position);
        when(unit.getAttackDistance()).thenReturn(2);
        when(unit.getGameField()).thenReturn(new GameField(10, 10));

        doAnswer(this::doSomething).when(unit).setPosition(any(Position.class));

        return unit;
    }

    private Void doSomething(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        System.out.println("called with arguments: " + Arrays.toString(args));
        return null;
    }

    private Answer newAnswer() {
        return null;
    }


}