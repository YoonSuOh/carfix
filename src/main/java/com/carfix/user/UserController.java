package com.carfix.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}