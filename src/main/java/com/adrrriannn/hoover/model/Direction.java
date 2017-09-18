package com.adrrriannn.hoover.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adrian on 11/09/17.
 */
public enum Direction {

    NORTH {
        @Override
        public Position move(Position current, Position upperBounds, Position lowerBounds) {

            return current.moveVertical(1, upperBounds, lowerBounds);
        }

        @Override
        public String getKey() {
            return "N";
        }
    },

    SOUTH {
        @Override
        public Position move(Position current, Position upperBounds, Position lowerBounds) {
            return current.moveVertical(-1, upperBounds, lowerBounds);
        }

        @Override
        public String getKey() {
            return "S";
        }
    },

    WEST {
        @Override
        public Position move(Position current, Position upperBounds, Position lowerBounds) {
            return current.moveHorizontal(-1, upperBounds, lowerBounds);
        }

        @Override
        public String getKey() {
            return "W";
        }
    },

    EAST {
        @Override
        public Position move(Position current, Position upperBounds, Position lowerBounds) {
            return current.moveHorizontal(1, upperBounds, lowerBounds);
        }

        @Override
        public String getKey() {
            return "E";
        }
    };

    public abstract Position move(Position current, Position upperBounds, Position lowerBounds);
    public abstract String getKey();

    public static Direction fromKey(final String key) {
        return Arrays.stream(values())
                .filter(x -> x.getKey().equals(key))
                .findFirst().orElseThrow(() -> new RuntimeException("Invalid instruction"));
    }

    public static List<Direction> readFromString(String instructions) {
        return Arrays.stream(instructions.split(""))
                .map(Direction::fromKey)
                .collect(Collectors.toList());
    }
}