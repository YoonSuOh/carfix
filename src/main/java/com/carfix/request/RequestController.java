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
    private final Map<String, Object> carInfo = new HashMap<>();
    private final Map<String, Object> fixInfo = new HashMap<>();

    // 수리 요청 화면 띄우기
    @GetMapping("/create")
    public String getRequestCreate(){
        return "request/create";
    }

    // 차량정보 입력 창으로 넘기기
    @GetMapping("/create/step0")
    public String completeStep1(
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email){
          userInfo.put("name", name);
          userInfo.put("phoneNumber", phoneNumber);
          userInfo.put("email", email);
        return "forward:/requests/create/step1";
    }


    // 차량정보 입력 창 띄우기
    @GetMapping("/create/step1")
    public String getStep2(){
        return "request/create2";
    }


    // 파손부위 등록 창으로 넘기기
    @GetMapping("/create/step2")
    public String completeStep2(
            @RequestParam("model") String model,
            @RequestParam("carnum") String carnum,
            @RequestParam("year") int year,
            @RequestParam("fuel") String fuel){
        carInfo.put("model", model);
        carInfo.put("carnum", carnum);
        carInfo.put("year", year);
        carInfo.put("fuel", fuel);
        return "forward:/requests/create/step3";
    }


    // 파손부위 등록 창 띄우기
    @GetMapping("/create/step3")
    public String getStep3(){
        return "request/create3";
    }
}
