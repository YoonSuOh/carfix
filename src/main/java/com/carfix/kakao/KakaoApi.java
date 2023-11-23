package com.carfix.kakao;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class KakaoApi {
    @Value("${kakao.client.id}")
    private String kakaoApiKey;

    @Value("${kakao.redirect.url}")
    private String kakaoRedirectUri;
}
