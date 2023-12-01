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
    public CompanyEntity findByCompanyIdx(int companyIdx);
    @Query(value = "select * from company "
            + "where approve = :approve ",
            nativeQuery = true)
    public List<CompanyEntity> findByApprove(int approve);

    @Query(value = "UPDATE company " +
            "SET distance = :distance " +
            "WHERE companyidx = :companyIdx",
            nativeQuery = true)
    @Modifying
    @Transactional
    public void updateByDistance(double distance, int companyIdx);

    @Query(value = "select companyIdx from company",
            nativeQuery = true)
    public List<Integer> findIdx();
}
