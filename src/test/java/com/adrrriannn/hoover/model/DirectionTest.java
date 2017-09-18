package com.adrrriannn.hoover.model;

import com.adrrriannn.hoover.util.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by adrian on 15/09/17.
 */
public class DirectionTest {

    private static final Position UPPER_BOUNDS = new Position(5, 5);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void fromStringSuccessTest() {
        String instructions = "NSWEWS";
        List<Direction> expectedDirections = Arrays.asList(
                Direction.NORTH, Direction.SOUTH, Direction.WEST,
                Direction.EAST, Direction.WEST, Direction.SOUTH
        );

        assertEquals(expectedDirections, Direction.readFromString(instructions));
    }

    @Test
    public void fromStringFailTest() {
        expectedException.expect(RuntimeException.class);
        String instructions = "NWSAWE";

        Direction.readFromString(instructions);
    }

    @Test
    public void movingNorthDirection() {
        Position position = new Position(0, 0);
        Position newPosition = Direction.NORTH.move(position, UPPER_BOUNDS, Constants.ROOM_LOWER_BOUNDS);
        assertEquals(1, newPosition.getY());
        assertEquals(position.getX(), newPosition.getX());
    }

    @Test
    public void movingSouthDirection() {
        Position position = new Position(1, 1);
        Position newPosition = Direction.SOUTH.move(position, UPPER_BOUNDS, Constants.ROOM_LOWER_BOUNDS);
        assertEquals(0, newPosition.getY());
        assertEquals(position.getX(), newPosition.getX());
    }

    @Test
    public void movingWestDirection() {
        Position position = new Position(1, 1);
        Position newPosition = Direction.WEST.move(position, UPPER_BOUNDS, Constants.ROOM_LOWER_BOUNDS);
        assertEquals(0, newPosition.getX());
        assertEquals(position.getY(), newPosition.getY());
    }

    @Test
    public void movingEastDirection() {
        Position position = new Position(0, 0);
        Position newPosition = Direction.EAST.move(position, UPPER_BOUNDS, Constants.ROOM_LOWER_BOUNDS);
        assertEquals(1, newPosition.getX());
        assertEquals(position.getY(), newPosition.getY());
    }
}
