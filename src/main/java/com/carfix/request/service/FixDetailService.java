package com.carfix.request.service;

import com.carfix.request.entity.FixDetailEntity;
import com.carfix.request.repository.FixDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixDetailService {
    private final FixDetailRepository fixDetailRepository;
    // 파손 정보 저장
    public void addFixDetail(long reqidx, String name){
        FixDetailEntity fixDetailInfo = fixDetailRepository.save(FixDetailEntity.builder()
                .reqidx(reqidx)
                .name(name)
                .build());
    }
}
