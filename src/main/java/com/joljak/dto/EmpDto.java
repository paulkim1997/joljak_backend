package com.joljak.dto;

import lombok.*;
import com.joljak.base.dto.BaseDto;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class EmpDto extends BaseDto {

    private String empId;
    private String empMobile;
    private String empNm;
    private String pwd;
    private String position;
    private String authority;
    private String joinDate;
    private String retireDate;
    private String initUrl;
    private int listCnt;
    private String compCd;
    private String status;
    private int verNo;
    private String regDt;
    private String chkStatus;
    private String lockYn;
    private String token;
    private String refreshToken;

}
