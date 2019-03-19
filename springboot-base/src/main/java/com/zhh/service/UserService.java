package com.zhh.service;

import com.zhh.common.utils.PagingResponse;
import com.zhh.entity.User;
import com.zhh.params.UserQueryParams;

/**
 * @Description: 用户管理模块服务接口层
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/17 16:10
 */
public interface UserService {
    /**
     * 分页查询用户列表
     * @param userQueryParams
     * @return PagingResponse
     */
    PagingResponse<User> listUserByPage(UserQueryParams userQueryParams);
}
