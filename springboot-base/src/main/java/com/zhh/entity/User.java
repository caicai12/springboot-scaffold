package com.zhh.entity;

import java.io.Serializable;

/**
 * @Description: 用户实体
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/17 15:37
 */
public class User implements Serializable {

    //用户名称
    private String username;

    //用户性别
    private String sex;

    //年龄
    private Integer age;

    //状态
    private Boolean state;

}
