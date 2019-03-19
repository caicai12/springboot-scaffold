package com.zhh.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhh.common.utils.PagingResponse;
import com.zhh.dao.dbOne.UserMapper;
import com.zhh.entity.User;
import com.zhh.params.UserQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户管理模块服务实现层
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/17 16:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PagingResponse<User> listUserByPage(UserQueryParams userQueryParams) {
        Page<User> page = PageHelper.startPage(userQueryParams.getOffset(), userQueryParams.getLimit())
                .doSelectPage(() -> userMapper.listUserByPage(userQueryParams));

        PagingResponse<User> pagingResponse = new PagingResponse<User>(
                page.getPageNum(), page.getPageSize(),
                page.getTotal(), page.getResult());
        return pagingResponse;
    }
}
