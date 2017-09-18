package com.adrrriannn.hoover.service;

import com.adrrriannn.hoover.util.TestUtils;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.model.response.HooverResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

/**
 * Created by adrian on 15/09/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HooverServiceTest {

    @Autowired
    HooverService hooverService;

    @Autowired
    HooverService hooverServiceSpy;

    private static final HooverRequest hooverRequest = TestUtils.hooverRequest();
    private static final HooverResponse expectedHooverResponse = TestUtils.hooverResponse();

    @Before
    public void setUp() {
        this.hooverServiceSpy = Mockito.spy(hooverService);
        doReturn(TestUtils.getRequestDirections()).when(hooverServiceSpy).readInstructions(hooverRequest.getInstructions());
    }

    @Test
    public void success() {

        HooverResponse hooverResponse = hooverService.run(hooverRequest);
        assertEquals(hooverResponse, expectedHooverResponse);
    }
}
