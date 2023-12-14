package com.carfix.request.service;

import com.carfix.common.FileManager;
import com.carfix.request.entity.PictureEntity;
import com.carfix.request.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PictureService {
    private final PictureRepository pictureRepository;
    private final FileManager fileManager;

    // 파일 업로드
    public List<String> fileupload(List<MultipartFile> files){
        List<String> imagePaths = new ArrayList<>();

        // 이미지가 있으면 업로드 후 imagePath를 받아옴
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String imagePath = fileManager.saveFile(file);
                if (imagePath != null) {
                    imagePaths.add(imagePath);
                }
            }
        }
        return imagePaths;
    }
    // 사진 저장
    public void addPictureEntity(long reqidx, List<MultipartFile> files) {
        List<String> pictures = this.fileupload(files);
        for (String item : pictures) {
            pictureRepository.save(PictureEntity.builder()
                    .reqidx(reqidx)
                    .name(item)
                    .build());
        }
    }

    // 요청정보랑 맞는 대표 사진 뿌리기
    public PictureEntity getPictureByIdOne(int reqidx) {
        return pictureRepository.findPictureByIdOne(reqidx);
    }

    // 요청정보에 맞는 모든 사진 가져오기
    public List<PictureEntity> getPictureById(int reqidx){
        return pictureRepository.findPictureById(reqidx);
    }
}
