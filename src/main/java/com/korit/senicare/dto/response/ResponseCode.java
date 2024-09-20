package com.korit.senicare.dto.response;

// ResponseDTO의 code 상수

public interface ResponseCode {

    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TEL_NUMBER = "DT";
    String NO_EXIST_USER_ID = "NI";
    String NO_EXIST_TOOL = "NT";

    String TEL_AUTH_FAIL = "TAF";
    String SIGN_IN_FIIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
    String DATABASE_ERROR = "DBE";

}
