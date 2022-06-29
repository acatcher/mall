package com.yzx.mall.service;

import com.yzx.mall.model.pojo.User;

public interface UserService {
    User selectUser();

    User selectUserByName(String name);

    void insertUser(String name, String passwd);

    User selectUserLogin(String userName, String password);

    void updateSignature(User user);

    User selectAdminLogin(String userName, String password);
}
