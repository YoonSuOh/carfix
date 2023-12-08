package com.carfix;

import com.carfix.common.GeoData;
import com.carfix.company.entity.CompanyEntity;
import com.carfix.company.service.CompanyService;
import com.carfix.map.service.GoogleMapApiService;
import com.carfix.request.dto.FixRequestDTO;
import com.carfix.request.entity.CarEntity;
import com.carfix.request.entity.FixDetailEntity;
import com.carfix.request.entity.FixRequestEntity;
import com.carfix.request.entity.PictureEntity;
import com.carfix.request.service.CarService;
import com.carfix.request.service.FixDetailService;
import com.carfix.request.service.FixRequestService;
import com.carfix.request.service.PictureService;
import com.carfix.user.entity.UserEntity;
import com.carfix.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final CompanyService companyService;
    private final GoogleMapApiService googleMapApiService;
    private final CarService carService;
    private final FixDetailService fixDetailService;
    private final PictureService pictureService;
    private final FixRequestService fixRequestService;
    private final UserService userService;

    @GetMapping("/")
    public String main(Model model, HttpSession session){
        try{
            if((int) session.getAttribute("perm") != 2){
                List<CompanyEntity> list = companyService.findByApprove();
                model.addAttribute("list", list);
            } else {
                List<FixRequestEntity> reqlist = fixRequestService.getAllFixRequest();
                List<FixRequestDTO> request = new ArrayList<>();

                for(int i=1;i<=reqlist.size();i++){
                    FixRequestDTO fixRequestDTO = new FixRequestDTO();

                    CarEntity carEntity = carService.getCarById(i);
                    FixRequestEntity fixRequestEntity = fixRequestService.getFixRequestById(i);
                    UserEntity userEntity = userService.getUserById(fixRequestEntity.getUseridx());
                    FixDetailEntity detailEntity = fixDetailService.getFixDetailById(i);
                    PictureEntity pictureEntity = pictureService.getPictureByIdOne(i);

                    fixRequestDTO.setUser(userEntity.getNickname());
                    fixRequestDTO.setReqidx(i);
                    fixRequestDTO.setCarName(carEntity.getModel());
                    fixRequestDTO.setImage(pictureEntity.getName());
                    fixRequestDTO.setFixdetail(detailEntity.getName());

                    request.add(fixRequestDTO);
                }
                model.addAttribute("list", request);
            }
        } catch(NullPointerException e){
            List<CompanyEntity> list = companyService.findByApprove();
            model.addAttribute("list", list);
        }
        return "index";
    }

    @PostMapping("/")
    public String model(@RequestBody GeoData geoData){
        double lat2 = geoData.getLat();
        double lng2 = geoData.getLng();
        googleMapApiService.extractCoordinatesFromAddress(lat2, lng2);
        return "index";
    }
}
