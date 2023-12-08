package com.carfix.request.repository;

import com.carfix.request.entity.FixRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixRequestRepository extends JpaRepository<FixRequestEntity, Long> {
}
