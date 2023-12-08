package com.carfix.request.repository;

import com.carfix.request.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    @Query(value = "select * from car where :reqidx = reqidx", nativeQuery = true)
    CarEntity getCarById(int reqidx);
}
