package com.yzx.mall.exception;

import com.yzx.mall.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Exception: ", e);
        return ApiResponse.error(MallExceptionCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(MallException.class)
    @ResponseBody
    public Object handleMallException(MallException e) {
        log.error("MallException: ", e);
        return ApiResponse.error(e.getCode(), e.getMsg());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ApiResponse handleBindException(
            BindException e) {
        log.error("BindException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    private ApiResponse handleBindingResult(BindingResult result) {

        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ApiResponse.error(MallExceptionCode.REQUEST_PARAM_ERROR);
        }
        return ApiResponse
                .error(MallExceptionCode.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }


}
