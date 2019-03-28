package com.zhh.service;

import com.zhh.data.DataSource;
import com.zhh.domain.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 用户服务实现类
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/27 14:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String username) {
        if(!DataSource.getData().containsKey(username)){
            return null;
        }
        User user = new User();
        Map<String ,String> detail = DataSource.getData().get(username);
        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermission(detail.get("permission"));
        return user;
    }
}
