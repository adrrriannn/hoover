package com.adrrriannn.hoover.controller;

import com.adrrriannn.hoover.service.HooverService;
import com.adrrriannn.hoover.model.request.HooverRequest;
import com.adrrriannn.hoover.model.response.HooverResponse;
import com.adrrriannn.hoover.validator.HooverRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by adrian on 11/09/17.
 */
@RestController
@RequestMapping("/hoover")
public class HooverController {

    @Autowired
    private HooverService hooverService;

    @Autowired
    private HooverRequestValidator hooverRequestValidator;

    @InitBinder("hooverRequest")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(hooverRequestValidator);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/run")
    public HooverResponse run(@Valid @RequestBody HooverRequest hooverRequest) {
        return hooverService.run(hooverRequest);
    }
}
