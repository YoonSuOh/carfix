package com.carfix.map.service;

import com.carfix.common.GeoData;
import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.repository.CompanyRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleMapApiService {
    private final CompanyRepository companyRepository;

    // 모든 회사의 주소별로 거리를 계산하여 리스트에 저장 후 반환
    public List<GeoData> calculateDistancesForAllCompanies() {
        List<GeoData> geoDataList = new ArrayList<>();

        List<CompanyEntity> companies = companyRepository.findAll(); // 모든 회사 정보 가져오기

        for (CompanyEntity company : companies) {
            GeoData geoData = extractCoordinatesFromAddress(company.getAddress());
            if (geoData != null) {
                geoDataList.add(geoData); // 주소별로 계산된 거리를 리스트에 추가
            }
        }

        return geoDataList;
    }

    // 기존의 extractCoordinatesFromAddress() 메서드를 수정하여 주소별로 좌표 추출하는 메서드로 변경
    private GeoData extractCoordinatesFromAddress(String address) {
        GeoData geoData = new GeoData();
        GeoApiContext context = new GeoApiContext.Builder().apiKey("YOUR_API_KEY_HERE").build();

        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            if (results != null && results.length > 0) {
                double latitude = results[0].geometry.location.lat;
                double longitude = results[0].geometry.location.lng;

                geoData.setLat(latitude);
                geoData.setLng(longitude);
                // 주소별로 계산된 좌표 정보를 출력하거나 다른 작업 수행
                System.out.println("주소: " + address + ", 위도: " + latitude + ", 경도: " + longitude);
                return geoData;
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return null;
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
