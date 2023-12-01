package com.carfix;

import com.carfix.common.GeoData;
import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import com.carfix.map.service.GoogleMapApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final CompanyService companyService;
    private final GoogleMapApiService googleMapApiService;

    @GetMapping("/")
    public String main(Model model){
        List<CompanyEntity> list = companyService.findByApprove();
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/")
    public String model(@RequestBody GeoData geoData){
        double lat2 = geoData.getLat();
        double lng2 = geoData.getLng();
        googleMapApiService.extractCoordinatesFromAddress(lat2, lng2);
        return "index";
    }
}
