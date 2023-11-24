package com.carfix.user.service;

import com.carfix.user.entity.UserEntity;
import com.carfix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaTestService {
    private final UserRepository userRepository;

    public void jpaInsertTest(){
        userRepository.save(UserEntity.builder()
                .id(2342342L)
                .nickname("test")
                .email("test")
                .build());
    }
}
