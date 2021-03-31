package com.joljak.dto;

import com.joljak.base.dto.BaseDto;
import lombok.*;

import java.math.BigDecimal;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class RoomDto extends BaseDto {
    private int roomNo     ;
    private String roomNm     ;
    private BigDecimal temp       ;
    private int hum        ;
    private int gas        ;
    private int dust       ;
    private int light      ;
    private String measureTime;

}