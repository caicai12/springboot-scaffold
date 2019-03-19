package com.zhh.common.auth;

import com.alibaba.fastjson.JSONObject;
import com.zhh.common.enums.BizExceptionEnum;
import com.zhh.entity.IdentityInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description 权限拦截
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/20 19:01
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        JSONObject jsonObject = new JSONObject();

        IdentityInfo identityInfo= (IdentityInfo)httpServletRequest.getAttribute("IdentityInfo");
        if(identityInfo==null){
            jsonObject.put("error", BizExceptionEnum.NEED_LOGIN.getErrCode());
            jsonObject.put("msg",BizExceptionEnum.NEED_LOGIN.getErrMsg());
            pw.write(jsonObject.toJSONString());
            pw.flush();
            return false;
        }
        /*jsonObject.put("error",BizExceptionEnum.SUCCESS.getErrCode());
        jsonObject.put("msg",BizExceptionEnum.SUCCESS.getErrMsg());
        pw.write(jsonObject.toJSONString());
        pw.flush();*/
        httpServletResponse.reset();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
