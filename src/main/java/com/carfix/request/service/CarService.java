package com.carfix.request.service;

import com.carfix.request.entity.CarEntity;
import com.carfix.request.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final CarRepository carRepository;

    // 차량 정보 저장
    public void addCarInfo(long reqidx, String model, String carNum, int year, String fuel, int km){
        carRepository.save(CarEntity.builder()
                .reqidx(reqidx)
                .model(model)
                .carnum(carNum)
                .year(year)
                .fuel(fuel)
                .km(km)
                .build());
    }

    // 요청정보랑 맞는 차량 정보 뿌리기
    public CarEntity getCarById(int reqidx){
        return carRepository.getCarById(reqidx);
    }
}
