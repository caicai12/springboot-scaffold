package com.zhh.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description cookie工具类
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/20 19:15
 */
public class CookieUtils {
    //新增或更新cookie
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    //删除cookie
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    //获取cookie值
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    //获取cookie
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();
        if(null==cookies){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }

}
