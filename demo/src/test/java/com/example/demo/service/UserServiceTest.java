package com.example.demo.service;

import com.example.demo.Model.domain.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        // 创建一个新的用户对象
        User user = new User();
        user.setId(122L);
        user.setUsername("lxy");
        user.setUserAccount("lxy");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("12345678");
        user.setEmail("");
        user.setUserStatus(0);
        user.setPhone("");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);

        // 调用保存方法
        boolean result = userService.save(user);

        // 断言保存成功
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "lxy123";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String email = "12345678@qq.com";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertNotNull(result);
    }
}
