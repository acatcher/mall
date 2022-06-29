package com.yzx.mall.exception;

public enum MallExceptionCode {

    NEED_USER_NAME(10001, "REQUIRE NAME!"),
    NEED_PASSWORD(10002, "REQUIRE PASSWORD!"),
    USER_ALREADY_EXISTS(10003, "USER NAME ALREADY EXISTS!"),
    FAIL_INSERT(10004, "FAIL INSERT!"),
    REQUEST_PARAM_ERROR(10005,"REQUEST PARAM ERROR"),
    LOGIN_FAILED(10006, "LOGIN FAILED!"),
    HAVE_NOT_LOGIN(10007, "HAVE NOT LOGIN!"),
    UPDATE_FAILED(10008, "UPDATE_FAILED"),
    NOT_ADMIN(10009, "NOT ADMIN"),
    SYSTEM_ERROR(20000, "SYSTEM ERROR");

    private Integer code;
    private String msg;

    MallExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
