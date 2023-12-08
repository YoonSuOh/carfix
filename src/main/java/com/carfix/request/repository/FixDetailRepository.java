package com.carfix.request.repository;

import com.carfix.request.entity.FixDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixDetailRepository extends JpaRepository<FixDetailEntity, Long> {
}
