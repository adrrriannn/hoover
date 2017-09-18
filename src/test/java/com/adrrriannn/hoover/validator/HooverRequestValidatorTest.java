package com.adrrriannn.hoover.validator;

import com.adrrriannn.hoover.model.Position;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by adrian on 18/09/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HooverRequestValidatorTest {

    @Autowired
    HooverRequestValidator hooverRequestValidator;

    private HooverRequest validRequest = TestUtils.hooverRequest();

    private Errors validate(HooverRequest hooverRequest) {

        Errors errors = new BeanPropertyBindingResult(hooverRequest, "hooverRequest");
        hooverRequestValidator.validate(validRequest, errors);
        return errors;
    }

    @Test
    public void validRequestTest() {
        assertFalse(validate(validRequest).hasErrors());
    }

    @Test
    public void invalidPatchesTest() {
        validRequest.setPatches(Collections.singletonList(new Position(-12, 0)));
        assertTrue(validate(validRequest).hasErrors());
    }

    @Test
    public void missingPatchesTest() {
        validRequest.setPatches(null);
        assertTrue(validate(validRequest).hasErrors());
    }


    @Test
    public void invalidInitialPosition() {
        validRequest.setInitialPosition(new Position(-123, 0));
        assertTrue(validate(validRequest).hasErrors());
    }

    @Test
    public void missingInitialPositionTest() {
        validRequest.setInitialPosition(null);
        assertTrue(validate(validRequest).hasErrors());
    }

    @Test
    public void invalidRoomSize() {
        validRequest.setRoomSize(new Position(-4, -7));
        assertTrue(validate(validRequest).hasErrors());
    }

    @Test
    public void missingRoomSize() {
        validRequest.setRoomSize(null);
        assertTrue(validate(validRequest).hasErrors());
    }

    @Test
    public void invalidInstructions() {
        validRequest.setInstructions("WSEA");
        assertTrue(validate(validRequest).hasErrors());
    }

    @Test
    public void missingInstructions() {
        validRequest.setInstructions(null);
        assertTrue(validate(validRequest).hasErrors());
    }

}
