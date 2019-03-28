package com.zhh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: 用户实体
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/28 10:21
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1612561928899665229L;

    private String username;

    private String password;

    private String role;

    private String permission;
}
