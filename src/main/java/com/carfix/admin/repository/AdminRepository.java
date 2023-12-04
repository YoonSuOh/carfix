package com.carfix.admin.repository;

import com.carfix.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    @Query(value = "select * from admin where id = :id and password = :password", nativeQuery = true)
    AdminEntity findByIdAndPassword(String id, String password);
}
