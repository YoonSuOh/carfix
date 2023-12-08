package com.carfix.request.service;

import com.carfix.request.entity.FixRequestEntity;
import com.carfix.request.repository.FixRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
