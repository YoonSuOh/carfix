package com.carfix.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    // 회원 정보 수정
    @PutMapping("/{id}")
    public Map<String, Object> modifyUserInfo(@PathVariable long id, @RequestParam("phoneNumber") String phoneNumber, HttpSession session){
        int resultcode = userService.modifyUserInfo(id, phoneNumber);
        Map<String, Object> result = new HashMap<>();

        if(resultcode == 0){
            result.put("code", 500);
            result.put("errorMessage", "수정 중 서버 오류가 발생했습니다.");
            return result;
        }

        session.setAttribute("phoneNumber", phoneNumber);
        result.put("code", 200);
        return result;
    }
}
