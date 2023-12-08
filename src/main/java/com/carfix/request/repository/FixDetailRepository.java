package com.carfix.request.repository;

import com.carfix.request.entity.FixDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FixDetailRepository extends JpaRepository<FixDetailEntity, Long> {
    @Query(value = "select * from fixdetail where :reqidx = reqidx", nativeQuery = true)
    FixDetailEntity findDetailById(int reqidx);
}
