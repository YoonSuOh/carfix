package com.carfix.request.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Data
@Builder(toBuilder = true)    // 필드 세팅, toBuilder=true: 기존 객체에 일부 필드값만 변경 가능
@NoArgsConstructor  // 파라미터 없는 생성자
@AllArgsConstructor // 모든 필드 있는 생성자
@Table(name = "fixreq")   // 테이블명과 클래스명이 동일하면 생략 가능
@Entity  // DB테이블 필수!
public class FixRequestEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long reqidx;
    private Long useridx;
    private String other;
    private String status;
    @UpdateTimestamp    // 현재시간 디폴트값
    @Column(name="createdAt", updatable = false) // insert시 최초 시간만 넣고 시간 수정 안되게
    private LocalDateTime createdAt;

    @UpdateTimestamp   // 현재시간 디폴트값
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;
}
