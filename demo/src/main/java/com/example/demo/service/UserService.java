package com.example.demo.service;

import com.example.demo.Model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author 12586
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-08-27 12:11:55
*/

public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param uerAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String uerAccount,String userPassword,String checkPassword);

    User userLogin(String uerAccount, String userPassword, HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    User getSafetyUser(User user);
}
