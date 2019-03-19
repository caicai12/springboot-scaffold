package com.zhh.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 过滤器
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/2/21 9:41
 */
//@WebFilter(urlPatterns = { "/user/*"},filterName = "responseFilter")
public class ResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
        //分界线，request拦截执行从这往前的代码，resposne拦截执行该行之后的代码
        filterChain.doFilter(servletRequest, wrapper);
        //后台已经把数据封装到缓冲区中，这里是取出来
        byte[] result = wrapper.getContent();
        //数据加工处理:生产乱码问题待处理 todo
        String strResult = null;
        if(result != null && result.length > 0){
            strResult = new String(result);
            strResult = strResult.replace("x","y");
            result = strResult.getBytes();
        }
        //后台数据编辑后，通过真正的response写到前台页面去
        servletResponse.getOutputStream().write(result);
    }

    @Override
    public void destroy() {
    }
}
