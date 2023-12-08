package com.carfix.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/requests/")
public class RequestController {

    // 수리 요청 화면 띄우기
    @GetMapping("/create")
    public String getRequestCreate(){
        return "request/create";
    }

    // 수리 요청 성공 화면 띄우기
    @GetMapping("/create/success")
    public String getRequestSucess(){
        return "request/success";
    }
}
