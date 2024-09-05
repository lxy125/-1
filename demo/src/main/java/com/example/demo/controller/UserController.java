package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Model.domain.User;
import com.example.demo.Model.domain.request.UserLoginRequest;
import com.example.demo.Model.domain.request.UserRegisterRequest;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户接口
 *
 * @author: lxy
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;




    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest httpServletRequest){
        Object userObj= httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null) {
            return null;
        }
        long userID = user.getId();
        User user1= userService.getById(userID);
        return userService.getSafetyUser(user1);

    }

    @PostMapping("/register")
    public long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return -1;
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (userAccount == null || userPassword == null || checkPassword == null) {
            return -1;
        }

        return userService.userRegister(userAccount, userPassword, checkPassword);
    }



    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null) {
            return null;
        }

        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (userAccount == null || userPassword == null) {
            return null;
        }

        return userService.userLogin(userAccount, userPassword,request);
    }


    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser==null||!userService.isAdmin(request)){
            return new ArrayList<>();
        }


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());


    }


    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            return false;
        }
        if(id<=0){
            return false;
        }
        return userService.removeById(id);
    }


}
