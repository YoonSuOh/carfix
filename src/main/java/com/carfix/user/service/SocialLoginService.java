package com.carfix.service;

import com.carfix.entity.UserEntity;

public interface SocialLoginService {
    public String getToken(String code) throws Exception;
    public UserEntity getUserInfo(String access_token) throws Exception;
}
