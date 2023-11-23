package com.carfix.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder(toBuilder = true)    // 필드 세팅, toBuilder=true: 기존 객체에 일부 필드값만 변경 가능
@NoArgsConstructor  // 파라미터 없는 생성자
@AllArgsConstructor // 모든 필드 있는 생성자
@Table(name = "post")   // 테이블명과 클래스명이 동일하면 생략 가능
@Entity  // DB테이블 필수!
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


}
