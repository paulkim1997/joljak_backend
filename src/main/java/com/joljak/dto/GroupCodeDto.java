package com.joljak.dto;

import java.util.List;

import lombok.*;
import com.joljak.base.dto.BaseDto;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class GroupCodeDto extends BaseDto {
    private String grpCd, grpCdNm, newCd;
    private Integer detailCount;
    private List<CodeDto> detailList;
    private List<String> grpCdList;

}