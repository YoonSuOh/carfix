package com.carfix.testapi.service;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.repository.CompanyRepository;
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
    public void extractCoordinatesFromAddress() {
        CompanyEntity company = companyRepository.findByCompanyIdx(2);

        if (company != null) {
            GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyA63jdrc3pRrYKwBui11CgWXcrD0arWc7o").build();

            try {
                GeocodingResult[] results = GeocodingApi.geocode(context, company.getAddress()).await(); // Google Geocoding API로 좌표 추출
                if (results != null && results.length > 0) {
                    double latitude = results[0].geometry.location.lat;
                    double longitude = results[0].geometry.location.lng;
                    // 좌표 정보를 사용하여 필요한 작업 수행 (예: 좌표 DB에 저장)
                    System.out.println("회사명: " + company.getName() + ", 주소: " + company.getAddress() + ", 위도: " + latitude + ", 경도: " + longitude);
                }
            } catch (ApiException | InterruptedException | java.io.IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("해당하는 회사 정보가 없습니다.");
        }
    }
}
