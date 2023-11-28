package com.carfix.company;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companys")
public class CompanyRestController {

    private final CompanyService companyService;

    @PostMapping("")
    public Map<String, Object> createCompany(
            @RequestParam("id") long id,
            @RequestParam("company") String company,
            @RequestParam("ceo") String ceo,
            @RequestParam("tel") String tel,
            @RequestParam("address") String address,
            @RequestParam("introduce") String introduce){
        Map<String, Object> map = new HashMap<>();
        CompanyEntity result = companyService.addCompany(id, company, ceo, tel, address, introduce);
        if(result == null){
            map.put("code", 500);
            map.put("errorMessage", "서버 오류가 발생했습니다.");
        }
        map.put("code", 200);
        return map;
    }
}
