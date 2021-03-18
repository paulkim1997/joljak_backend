package com.joljak.base.constant;

import com.google.api.pathtemplate.ValidationException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Constant {

    public static final String HEADER_KEY = "Authorization";
    public static final String ROLE_DRIVER = "DRIVER";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    public static final String NORMAL = "정상";  //정상
    public static final String DELETE = "삭제";  //삭제
    public static final String SUCCESS = "성공";  //성공
    public static final String FAIL = "실패";  //실패
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String REST = "R";  //휴식

    public static final String ON = "ON";
    public static final String OFF = "OFF";

    public static final String S10_DEPARTURE = "10"; //출발지이동
    public static final String S20_DEPARTURE_ARRIVE = "20"; //출발지도착
    public static final String S30_BOARDING = "30"; //고객탑승(목적지도착)
    public static final String S40_CHARGE_REQ = "40"; //결제요청
    public static final String S40_CHARGE_SUCCESS = "40S"; //결제성공
    public static final String S40_CHARGE_FAIL = "40F"; //결제실패
    public static final String S50_END = "50"; //운행완료
    public static final String S50_NOT_BOARDING = "50N"; //고객미탑승(운행완료)
    public static final String S55_CANCEL = "55"; //운행취소

    public static final String DrvWorkStatus = "DRVWORK"; //기사상태변경 - 업무시작, 종료
    public static final String AllocStatus = "ALLOCOP"; //배차운행상태변경 - 출발지이동,도착,탑승/미탑승,목적지도착,결제요청,운행완료
    public static final String DrvCallStatus = "DRVCALL";

    public static final Integer DrvInRadiusAKm = 1; //반경내 기사 검색 - 1Km
    public static final Integer DrvInRadiusBKm = 3; //반경내 기사 검색 - 3Km
    public static final Integer DrvInRadiusCKm = 5; //반경내 기사 검색 - 5Km

    public static final Integer CallNonConfirmSec = 15; //기사가 콜확인을 위한 시간 15

    public static final Integer ResvCanAfterTime = 3; //예약가능시간

    public static final String allocDirectory = "alloc";

    public static final String SMS_SEND_PHONENO = "01034813959";


    public static String getCurrentDateString(String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        Calendar cal = Calendar.getInstance();
        String curDate = format.format(cal.getTime());
        return curDate;
    }

    // 이게 왜 필요하냐면, N일의 입차 상태를 체크할 때 인터내셔널택시의 업무기준이 AM 2시 이므로 현재 시간이 AM 00:00 ~ AM 01:59 까지는 N-1의 날짜로 조회를 해야 한다.
    public static String getCurDateFrom2AmString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 2) { // 현재 오전 2시 이전이면 이전 날짜를 기준으로 검색시킨다.
            cal.add(Calendar.DATE, -1);
        }
        String curDate = format.format(cal.getTime());
        return curDate;
    }

    /**
     * 콜멈춤 사유
     */
    public enum CallStopReason {
        휴식("휴식"), 긴급("긴급"), 기타("기타");

        private String name;

        CallStopReason(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 콜취소 사유
     */
    public enum CallCancelReason {
        OTHER("다른 운송수단을 선택합니다."), PERSONAL("긴급한 사정으로 취소합니다."), DRIVER("기사분에게 취소요청을 받았습니다."), CS("고객센터에서 취소요청을 받았습니다.");

        private String name;

        CallCancelReason(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 파이어베이스 AUTH 사용자 타입(우선은 3번만 사용됨)
     */
    public enum FIREBASE_AUTH_USER_TYPE {
        USER(1, "앱사용자"), DRIVER(2, "드라이버"), ADMIN(3, "관리자");

        private int value;
        private String name;

        FIREBASE_AUTH_USER_TYPE(int v, String name) {
            this.value = v;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static FIREBASE_AUTH_USER_TYPE get(int v) {
            switch (v) {
                case 1:
                    return FIREBASE_AUTH_USER_TYPE.USER;
                case 2:
                    return FIREBASE_AUTH_USER_TYPE.DRIVER;
                case 3:
                    return FIREBASE_AUTH_USER_TYPE.ADMIN;
                default:
                    throw new RuntimeException("FIREBASE_AUTH_USER_TYPE : " + v);
            }
        }
    }

    /**
     * Push Type
     */
    public enum PUSH_TYPE {
        PUSH_01(1, "배차알림"), PUSH_02(2, "출발알림"), PUSH_03(3, "도착1시간전알림"),
        PUSH_04(4, "도착5분전알림"), PUSH_05(5, "배차변경"), PUSH_06(6, "운행종료"),
        PUSH_07(7, "예약변경"), PUSH_08(8, "추가결제-요청"), PUSH_09(9, "일반메세지"),
        PUSH_10(10, "예약취소"), PUSH_11(11, "실시간 매칭완료"), PUSH_12(12, "실시간 매칭거절"),
        PUSH_13(13, "결제콜백"), PUSH_14(14, "실시간 도착 1분전"), PUSH_15(15, "배차 미확정 예약 취소");

        private int value;
        private String name;

        PUSH_TYPE(int v, String name) {
            this.value = v;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static PUSH_TYPE get(int v) {
            switch (v) {
                case 1:
                    return PUSH_TYPE.PUSH_01;
                case 2:
                    return PUSH_TYPE.PUSH_02;
                case 3:
                    return PUSH_TYPE.PUSH_03;
                case 4:
                    return PUSH_TYPE.PUSH_04;
                case 5:
                    return PUSH_TYPE.PUSH_05;
                case 6:
                    return PUSH_TYPE.PUSH_06;
                case 7:
                    return PUSH_TYPE.PUSH_07;
                case 8:
                    return PUSH_TYPE.PUSH_08;
                case 9:
                    return PUSH_TYPE.PUSH_09;
                case 10:
                    return PUSH_TYPE.PUSH_10;
                case 11:
                    return PUSH_TYPE.PUSH_11;
                case 12:
                    return PUSH_TYPE.PUSH_12;
                case 13:
                    return PUSH_TYPE.PUSH_13;
                case 14:
                    return PUSH_TYPE.PUSH_14;
                case 15:
                    return PUSH_TYPE.PUSH_15;
                default:
                    throw new ValidationException("PUSH_TYPE : " + v);
            }
        }

        public static PUSH_TYPE get(String v) {
            return PUSH_TYPE.get(Integer.parseInt(v));
        }
    }

    /**
     * FCM 요청 타입
     */
    public enum REQUEST_PUSH_TYPE {
        PUSH_IDS,
        TOPIC,
        ALL_USER,
        ANDROID_ALL,
        IOS_ALL
    }

    /**
     * 탈퇴 사유
     */
    public enum LeaveReason {
        LR1("잦은 장애와 속도가 느립니다."), LR2("데이터 및 배터리 사용이 많습니다."),
        LR3("차량호출이 되지 않습니다."), LR4("사용법이 어렵습니다."), LR5("개인정보 유출이 염려됩니다.");

        private String name;

        LeaveReason(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 운전면허 종류
     */
    public enum LicenseType {
        L1_NORMAL("1종보통"), L1_LARGE("1종대형"), L1_SMALL_TRUCK("1종소형견인차"), L1_IMPAIRED("1종장애인"), L2_IMPAIRED("2종장애인"), L2_AUTO("2종자동"), L2_MANUAL("2종수동"), L2_SMALL("2종소형"), L2_BIKE("2종원동기");

        private String name;

        LicenseType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
