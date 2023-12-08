package com.carfix.user.repository;

import com.carfix.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 이미 가입된 회원인지 조회
    public UserEntity findByNickname(String nickname);

    // 수리요청에 따른 고속도로 고슴도치 조회
    @Query(value = "select * from user where :id = id", nativeQuery = true)
    UserEntity findUserById(long id);
}
