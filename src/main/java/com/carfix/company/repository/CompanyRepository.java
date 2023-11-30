package com.carfix.company.repository;

import com.carfix.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    @Query(value = "select * from company "
            + "where companyIdx = :companyIdx ",
            nativeQuery = true)
    public CompanyEntity findByCompanyIdx(int companyIdx);
    @Query(value = "select * from company "
            + "where approve = :approve ",
            nativeQuery = true)
    public List<CompanyEntity> findByApprove(int approve);
}
