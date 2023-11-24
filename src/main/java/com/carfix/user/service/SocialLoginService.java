package com.carfix.user.service;

import com.carfix.user.entity.UserEntity;

public interface SocialLoginService {
    public String getToken(String code) throws Exception;
    public UserEntity getUserInfo(String access_token) throws Exception;
}
