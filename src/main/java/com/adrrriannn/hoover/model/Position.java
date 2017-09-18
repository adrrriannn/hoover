package com.adrrriannn.hoover.model;

/**
 * Created by adrian on 11/09/17.
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Position() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position moveVertical(int positions, Position upperBounds, Position lowerBounds) {
        int newY = move(y, positions, upperBounds.getY(), lowerBounds.getY());

        return new Position(x, newY);
    }

    public Position moveHorizontal(int positions, Position upperBounds, Position lowerBounds) {
        int newX = move(x, positions, upperBounds.getX(), lowerBounds.getX());

        return new Position(newX, y);
    }

    private int move(int coord, int positions, int upperBound, int lowerBound) {
        int newCoord = coord + positions;

        if(newCoord < lowerBound) {
            newCoord = lowerBound;
        } else if(newCoord > upperBound) {
            newCoord = upperBound;
        }

        return newCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position that = (Position) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
