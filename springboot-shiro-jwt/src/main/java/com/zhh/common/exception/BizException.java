package com.zhh.common.exception;

import com.zhh.common.enums.BizExceptionEnum;

import java.io.Serializable;

/**
 * @Description 自定义异常
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/1/12 10:36
 */
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 2059502058802658332L;

    private BizExceptionEnum bizExceptionEnum;

    /**
     * 默认构造方法
     */
    public BizException(){
    }

    public BizException(BizExceptionEnum bizExceptionEnum){
        this.bizExceptionEnum = bizExceptionEnum;
    }

    public BizExceptionEnum getBizExceptionEnum(){
        return this.bizExceptionEnum;
    }
}
