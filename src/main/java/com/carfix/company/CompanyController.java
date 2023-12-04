package com.carfix.company;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companys")
public class CompanyController {
    private final CompanyService companyService;
    // 등록 화면으로 이동
    @GetMapping("/create")
    public String getCompanyCreate(){
        return "company/create";
    }

    // 업체 정보 화면으로 이동
    @GetMapping("/{companyidx}")
    public String getCompanyInfo(@PathVariable("companyidx") int id, Model model){
        CompanyEntity company = companyService.findById(id);
        model.addAttribute("company", company);
        return "company/info";
    }
}
