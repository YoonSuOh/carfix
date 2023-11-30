package com.carfix.testapi;

import com.carfix.testapi.service.TestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestApiController {

    private final TestApiService testApiService;
    @GetMapping("/navermap")
    public String naverMapTestApi(){
        return "test/map";
    }

    @GetMapping("/geotest")
    public String googleMapTestApi(){
        testApiService.extractCoordinatesFromAddress();
        return "test/map";
    }
}
