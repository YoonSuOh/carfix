package com.carfix.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companys")
public class CompanyController {
    // 등록 화면으로 이동
    @GetMapping("/create")
    public String getCompanyCreate(){
        return "company/create";
    }

    // 업체 정보 화면으로 이동
    @GetMapping("/info")
    public String getCompanyInfo(){
        return "map";
    }
}
