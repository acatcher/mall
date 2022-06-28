package com.yzx.mall.service.impl;

import com.yzx.mall.exception.MallException;
import com.yzx.mall.exception.MallExceptionCode;
import com.yzx.mall.model.dao.UserMapper;
import com.yzx.mall.model.pojo.User;
import com.yzx.mall.service.UserService;
import com.yzx.mall.utils.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User selectUser() {
        User user = userMapper.selectByPrimaryKey(1);
        return user;
    }

    @Override
    public User selectUserByName(String name){
        User user = userMapper.selectByName(name);
        return user;
    }

    @Override
    public void insertUser(String name, String passwd){
        User user = new User();
        User oldUser = selectUserByName(name);
        if(oldUser != null){
            throw new MallException(MallExceptionCode.USER_ALREADY_EXISTS);
        }
        user.setUsername(name);
        try {
            user.setPassword(MD5.getMD5Str(passwd));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count = userMapper.insertSelective(user);
        if(count == 0){
            throw new MallException(MallExceptionCode.FAIL_INSERT);
        }
    }

}
