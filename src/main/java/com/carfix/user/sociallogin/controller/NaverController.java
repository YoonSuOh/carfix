package com.carfix.user.sociallogin.controller;

import com.carfix.user.sociallogin.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/naver")
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/naverTerms")
    public String kakaoConnect() {

        StringBuffer url = new StringBuffer();
        url.append("https://nid.naver.com/oauth2.0/authorize?");
        url.append("client_id=" + "xkAMF6O_N6acMoWf0HK5");
        url.append("&redirect_uri=http://localhost:8080/naver/callback");
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }

    @RequestMapping(value = "/callback")
    public String naverLogin(@RequestParam("code") String code, HttpSession session) throws Exception {
        System.out.println("code: " + code);
        String access_token = naverService.getToken(code);//code로 토큰 받음
        System.out.println("access_token : " + access_token);
        naverService.getUserInfo(access_token);

        return "redirect:/";
    }

    @GetMapping("/naverlogout")
    public String access(HttpSession session) throws IOException {
        String client_id = "xkAMF6O_N6acMoWf0HK5";
        String redirect_url = "http://localhost:8080/";
        return "redirect:https://nid.naver.com/oauth2.0/logout?client_id=xkAMF6O_N6acMoWf0HK5&logout_redirect_uri=http://localhost:8080/";
    }
}
