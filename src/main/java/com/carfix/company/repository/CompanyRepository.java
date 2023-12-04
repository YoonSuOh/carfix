package com.carfix.company.repository;

import com.carfix.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    @Query(value = "select * from company "
            + "where companyIdx = :companyIdx ",
            nativeQuery = true)
    CompanyEntity findByCompanyIdx(int companyIdx);
    @Query(value = "select * from company "
            + "where approve = :approve " +
            "order by distance asc",
            nativeQuery = true)
    List<CompanyEntity> findByApprove(int approve);

    @Query(value = "UPDATE company " +
            "SET distance = :distance " +
            "WHERE companyidx = :companyIdx",
            nativeQuery = true)
    @Modifying
    @Transactional
    void updateByDistance(double distance, int companyIdx);

    @Query(value = "select companyIdx from company",
            nativeQuery = true)
    List<Integer> findIdx();
}
