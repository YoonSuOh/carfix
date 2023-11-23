package com.carfix.controller.user;

import com.carfix.kakao.KakaoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final KakaoApi kakaoApi;

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String join(){
        return "user/join";
    }
    
    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("kakaoApiKey", kakaoApi.getKakaoApiKey());
        model.addAttribute("redirectUri", kakaoApi.getKakaoRedirectUri());
        return "user/login";
    }
}
