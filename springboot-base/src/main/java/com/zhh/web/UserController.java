package com.zhh.web;

import com.zhh.common.utils.PagingResponse;
import com.zhh.common.utils.ResponsePacket;
import com.zhh.entity.IdentityInfo;
import com.zhh.entity.User;
import com.zhh.params.UserQueryParams;
import com.zhh.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户管理模块控制层
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/24 9:49
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户管理模块"})
public class UserController extends BaseController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页查询用户列表")
    @RequestMapping(value="/listUserByPage", method = RequestMethod.POST)
    public ResponsePacket<PagingResponse<User>> listUserByPage(@RequestBody UserQueryParams userQueryParams) {
        PagingResponse<User> users = null;
        IdentityInfo identityInfo = getIdentityInfo();
        userQueryParams.setUserId(identityInfo.getUserId());
        users = userService.listUserByPage(userQueryParams);
        return ResponsePacket.onSuccess(users);
    }
}
