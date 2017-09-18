package com.adrrriannn.hoover.validator;

import com.adrrriannn.hoover.exception.ExceptionCode;
import com.adrrriannn.hoover.model.Position;
import com.adrrriannn.hoover.model.request.HooverRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by adrian on 12/09/17.
 */
@Component
public class HooverRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return HooverRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HooverRequest hooverRequest = (HooverRequest) o;

        validateRoomSize(errors, hooverRequest);
        validatePatches(errors, hooverRequest);
        validateInitialPosition(errors, hooverRequest);
        validateInstructions(errors, hooverRequest);
    }

    private void validatePositions(Position position, Errors errors, String errorField, ExceptionCode exceptionCode) {
        if(position == null || position.getX() < 0 || position.getY() < 0) {
            errors.rejectValue(errorField, exceptionCode.getCode(), "Position coordinates can not be less than 0");
        }
    }

    private void validateRoomSize(Errors errors, HooverRequest hooverRequest) {
        ValidationUtils.rejectIfEmpty(
                errors,
                "roomSize",
                ExceptionCode.INVALID_ROOM_SIZE.getCode(),
                "Patches must be specified"
        );
        validatePositions(hooverRequest.getRoomSize(), errors, "roomSize", ExceptionCode.INVALID_ROOM_SIZE);
    }

    private void validatePatches(Errors errors, HooverRequest hooverRequest) {
        ValidationUtils.rejectIfEmpty(
                errors,
                "patches",
                ExceptionCode.INVALID_PATCHES.getCode(),
                "Patches must be specified"
        );

        List<Position> patches = hooverRequest.getPatches();
        if(patches != null) {
            patches
                    .forEach(x -> validatePositions(x, errors, "patches", ExceptionCode.INVALID_PATCHES));
        }
    }

    private void validateInitialPosition(Errors errors, HooverRequest hooverRequest) {
        ValidationUtils.rejectIfEmpty(
                errors,
                "initialPosition",
                ExceptionCode.INVALID_INITIAL_POSITION.getCode(),
                "Initial position must be specified"
        );

        validatePositions(hooverRequest.getInitialPosition(), errors, "initialPosition", ExceptionCode.INVALID_INITIAL_POSITION);
    }

    private void validateInstructions(Errors errors, HooverRequest hooverRequest) {
        ValidationUtils.rejectIfEmpty(
                errors,
                "instructions",
                ExceptionCode.INVALID_INSTRUCTIONS.getCode(),
                "Instructions must be specified"
        );

        String instructions = hooverRequest.getInstructions();
        if(instructions != null && !instructions.matches("[NSWE]+")) {
            errors.rejectValue("instructions", ExceptionCode.INVALID_INSTRUCTIONS.getCode(), "Instructions contains invalid values");
        }
    }
}
