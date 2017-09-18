package com.adrrriannn.hoover.util;

import com.adrrriannn.hoover.model.Direction;
import com.adrrriannn.hoover.model.Position;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.model.response.HooverResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adrian on 15/09/17.
 */
public class TestUtils {

    public static HooverRequest hooverRequest() {
        Position roomSize = new Position(5, 5);
        Position coords = new Position(1,2);
        List<Position> patches = Arrays.asList(
                new Position(1,0),
                new Position(2,2),
                new Position(2, 3)
        );
        String instructions = "NNESEESWNWW";

        return new HooverRequest(patches, coords, roomSize, instructions);
    }

    public static HooverResponse hooverResponse() {
        Position finalPosition = new Position(1, 3);
        int cleanedPatches = 1;

        return new HooverResponse(finalPosition, cleanedPatches);
    }

    public static List<Direction> getRequestDirections() {
        return Direction.readFromString(hooverRequest().getInstructions());
    }

    public static <T> String objectToJson(T object) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        return objectWriter.writeValueAsString(object);
    }

    public static <T> T parseJson(String json, Class<T> clazz) throws IOException{

        ObjectMapper oM = new ObjectMapper();
        return oM.readValue(json, clazz);
    }
}
