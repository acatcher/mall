package com.yzx.mall.model.request;

import javax.validation.constraints.*;

public class RegisterReq {


    @NotEmpty(message = "No User Name!")
    @NotBlank(message = "No Blank!")
    private String userName;

    @NotEmpty(message = "No passwd!")
    @NotBlank(message = "No Blank!")
    @Size(min = 3, max = 10)
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
