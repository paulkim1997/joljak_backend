package com.joljak.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class BaseInfoDto {

    private int baseInfoId;
    private String baseInfoNm;
    private String baseInfoContents;
    private BigDecimal amt;
    private String baseInfoTp;
    private String status;
    private int verNo;
    private String regDt;

}
