package com.adrrriannn.hoover.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by adrian on 15/09/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PositionTest {

    private static final Position upperBounds = new Position(10, 10);
    private static final Position lowerBounds = new Position(0, 0);

    @Test
    public void success() {
        Position position = new Position(0, 0);
        Position newPositionX = position.moveHorizontal(1, upperBounds, lowerBounds);
        assertEquals(1, newPositionX.getX());

        Position newPositionY = position.moveVertical(1, upperBounds, lowerBounds);
        assertEquals(1, newPositionY.getY());
    }

    @Test
    public void tooFarDown() {
        Position position = new Position(3, 3);
        Position newPositionX = position.moveHorizontal(-5, upperBounds, lowerBounds);
        assertEquals(lowerBounds.getX(), newPositionX.getX());

        Position newPositionY = position.moveVertical(-6, upperBounds, lowerBounds);
        assertEquals(lowerBounds.getY(), newPositionY.getY());
    }

    @Test
    public void tooFarUp() {
        Position position = new Position(3, 3);
        Position newPositionX = position.moveHorizontal(20, upperBounds, lowerBounds);
        assertEquals(upperBounds.getX(), newPositionX.getX());

        Position newPositionY = position.moveVertical(8, upperBounds, lowerBounds);
        assertEquals(upperBounds.getY(), newPositionY.getY());
    }
}
