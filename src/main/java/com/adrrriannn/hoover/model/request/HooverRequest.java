package com.adrrriannn.hoover.model.request;

import com.adrrriannn.hoover.model.Position;

import java.util.List;

/**
 * Created by adrian on 11/09/17.
 */
public class HooverRequest {

    private List<Position> patches;
    private Position initialPosition;
    private Position roomSize;
    private String instructions;

    public HooverRequest() {}

    public HooverRequest(List<Position> patches, Position initialPosition, Position roomSize, String instructions) {
        this.patches = patches;
        this.initialPosition = initialPosition;
        this.roomSize = roomSize;
        this.instructions = instructions;
    }

    public List<Position> getPatches() {
        return patches;
    }

    public void setPatches(List<Position> patches) {
        this.patches = patches;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Position getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Position roomSize) {
        this.roomSize = roomSize;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
