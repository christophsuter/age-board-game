package ch.hslu.testing.domain.model;

import ch.hslu.testing.domain.model.unit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Christoph on 23.04.2016.
 */
public class GameStateFactory {

    public static final int MAP_SIZE = 10;


    private GameStateFactory() {
        //Not needed its a Factory.
    }

    public static GameState createInitialGameState(Map<Player, Position> startingPositions, int knightCount, int crossbowCount, int pikeCount) {
        List<Unit> units = new ArrayList<>();

        GameField gameField = new GameField(MAP_SIZE, MAP_SIZE);

        for (Player player : startingPositions.keySet()) {
            units.addAll(createUnits(player, knightCount, crossbowCount, pikeCount, startingPositions.get(player), gameField));
        }

        return new GameState(gameField, units);
    }

    private static List<Unit> createUnits(Player player, int knightCount, int crossbowCount, int pikeCount, Position startingPosition, GameField gameField) {
        List<Unit> units = new ArrayList<>();

        units.addAll(createUnits(knightCount, (i) -> new Knight(player, startingPosition, gameField)));
        units.addAll(createUnits(crossbowCount, (i) -> new Crossbow(player, startingPosition, gameField)));
        units.addAll(createUnits(pikeCount, (i) -> new Pikeman(player, startingPosition, gameField)));

        return units;
    }

    private static List<Unit> createUnits(int count, Function<Integer, ? extends Unit> unitProvider) {
        return IntStream.range(0, count)
                .boxed()
                .map(unitProvider)
                .collect(Collectors.toList());
    }
}
