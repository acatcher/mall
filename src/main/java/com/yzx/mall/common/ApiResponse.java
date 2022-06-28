package com.yzx.mall.common;

import com.yzx.mall.exception.MallExceptionCode;

public class ApiResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    private static final int OK_CODE = 10000;
    private static final String OK_MSG = "SUCCESS!";

    public ApiResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResponse() {
        this(OK_CODE, OK_MSG);
    }

    public ApiResponse(T data) {
        this(OK_CODE, OK_MSG);
        this.data = data;
    }

    public static <T> ApiResponse<T> success(){
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<T>(data);
    }

    public static <T> ApiResponse<T> error(Integer code, String msg){
        return new ApiResponse<>(code, msg);
    }

    public static <T> ApiResponse<T> error(MallExceptionCode mec){
        return new ApiResponse<>(mec.getCode(), mec.getMsg());
    }




    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
