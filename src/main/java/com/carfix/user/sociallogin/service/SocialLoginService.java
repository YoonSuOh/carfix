package com.carfix.user.sociallogin.service;

import com.carfix.user.entity.UserEntity;

public interface SocialLoginService {
    public String getToken(String code) throws Exception;
    public UserEntity getUserInfo(String access_token) throws Exception;
}
