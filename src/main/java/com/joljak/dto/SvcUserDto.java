package com.joljak.dto;

import com.joljak.base.dto.BaseDto;
import lombok.*;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class SvcUserDto extends BaseDto {

    private int userId;
    private String userMobile;
    private String userNm;
    private String userEmail;
    private String pwd;
    private String birthday;
    private String gender;
    private String authInfo;
    private String signupDt;
    private String leaveDt;
    private String leaveReason;
    private String recommMobile;
    private String os;
    private String token;
    private String refreshToken;
    private String osVer;
    private String mobileBrand;
    private String mobileModel;
    private String blockDt;
    private String blockReason;
    private Integer subsId;
    private String compCd;
    private String language;
    private String status;
    private int verNo;
    private String regDt;
    private String chkStatus;
    private String registrationId;
    private String lastLoginDt;
    private String personalInfoAgree;
    private String personalInfoAgreeDt;
    private String evtDcInfoAgree;
    private String evtDcInfoAgreeDt;
    private int profileFile;
    private String profileUrl;
    private String smsAgree;
    private String smsAgreeDt;
    private String pushAgree;
    private String pushAgreeDt;
    private String emailAgree;
    private String emailAgreeDt;
}
