package com.zhh.shiro;

import com.zhh.common.utils.JwtUtil;
import com.zhh.domain.User;
import com.zhh.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义Realm
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/27 14:04
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    private UserService userService;

    @Autowired
    public ShiroRealm(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroToken;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtil.getUsername(token);
        // 判断用户名是否存在且与token中的一致
        if (username == null || !JwtUtil.verifyToken(token,username)){
            throw new AuthenticationException();
        }
        // 去数据库中查询用户名是否存在、激活状态等判断...
        User user = userService.getUser(username);
        if (user == null){
            throw new AuthenticationException();
        }
        return new SimpleAuthenticationInfo(token, token, "shiro-realm");
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.toString();
        String username = JwtUtil.getUsername(token);

        Set<String> roleSet = new HashSet();
        Set<String> permissionSet = new HashSet();

        User user = userService.getUser(username);
        // 获取用户角色
        String role = user.getRole();
        // 获取用户权限
        String permission = user.getPermission();

        if (role != null){
            String[] roles = role.split(",");
            roleSet.addAll(Arrays.asList(roles));
        }
        if (permission != null){
            String[] permissions = permission.split(",");
            permissionSet.addAll(Arrays.asList(permissions));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

}
