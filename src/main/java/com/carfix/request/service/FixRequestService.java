package com.carfix.request.service;

import com.carfix.request.dto.FixRequestDTO;
import com.carfix.request.entity.CarEntity;
import com.carfix.request.entity.FixDetailEntity;
import com.carfix.request.entity.FixRequestEntity;
import com.carfix.request.entity.PictureEntity;
import com.carfix.request.repository.FixRequestRepository;
import com.carfix.user.entity.UserEntity;
import com.carfix.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixRequestService {
    private final FixRequestRepository fixRequestRepository;
    private final CarService carService;
    private final FixDetailService fixDetailService;
    private final PictureService pictureService;
    private final UserService userService;

    // 수리 요청 생성
    public FixRequestEntity addFixRequest(long user, String other){
        FixRequestEntity fixRequestEntity = fixRequestRepository.save(FixRequestEntity.builder()
            .useridx(user)
            .other(other)
            .build());
        return fixRequestEntity;
    }

    // 생성된 모든 수리 요청 가져오기
    public List<FixRequestEntity> getAllFixRequest(){
        return fixRequestRepository.findAll();
    }

    // 요청에 맞는 수리 요청 가져오기
    public FixRequestEntity getFixRequestById(int idx){
        return fixRequestRepository.findFixRequestByIdOne(idx);
    }

    // 수리 요청 목록 뷰 만들기
    public List<FixRequestDTO> createRequestView(){
        List<FixRequestEntity> reqlist = this.getAllFixRequest();
        List<FixRequestDTO> request = new ArrayList<>();

        for(int i=1;i<=reqlist.size();i++){
            FixRequestDTO fixRequestDTO = new FixRequestDTO();

            CarEntity carEntity = carService.getCarById(i);
            FixRequestEntity fixRequestEntity = this.getFixRequestById(i);
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
        return request;
    }

    // 수리 요청 가져오기
    public FixRequestDTO getRequest(long id){
        FixRequestDTO fixRequestDTO = new FixRequestDTO();
        CarEntity carEntity = carService.getCarById((int) id);
        FixRequestEntity fixRequestEntity = this.getFixRequestById((int) id);
        UserEntity userEntity = userService.getUserById(fixRequestEntity.getUseridx());
        FixDetailEntity detailEntity = fixDetailService.getFixDetailById((int) id);
        List<PictureEntity> pictureEntityList = pictureService.getPictureById((int) id);
        List<String> carImage = new ArrayList<>();

        for(int i=0;i<pictureEntityList.size();i++){
            carImage.add(pictureEntityList.get(i).getName());
        }

        String carImages = String.join(",", carImage);

        fixRequestDTO.setUser(userEntity.getNickname());
        fixRequestDTO.setReqidx(id);
        fixRequestDTO.setCarName(carEntity.getModel());
        fixRequestDTO.setCarNum(carEntity.getCarnum());
        fixRequestDTO.setKm(carEntity.getKm());
        fixRequestDTO.setCarYear(carEntity.getYear());
        fixRequestDTO.setCarFuel(carEntity.getFuel());
        fixRequestDTO.setFixdetail(detailEntity.getName());
        for(int i=0;i<pictureEntityList.size();i++){
            fixRequestDTO.setCarImages(carImages);
        }
        fixRequestDTO.setOthers(fixRequestEntity.getOther());

        log.info("차량 사진 1 : " + fixRequestDTO.getCarImages());
        return fixRequestDTO;
    }
}
