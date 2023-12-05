package com.carfix.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/requests/")
public class RequestController {
    private final Map<String, Object> userInfo = new HashMap<>();

    // 수리 요청 화면 띄우기
    @GetMapping("/create")
    public String getRequestCreate(){
        return "request/create";
    }

    @GetMapping("/create/1")
    public String completeStep1(
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email){
          log.info(name);
          userInfo.put("name", name);
          userInfo.put("phoneNumber", phoneNumber);
          userInfo.put("email", email);
        return "forward:/requests/create/2";
    }

    @GetMapping("/create/2")
    public String getStep2(){
        log.info((String) userInfo.get("name"));
        return "request/create2";
    }
}
