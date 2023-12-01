package com.carfix.map.service;

import com.carfix.common.CalcDistance;
import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.repository.CompanyRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleMapApiService {
    private final CompanyRepository companyRepository;
    private List<Double> list = new ArrayList<>();

    // 모든 회사의 주소별로 거리를 계산하여 리스트에 저장 후 반환
    public void extractCoordinatesFromAddress(double lat2, double lng2) {
        List<CompanyEntity> company = companyRepository.findAll();
        if(company != null){
            GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyA63jdrc3pRrYKwBui11CgWXcrD0arWc7o").build();
            for(CompanyEntity item : company){
                try{
                    GeocodingResult[] results = GeocodingApi.geocode(context, item.getAddress()).await();
                    if (results != null && results.length > 0) {
                        double lat1 = results[0].geometry.location.lat;
                        double lng1 = results[0].geometry.location.lng;
                        double distance = CalcDistance.calcDistance(lat1, lat2, lng1, lng2);
                        list.add(distance);
                        System.out.println("회사명: " + item.getName() + ", 주소: " + item.getAddress() + ", 위도: " + lat1 + ", 경도: " + lng1 + ", 거리: " + distance + "km");
                    }
                } catch(ApiException | InterruptedException | java.io.IOException e){
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("해당하는 회사 정보가 없습니다.");
        }
        updateDistanceByIdx();
    }

    // 거리를 업데이트
    public void updateDistanceByIdx(){
        List<Integer> idList = companyRepository.findIdx();
        for(int i=0;i<idList.size();i++){
            companyRepository.updateByDistance(list.get(i), idList.get(i));
        }
    }

}
