package com.adrrriannn.hoover.integration;

import com.adrrriannn.hoover.controller.HooverController;
import com.adrrriannn.hoover.exception.ExceptionCode;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.model.response.HooverResponse;
import com.adrrriannn.hoover.service.HooverService;
import com.adrrriannn.hoover.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by adrian on 18/09/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HooverIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    HooverController hooverController;

    @Autowired
    HooverService hooverService;

    HooverService hooverServiceSpy;

    MockMvc mockMvc;

    private HooverRequest hooverRequest = TestUtils.hooverRequest();

    private HooverResponse hooverResponse = TestUtils.hooverResponse();

    @Before
    public void setup() {
        hooverServiceSpy = Mockito.spy(hooverService);
        ReflectionTestUtils.setField(hooverController, "hooverService", hooverServiceSpy);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void hooverTestSuccess() throws Exception {
        MvcResult result = mockMvc
            .perform(
                post("/hoover/run")
                .content(TestUtils.objectToJson(hooverRequest))
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andReturn();

        assertEquals(TestUtils.parseJson(result.getResponse().getContentAsString(), HooverResponse.class), hooverResponse);
    }

    @Test
    public void hooverTestFailedBadRequest() throws Exception {

        hooverRequest.setInitialPosition(null);

        mockMvc
            .perform(
                    post("/hoover/run")
                            .content(TestUtils.objectToJson(hooverRequest))
                            .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    public void hooverTestFailedInternalServerError() throws Exception {
        doThrow(new RuntimeException("Testing exception")).when(hooverServiceSpy).run(any());
        mockMvc
                .perform(
                        post("/hoover/run")
                                .content(TestUtils.objectToJson(hooverRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }
}
