package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;
import ch.hslu.testing.domain.model.Player;
import ch.hslu.testing.domain.model.Position;
import org.junit.Before;
import org.junit.Test;

import static ch.hslu.testing.domain.model.Player.Blue;
import static ch.hslu.testing.domain.model.Player.Red;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by christoph.suter on 27.04.2016.
 */
public class MockedKnightTest {

    private static final GameField GAME_FIELD = new GameField(2, 3);
    private static final Position STARTING_POSITION = new Position(0, 0);
    private static final Player PLAYER = Blue;
    public static final Player ENEMY = Red;

    private Knight testee;

    @Before
    public void setUp() {
        testee = new Knight(PLAYER, STARTING_POSITION, GAME_FIELD);
    }

    @Test
    public void calculate_damage_vs_no_archer() throws Exception {
        //Arrange
        Unit noArcher = mock(Unit.class);

        when(noArcher.isArcher()).thenReturn(false);

        //Act
        int damage = testee.calculateDamage(noArcher);

        //Assert
        assertEquals(damage, 10);
    }

    @Test
    public void calculate_damage_vs_crossbow() throws Exception {
        //Arrange
        Unit archer = mock(Unit.class);

        when(archer.isArcher()).thenReturn(true);

        //Act
        int damage = testee.calculateDamage(archer);

        //Assert
        assertEquals(damage, 12);
    }

}