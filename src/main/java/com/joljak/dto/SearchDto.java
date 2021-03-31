package com.joljak.dto;

import lombok.*;
import com.joljak.base.dto.BaseDto;

@Builder // builder를 사용할수 있게 합니다.
@Getter  // 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
public class SearchDto extends BaseDto {
    private int start = 0;
    private int length = 1000;

    private String searchValue;
    private String departure;
    private String departureInput;
    private String userSnsId;
    private String userTel;
    private String frDate;
    private String toDate;
    private String status;
    private String selectType;

    //Emp
    private String empId;
    private String empMobile;
    private String joinDate;
    private String retireDate;
    private String empNm;
    private String position;
    //SvcUser
    private int userId;
    private String userMobile;
    private String userNm;
    private String userEmail;
    private String birthday;
    private String gender;
    private String authInfo;
    private String recommMobile;
    private String os;
    private String osVer;
    private String mobileBrand;
    private String mobileModel;
    private String compCd;
    private String registrationId;
    private String language;
    private String blockReason;

    //Driver
    private int drvId;
    private String drvMobile;
    private String drvNm;
    private String drvPwd;
    private String newDrvPwd;
    private String licenseNo;
    private String engLevel;
    private String chnLevel;
    private String jpnLevel;
    private String residence;
    private String memo;
    private String ifNo;
    private String token;
    private String callStatus;
    private String commuteStatus;
    private String allocStatus;
    private String statusType;
    private String carStatus;

    //CarModel
    private int carModelId;
    private String brand;
    private String carClass;
    private String modelNm;
    private String modelSNm;
    private String isBest;

    //Car
    private int carId;
    private String carNo;
    private String exColor;
    private String inColor;
    private String cc;
    private String fuel;
    private int km;
    private String carType;

    //Subscriber
    private int subsId;
    private String subsMobile;
    private String pwd;
    private String subsNm;
    private String subsLicense;
    private String subsAddr1;
    private String subsAddr2;
    private String subsOption;
    private String startDate;
    private String endDate;
    private String subsFee;

    private String passengerNm;
    private String passengerTel;
    private String svcUserNm;
    private String departureShort;
    private String destinationShort;

    private int allocId;
    private String allocDt;
    private int fileId;

    private int callId;

    private int readerId;

    //Room
    private int roomNo;
    private String roomNm;
}



