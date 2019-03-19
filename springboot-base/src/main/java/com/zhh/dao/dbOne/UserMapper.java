package com.zhh.dao.dbOne;

import com.zhh.entity.User;
import com.zhh.params.UserQueryParams;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Description 用户管理模块数据访问接口层
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/27 19:22
 */
@Repository
public interface UserMapper {
    List<User> listUserByPage(UserQueryParams userQueryParams);

}
