package com.carfix.admin;

import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final CompanyService companyService;

    // 관리자 메인 화면
    @GetMapping("")
    public String getAdminMain(HttpSession session){
        log.info("세션값 : " + session.getAttribute("adminname"));
        return  "admin/main";
    }
    // 관리자 로그인 화면
    @GetMapping("/login")
    public String getAdminLogin(){
        return "admin/login";
    }
    @GetMapping("/companys")
    public String getCompanys(Model model){
        List<CompanyEntity> company = companyService.checkApprove();
        model.addAttribute("company", company);
        return "admin/companylist";
    }
}
