package com.zhh.web;

import com.zhh.common.enums.BizExceptionEnum;
import com.zhh.common.utils.JwtUtil;
import com.zhh.common.utils.ResponsePacket;
import com.zhh.domain.User;
import com.zhh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 控制器
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/28 11:01
 */
@RestController
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponsePacket<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password){
        User user = userService.getUser(username);
        // 用户名不存在
        if (user == null){
            return ResponsePacket.onError(BizExceptionEnum.USERNAME_NOT_EXSIT);
        } else if (!password.equals(user.getPassword())){
            // 密码错误
            return ResponsePacket.onError(BizExceptionEnum.PASSWORD_NOT_CORRECT);
        } else {
            return ResponsePacket.onSuccess(JwtUtil.createToken(username));
        }
    }

    @RequestMapping(value = "/requireAuth",method = RequestMethod.GET)
    public ResponsePacket<String> requireAuth(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return ResponsePacket.onSuccess();
        } else{
            return ResponsePacket.onError(BizExceptionEnum.NOT_LOGIN);
        }
    }

    @RequestMapping(value = "/requireRole",method = RequestMethod.GET)
    @RequiresRoles("admin")
    public ResponsePacket<String> requireRole() {
        return ResponsePacket.onSuccess();
    }

    @RequestMapping(value = "/requirePermission",method = RequestMethod.GET)
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponsePacket<String> requirePermission() {
        return ResponsePacket.onSuccess();
    }


}
