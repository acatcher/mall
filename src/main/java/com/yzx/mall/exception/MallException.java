package com.yzx.mall.exception;

public class MallException extends RuntimeException{

    private Integer code;
    private String msg;

    public MallException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MallException(MallExceptionCode mec){
        this(mec.getCode(), mec.getMsg());
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
