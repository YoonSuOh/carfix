package com.carfix.request;

import com.carfix.request.entity.FixRequestEntity;
import com.carfix.request.service.CarService;
import com.carfix.request.service.FixDetailService;
import com.carfix.request.service.FixRequestService;
import com.carfix.request.service.PictureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/requests")
public class RequestRestController {
    private final CarService carService;
    private final FixDetailService fixDetailService;
    private final PictureService pictureService;
    private final FixRequestService fixRequestService;
    // 수리 요청 정보 저장 및 생성
    @PostMapping("/create")
    public Map<String, Object> addRequest(
            HttpSession session,
            @RequestParam("model") String model,
            @RequestParam("carNum") String carNum,
            @RequestParam("km") int km,
            @RequestParam("year") int year,
            @RequestParam("fuel") String fuel,
            @RequestParam("checkbox") List<String> checkbox,
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("other") String other){
        long useridx = (long) session.getAttribute("id");
        // 수리 요청 생성
        FixRequestEntity fixRequest = fixRequestService.addFixRequest(useridx, other);
        long reqidx = fixRequest.getReqidx();

        // 차량 정보 저장
        carService.addCarInfo(reqidx, model, carNum, year, fuel, km);
        String result = String.join(",", checkbox);

        // 수리 정보(파손 부위 저장)
        fixDetailService.addFixDetail(reqidx, result);
        // 사진 저장
        pictureService.addPictureEntity(reqidx, files);

        // 수리 요청 생성
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }
}
