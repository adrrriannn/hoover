package com.adrrriannn.hoover.service;

import com.adrrriannn.hoover.model.Position;
import com.adrrriannn.hoover.model.Direction;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.model.response.HooverResponse;
import com.adrrriannn.hoover.util.Constants;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by adrian on 11/09/17.
 */
@Service
public class HooverService {

    public HooverResponse run(HooverRequest request) {
        List<Position> dirtPatches = request.getPatches();
        List<Direction> directions = readInstructions(request.getInstructions());
        Position currentPosition = request.getInitialPosition();
        Position roomUpperBounds = request.getRoomSize();

        Set<Position> cleanedPatches = new HashSet<>();
        for(Direction direction : directions) {

            if(dirtPatches.contains(currentPosition)) {
                cleanedPatches.add(currentPosition);
            }

            currentPosition = direction.move(currentPosition, roomUpperBounds, Constants.ROOM_LOWER_BOUNDS);
        }

        return new HooverResponse(currentPosition, cleanedPatches.size());
    }

    public List<Direction> readInstructions(String instructions) {
        return Direction.readFromString(instructions);
    }
}