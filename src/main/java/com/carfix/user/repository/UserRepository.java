package com.carfix.repository;

import com.carfix.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 이미 가입된 회원인지 조회
    public UserEntity findByNickname(String nickname);
}
