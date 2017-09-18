package com.adrrriannn.hoover.controller;
import com.adrrriannn.hoover.util.TestUtils;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.model.response.HooverResponse;
import com.adrrriannn.hoover.service.HooverService;
import com.adrrriannn.hoover.validator.HooverRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;



/**
 * Created by adrian on 13/09/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HooverControllerTest {

    private static final HooverRequest hooverRequest = TestUtils.hooverRequest();
    private static final HooverResponse hooverResponse = TestUtils.hooverResponse();

    @Mock
    private HooverService hooverService;

    @Mock
    private HooverRequestValidator hooverRequestValidator;

    @InjectMocks
    private HooverController hooverController;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        doReturn(hooverResponse).when(hooverService).run(any());
        doNothing().when(hooverRequestValidator).validate(any(), any());
    }

    @Test
    public void hooverRequestSuccess() {

        assertEquals(hooverResponse, hooverController.run(hooverRequest))   ;
    }
}
