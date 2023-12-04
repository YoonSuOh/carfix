package com.carfix.company.service;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    // 업체 등록 요청 생성
    public CompanyEntity addCompany(long id, String company, String ceo, String tel, String address, String introduce){
        CompanyEntity companyInfo = companyRepository.save(CompanyEntity.builder()
            .id(id)
            .name(company)
            .ceo(ceo)
            .tel(tel)
            .address(address)
            .introduce(introduce)
            .build());
        return companyInfo;
    }

    // 업체 목록 조회
    public List<CompanyEntity> checkApprove(){
        List<CompanyEntity> approve = companyRepository.findAll();
        return approve;
    }

    // 업체 등록 승인 / 취소
    public CompanyEntity editApprove(long id){
        CompanyEntity companyInfo = companyRepository.findById((int) id).orElse(null);
        if(companyInfo.getApprove() == 0){
            companyInfo = companyInfo.toBuilder()
                    .approve(1)
                    .updatedAt(LocalDateTime.now())
                    .build();
        } else {
            companyInfo = companyInfo.toBuilder()
                    .approve(0)
                    .updatedAt(LocalDateTime.now())
                    .build();
        }
        companyRepository.save(companyInfo);
        return companyInfo;
    }

    // 승인된 업체만 보여주기
    public List<CompanyEntity> findByApprove(){
        return companyRepository.findByApprove(1);
    }

    // 업체 정보 가져오기
    public CompanyEntity findById(int id){return companyRepository.findByCompanyIdx(id);}
}
