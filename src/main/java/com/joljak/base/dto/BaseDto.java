package com.joljak.base.dto;

import lombok.*;

//@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class BaseDto {

    private String status;
    private String regId;
    private String modId;
    private int rowNo;
    private int verNo;
    private String regDt;
    private String modDt;
    private String compCd;

}