package ch.hslu.testing.domain.model.unit;

import ch.hslu.testing.domain.model.GameField;
import ch.hslu.testing.domain.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static ch.hslu.testing.domain.model.Player.Blue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by christoph.suter on 27.04.2016.
 */
@RunWith(Parameterized.class)
public class MockedRuledKnightTest {
    private static final Logger LOG = LoggerFactory.getLogger(MockedRuledKnightTest.class);

    private static final GameField GAME_FIELD = new GameField(2, 3);
    private static final Position STARTING_POSITION = new Position(0, 0);
    private static final Player PLAYER = Blue;

    private Knight testee;
    private final Unit archer;
    private final int expectedDamage;

    @Parameterized.Parameters
    public static Collection UnitTypeAndExpectedDame() {
        return Arrays.asList(new Object[][] {
                { false, 10 },
                { true, 12 }
        });
    }

    public MockedRuledKnightTest(boolean isArcher, int expectedDamage) {
        LOG.info("Run with Parameter: [{}, {}]: ", isArcher, expectedDamage);

        //Arrange
        archer = mock(Unit.class);
        when(this.archer.isArcher()).thenReturn(isArcher);

        this.expectedDamage = expectedDamage;
    }

    @Before
    public void setUp() {
        testee = new Knight(PLAYER, STARTING_POSITION, GAME_FIELD);
    }

    @Test
    public void calculate_damage_vs_parametrized_archer() throws Exception {
        //Act
        int damage = testee.calculateDamage(archer);

        //Assert
        assertEquals(damage, expectedDamage);
    }

}