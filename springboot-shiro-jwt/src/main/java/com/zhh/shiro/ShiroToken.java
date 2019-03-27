package com.zhh.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description: shiro令牌
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/27 14:01
 */
public class ShiroToken implements AuthenticationToken {
    private String token;

    public ShiroToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
