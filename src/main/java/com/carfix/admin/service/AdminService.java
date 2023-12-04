package com.carfix.admin.service;

import com.carfix.admin.entity.AdminEntity;
import com.carfix.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final AdminRepository adminRepository;

    // 로그인
    public AdminEntity login(String id, String password){
        return adminRepository.findByIdAndPassword(id, password);
    }
}
