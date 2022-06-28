package com.yzx.mall.controller;

import com.yzx.mall.common.ApiResponse;
import com.yzx.mall.model.pojo.User;
import com.yzx.mall.model.request.RegisterReq;
import com.yzx.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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



}
