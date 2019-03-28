package com.zhh.service;

import com.zhh.domain.User;

/**
 * @Description: 用户服务接口
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/27 14:07
 */
public interface UserService {
    public User getUser(String username);
}
