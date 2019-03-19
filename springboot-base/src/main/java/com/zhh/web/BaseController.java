package com.zhh.web;

import com.zhh.common.enums.BizExceptionEnum;
import com.zhh.common.exception.BizException;
import com.zhh.entity.IdentityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 控制器基类
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/17 17:25
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    public HttpServletRequest getRequest(){
        return this.request;
    }

    public IdentityInfo getIdentityInfo(){
        return (IdentityInfo)getRequest().getAttribute("IdentityInfo");
    }

    public void vertifyParams(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BizException(BizExceptionEnum.PARAM_ERROR);
        }
    }

}
