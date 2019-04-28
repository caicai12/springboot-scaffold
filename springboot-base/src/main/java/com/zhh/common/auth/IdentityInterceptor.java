package com.zhh.common.auth;

import com.zhh.common.utils.CookieUtils;
import com.zhh.common.utils.DateUtils;
import com.zhh.common.utils.JwtUtil;
import com.zhh.entity.IdentityInfo;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description 权限拦截
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/20 19:01
 */
@Component
public class IdentityInterceptor implements HandlerInterceptor {
    private static final int EXPIRED_DAY = 7;

    private static final String JWT_COOKIE_NAME = "jwt";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie jwtCookie = CookieUtils.getCookie(httpServletRequest, JWT_COOKIE_NAME);
        if (null == jwtCookie) {
            return true;
        }

        Claims claims = JwtUtil.parseJWT(jwtCookie.getValue());
        if (null == claims) {
            return true;
        }

        IdentityInfo identityInfo = new IdentityInfo();
        identityInfo.setUserId((int) claims.get("UserId"));
        identityInfo.setLoginTime(DateUtils.transferStrToDate((String) claims.get("LoginTime"), "yyyy-MM-dd HH:mm:ss"));

        int diffDay = DateUtils.differentDaysByMillisecond(new Date(),identityInfo.loginTime);
        if (diffDay > EXPIRED_DAY) {
            return true;
        }
        httpServletRequest.setAttribute("IdentityInfo", identityInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
