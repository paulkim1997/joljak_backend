package com.joljak.base.error;

public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(false, 400, "L-10000", "잘못된 파라메터 값"),
    INVALID_INPUT_NAME(false, 400, "L-10010", "잘못된 파라메터 이름"),
    INVALID_INPUT_TYPE(false, 400, "L-10020", "잘못된 파라메터 타입"),

    JWT_AUTH_ERROR(false, 403, "L-10030", "액세스 권한이 없습니다. 다시 로그인하세요"),
    HANDLE_ACCESS_DENIED(false, 403, "L-10040", "허가되지 않은 요청"),
    EXPIRED_TOKEN_ERROR(false, 401, "L-10050", "액세스 토큰이 존재하지 않거나 유효기간이 지났습니다."),
    ROW_NOT_FOUND_ERROR(true, 200, "L-10060", "해당 데이터가 없습니다."),
    METHOD_NOT_ALLOWED(false, 400, "L-10070", "잘못된 HTTP 메소드"),
    INTERNAL_SERVER_ERROR(false, 500, "L-10080", "서버 에러! 관리자에게 문의하여주세요"),
    ENTITY_NOT_FOUND(true, 200, "L-10090", "해당항목이 없습니다"),
    SQL_SYNTAX_ERROR(false, 500, "L-10100", "SQL Syntax Error이 발생하였습니다"),
    EMPTY_FILE_ERROR(true, 200, "L-10110", "파일이 존재하지 않습니다."),
    SAVE_FILE_ERROR(false, 500, "L-10120", "파일이 저장중 에러가 났습니다."),
    EMAIL_DUPLICATION(true, 200, "L-10130", "중복된 이메일 입니다."),
    LOGIN_INPUT_INVALID(true, 200, "L-10140", "로그인 정보가 유효하지 않습니다. 입력정보를 확인해주세요"),
    DRIVER_NOT_FOUND(true, 200, "L-10150", "해당 기사정보가 없습니다"),
    DRIVER_COMMUTE_STATUS_ERROR(true, 200, "L-10160", "이미 업무시작이거나 종료상태이므로 상태를 변경할 수 없습니다"),
    DRIVER_CALL_STATUS_ERROR(true, 200, "L-10170", "업무종료상태이거나 이미 해당상태이므로 상태를 변경할 수 없습니다"),
    DRIVER_OP_STATUS_ERROR(true, 200, "L-10180", "기사님이 이미 운행상태이거나 퇴근상태라 상태를 변경할 수 없습니다"),
    DUP_CHK_ERROR(true, 200, "L-10190", "중복된 정보입니다(%1)"),
    ALLOC_STATUS_ERROR(true, 200, "L-10200", "해당 상태의 배차가 없어 배차상태를 변경할 수 없습니다"),
    USER_NOT_FOUND(true, 200, "L-10210", "해당 사용자 정보가 없습니다"),
    CANNOT_LOGOUT(true, 200, "L-10220", "로그아웃할 수 없습니다."),
    AUTH_NO_CHECK_ERROR(true, 200, "L-10230", "인증번호가 일치하지 않습니다."),
    AUTH_NO_GENERATE_ERROR(true, 200, "L-10235", "인증번호 생성에 실패했습니다."),
    LEAVE_CHK_ERROR(true, 200, "L-10240", "탈퇴 후 30일 이내에는 재가입이 불가합니다."),
    CANNOT_LEAVE(true, 200, "L-10250", "현재 구독중인 상품이 있습니다.\n자세한 사항은 고객센터로 연락바랍니다."),
    NOT_MATCHED_PWD(true, 200, "L-10260", "기존 비밀번호가 일치하지 않습니다."),
    SAME_PWD(true, 200, "L-10270", "기존과 동일한 비밀번호는 사용할 수 없습니다."),
    CANNOT_LEAVE_BECAUSE_ALLOC(true, 200, "L-10280", "남아있는 배차정보가 있습니다. \n자세한 사항은 고객센터로 연락해 주시기 바랍니다."),

    //필수
    REQUIRED_MANDATORY(false, 400, "R-20000", "%1은(는) 필수항목 입니다"),
    REQUIRED_CALL_STOP(true, 200, "R-20031", "콜정지(휴식/긴급/기타) 정보는 필수입니다"),
    REQUIRED_FILE(true, 200, "R-20032", "업로드할 파일이 없습니다."),
    REQUIRED_USER_ID(true, 200, "R-20033", "userId 값이 누락되었습니다."),
    REQUIRED_ALLOC_ID(true, 200, "R-20034", "allocId 값이 누락되었습니다."),

    //배차
    CHECK_ALLOC_TYPE(true, 200, "A-10000", "차량인수가 필요하나 인수배차가 없습니다. 잠시 후 다시 조회하거나 배차상태를 문의하세요"),
    FAIL_ALLOC(true, 200, "A-20000", "현재 배차가능한 차량이 주변에 업습니다. 잠시 후 다시 호출하여 주시기 바랍니다"),
    FAIL_RESV_ALLOC(true, 200, "A-20000", "현재 예약가능한 차량이 업습니다. 예약시간을 변경하시거나 예약일자를 변경하여 주시기 바랍니다."),

    /**
     * 데이터 검증 에러(E)
     */
    //예약
    CHECK_RESV_DATE(true, 200, "E-20010", "%1은(는) %2시간 이후부터 가능합니다."),
    CHECK_CALL_STOP(true, 200, "E-20020", "%1에 포함되지 않는 값이 입력되었습니다."),

    SAVE_ERROR(true, 200, "R-30000", "%1이(가) 저장되지 않았습니다"),
    UPDATE_ERROR(true, 200, "R-30010", "%1이(가) 수정되지 않았습니다"),
    DELETE_ERROR(true, 200, "R-30020", "%1이(가) 삭제되지 않았습니다"),

    //카드등록 및 결제
    PASSENGER_CARD_REGIST_ERROR(true, 200, "L-10210", "카드등록실패 => %1"),
    EXIST_CARD_REGIST_ERROR(true, 200, "L-10215", "기존에 등록된 카드입니다."),
    PAYMENT_ERROR(true, 200, "L-10220", "결제실패 => %1"),
    PAYMENT_CANCEL_ERROR(true, 200, "L-10230", "결제취소실패 => %1"),
    DEL_SUBSCRIBE_CARD_ERROR(true, 200, "L-10240", "구독 중인 상품의 결제카드입니다. 삭제불가합니다."),

    //메일발송실패
    SEND_MAIL_FAIL(false, 500, "L-10300", "메일발송에 실패했습니다."),
    //쿠폰등록
    NOT_VALID_COUPON(true, 200, "L-10400", "유효하지 않은 쿠폰번호입니다."),
    ;

    private final boolean result;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final boolean result, final int status, final String code, final String message) {
        this.result = result;
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Boolean getResult() {
        return this.result;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}