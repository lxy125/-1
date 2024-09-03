package com.example.demo.Model.domain.request;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 *
 */
@Data
public class UserLoginRequest implements Serializable {
    public static final long serialVersionUID = 1L;
    private String userAccount;
    private String userPassword;


}
