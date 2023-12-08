package com.carfix.request.service;

import com.carfix.request.entity.FixRequestEntity;
import com.carfix.request.repository.FixRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixRequestService {
    private final FixRequestRepository fixRequestRepository;
    // 수리 요청 생성
    public FixRequestEntity addFixRequest(long user, String other){
        FixRequestEntity fixRequestEntity = fixRequestRepository.save(FixRequestEntity.builder()
            .useridx(user)
            .other(other)
            .build());
        return fixRequestEntity;
    }

    // 생성된 모든 수리 요청 가져오기
    public List<FixRequestEntity> getAllFixRequest(){
        return fixRequestRepository.findAll();
    }

    // 요청에 맞는 수리 요청 가져오기
    public FixRequestEntity getFixRequestById(int idx){
        return fixRequestRepository.findFixRequestByIdOne(idx);
    }
}
