package com.joljak.dto;

import lombok.*;
import com.joljak.base.dto.BaseDto;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class AuthDto extends BaseDto {
    private String compCd;
    private String empId;
    private String empNm;
    private String pwd;
    private String position;
    private String initUrl;
    private String status;
    private String authority;
    private String regDt;
    private String drvNm;
    private String drvMobile;
    private String userId;
    private String userNm;
    private String userMobile;
    private String userEmail;
    private int verNo;
    private String token;
    private String drvId;

}