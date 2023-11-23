package com.carfix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String join(){
        return "user/join";
    }
    
    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
}
