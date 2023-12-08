package com.carfix.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixRequestDTO {
    private String user;
    private long reqidx;
    private String image;
    private String carName;
    private String fixdetail;
}
