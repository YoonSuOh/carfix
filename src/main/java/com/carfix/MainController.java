package com.carfix;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CompanyService companyService;
    @RequestMapping("/")
    public String main(Model model){
        List<CompanyEntity> list = companyService.findByApprove();
        model.addAttribute("list", list);
        return "index";
    }
}
