package com.yzx.mall.controller;

import com.yzx.mall.common.ApiResponse;
import com.yzx.mall.common.Constant;
import com.yzx.mall.exception.MallException;
import com.yzx.mall.exception.MallExceptionCode;
import com.yzx.mall.model.pojo.User;
import com.yzx.mall.model.request.RegisterReq;
import com.yzx.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User getUser(){
        User user = userService.selectUser();
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse register(@Valid RegisterReq registerReq){

        userService.insertUser(registerReq.getUserName(), registerReq.getPassword());
        return ApiResponse.success();
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(@RequestParam String userName, @RequestParam String password, HttpSession httpSession){
        if(StringUtils.isEmpty(userName)){
            throw new MallException(MallExceptionCode.NEED_USER_NAME);
        }

        if(StringUtils.isEmpty(password)){
            throw new MallException(MallExceptionCode.NEED_PASSWORD);
        }

        User user = userService.selectUserLogin(userName, password);
        httpSession.setAttribute(Constant.USER, user);

        return ApiResponse.success(user);

    }

    @ApiOperation("UPDATE SIGNATURE")
    @PostMapping("/user/update")
    @ResponseBody
    public ApiResponse updateSignature(String signature, HttpSession httpSession){
        User userSession = (User)httpSession.getAttribute(Constant.USER);
        // not login
        if(userSession == null){
            return ApiResponse.error(MallExceptionCode.HAVE_NOT_LOGIN);
        }
        User user = new User();
        user.setId(userSession.getId());
        user.setPersonalizedSignature(signature);

        userService.updateSignature(user);
        return ApiResponse.success();
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ApiResponse logout(HttpSession httpSession){

        httpSession.removeAttribute(Constant.USER);
        return ApiResponse.success();

    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public ApiResponse AdminLogin(String userName, String password, HttpSession httpSession){

        if(StringUtils.isEmpty(userName)){
            return ApiResponse.error(MallExceptionCode.NEED_USER_NAME);
        }

        if(StringUtils.isEmpty(password)){
            return ApiResponse.error(MallExceptionCode.NEED_PASSWORD);
        }

        User user = userService.selectAdminLogin(userName, password);
        httpSession.setAttribute(Constant.USER, user);

        return ApiResponse.success(user);

    }



}
