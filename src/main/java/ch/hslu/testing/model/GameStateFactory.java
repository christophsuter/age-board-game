package ch.hslu.testing.model;

import ch.hslu.testing.model.unit.Crossbow;
import ch.hslu.testing.model.unit.Knight;
import ch.hslu.testing.model.unit.Pikeman;
import ch.hslu.testing.model.unit.Unit;

import javax.lang.model.type.TypeKind;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Christoph on 23.04.2016.
 */
public class GameStateFactory {

    public static final int MAP_SIZE = 10;


    private GameStateFactory() {
        //Not needed its a Factory.
    }

    public static GameState createInitialGameState(Map<Player, Point> startingPositions, int knightCount, int crossbowCount, int pikeCount) {
        Map<Player, List<Unit>> units = new HashMap<>();

        for (Player player : startingPositions.keySet()) {
            List<Unit> playerUnits = createUnits(knightCount, crossbowCount, pikeCount, startingPositions.get(player));
            units.put(player, playerUnits);
        }

        return new GameState(MAP_SIZE, MAP_SIZE, units);
    }

    private static List<Unit> createUnits(int knightCount, int crossbowCount, int pikeCount, Point startingPosition) {
        List<Unit> units = new ArrayList<>();

        units.addAll(createUnits(knightCount, (i) -> new Knight(startingPosition)));
        units.addAll(createUnits(crossbowCount, (i) -> new Crossbow(startingPosition)));
        units.addAll(createUnits(pikeCount, (i) -> new Pikeman(startingPosition)));

        return units;
    }

    private static List<Unit> createUnits(int count, Function<Integer, ? extends Unit> unitProvider) {
        return IntStream.range(0, count)
                .boxed()
                .map(unitProvider)
                .collect(Collectors.toList());
    }
}
