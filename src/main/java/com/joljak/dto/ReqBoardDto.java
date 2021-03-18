package com.joljak.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Positive;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class ReqBoardDto {

    @Positive(message = "boardId는 필수값입니다.")
    @ApiModelProperty(notes = "boardId(상세조회할때만)", example = "12", position = 1)
    private int boardId;
}
