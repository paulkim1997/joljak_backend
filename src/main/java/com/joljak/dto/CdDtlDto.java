package com.joljak.dto;

import lombok.*;
import com.joljak.base.dto.BaseDto;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class CdDtlDto extends BaseDto {

    private String grpCd;
    private String dtlCd;
    private String dtlCdNm;
    private String val1;
    private String val2;
    private String val3;
    private String val4;
    private Integer dtlOrder;
    private String status;
    private int verNo;

}