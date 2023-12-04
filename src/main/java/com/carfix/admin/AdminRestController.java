package com.carfix.admin;

import com.carfix.admin.entity.AdminEntity;
import com.carfix.admin.service.AdminService;
import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final CompanyService companyService;
    private final AdminService adminService;

    // 업체 등록 요청
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

    // 관리자 로그인
    @PostMapping("/login")
    public Map<String, Object> adminLogin(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session){
        AdminEntity admin = adminService.login(id, password);
        Map<String, Object> map = new HashMap<>();
        if(admin != null){
            session.setAttribute("admin", admin.getAdminidx());
            session.setAttribute("adminname", admin.getId());

            map.put("code", 200);
        } else {
            map.put("code", 500);
            if(admin != null){
                map.put("errorMessage", "유효하지 않은 id입니다.");
            }
            if(id.equals(admin.getId())){
                map.put("errorMessage", "id가 일치하지 않습니다.");
            }
            if(password.equals(admin.getPassword())){
                map.put("errorMessage", "비밀번호가 일치하지 않습니다.");
            }
        }
        return map;
    }
}
