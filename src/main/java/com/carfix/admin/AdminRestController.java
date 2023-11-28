package com.carfix.admin;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final CompanyService companyService;
    @PutMapping("/companys/{id}")
    public Map<String, Object> editApprove(@PathVariable("id") long id){
        Map<String, Object> map = new HashMap<>();
        CompanyEntity company = companyService.editApprove(id);
        if(company.getApprove()==0){
            map.put("code", 500);
            map.put("errorMessage", "서버 오류가 발생했습니다.");
        }
        map.put("code", 200);
        return map;
    }
}
