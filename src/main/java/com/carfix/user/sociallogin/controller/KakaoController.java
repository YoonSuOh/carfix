package com.carfix.user.sociallogin.controller;

import com.carfix.user.entity.UserEntity;
import com.carfix.user.sociallogin.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    // 카카오 로그인을 위한 코드 생성
    @GetMapping("/kakaoTerms")
    public String kakaoConnect() {

        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + "f7cb1db7fa96462cab3a07f62034838d");
        url.append("&redirect_uri=http://localhost:8080/kakao/callback");
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }

    // 카카오 로그인 처리
    @RequestMapping(value = "/callback")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session) throws Exception {
        System.out.println("code: " + code);
        String access_token = kakaoService.getToken(code);//code로 토큰 받음
        System.out.println("access_token : " + access_token);
        UserEntity user = kakaoService.getUserInfo(access_token);

        log.info("id : " + user.getId());

        session.setAttribute("token", access_token);
        session.setAttribute("id", user.getId());
        session.setAttribute("nickname", user.getNickname());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("phoneNumber", user.getPhoneNumber());

        return "redirect:/";
    }

    // 카카오 로그아웃
    @GetMapping("/kakaologout")
    public String access(HttpSession session) throws IOException {
        session.invalidate();
        return "redirect:https://kauth.kakao.com/oauth/logout?client_id=f7cb1db7fa96462cab3a07f62034838d&logout_redirect_uri=http://localhost:8080/";
    }
}
