package com.carfix.request;

import com.carfix.request.dto.FixRequestDTO;
import com.carfix.request.service.FixRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/requests/")
public class RequestController {
    private final FixRequestService requestService;
    // 수리 요청 화면 띄우기
    @GetMapping("/create")
    public String getRequestCreate(){
        return "request/create";
    }

    // 수리 요청 성공 화면 띄우기
    @GetMapping("/create/success")
    public String getRequestSucess(){
        return "request/success";
    }

    @GetMapping("/{reqidx}")
    public String getRequest(@PathVariable long reqidx, Model model){
        FixRequestDTO requestDTO = requestService.getRequest(reqidx);
        model.addAttribute("request", requestDTO);
        return "request/request";
    }
}
