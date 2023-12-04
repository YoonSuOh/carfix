package com.carfix.map;

import com.carfix.aop.TimeTrace;
import com.carfix.common.GeoData;
import com.carfix.map.service.GoogleMapApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/map")
public class GoogleMapController {
    private final GoogleMapApiService googleMapApiService;

    @PutMapping("/distance")
    @TimeTrace
    public Map<String, Object> getDistance(@RequestBody GeoData geoData){
        double lat2 = geoData.getLat();
        double lng2 = geoData.getLng();
        googleMapApiService.extractCoordinatesFromAddress(lat2, lng2);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }
}
