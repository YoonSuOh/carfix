package com.carfix.testapi.service;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.repository.CompanyRepository;
import com.carfix.common.GeoData;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestApiService {
    private final CompanyRepository companyRepository;

    // 사용자 위치 정보 주소 반환
    public GeoData extractCoordinatesFromAddress() {
        CompanyEntity company = companyRepository.findByCompanyIdx(2);
        GeoData geoData = new GeoData();
        if (company != null) {
            GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyA63jdrc3pRrYKwBui11CgWXcrD0arWc7o").build();

            try {
                GeocodingResult[] results = GeocodingApi.geocode(context, company.getAddress()).await(); // Google Geocoding API로 좌표 추출
                if (results != null && results.length > 0) {
                    double latitude = results[0].geometry.location.lat;
                    double longitude = results[0].geometry.location.lng;

                    geoData.setLat(latitude);
                    geoData.setLng(longitude);
                    // 좌표 정보를 사용하여 필요한 작업 수행 (예: 좌표 DB에 저장)
                    System.out.println("회사명: " + company.getName() + ", 주소: " + company.getAddress() + ", 위도: " + latitude + ", 경도: " + longitude);
                    return geoData;
                }
            } catch (ApiException | InterruptedException | java.io.IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("해당하는 회사 정보가 없습니다.");
        }
        return geoData;
    }

    // 거리 계산 후 반환
    public double calcDistance(double lat1, double lat2, double lng1, double lng2){
        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;
        dist = Math.round(dist);

        dist = dist/1000;
        dist = Math.round(dist * 10.0) / 10.0;

        return dist; //단위 meter
    }

    //10진수를 radian(라디안)으로 변환
    public double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    public double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }

}
