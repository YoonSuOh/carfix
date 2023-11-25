package com.carfix.user;

import com.carfix.user.entity.UserEntity;
import com.carfix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public int modifyUserInfo(long id, String phoneNumber){
        UserEntity user = userRepository.findById(id).orElse(null);
        user = user.toBuilder() // 기존 내용은 그대로
                .phoneNumber(phoneNumber)
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user); // 데이터 있으면 수정

        if(user != null){
            return 1;
        } else {
            return 0;
        }
    }
}
