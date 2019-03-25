package com.zhh.common.utils;

import com.zhh.common.enums.BizExceptionEnum;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 响应信息主体
 * @param <T>
 */
public class ResponsePacket<T> implements Serializable {

    private static final long serialVersionUID = -5836459412110053424L;

    private String msg = "";

    private int error;

    private T data;

    public ResponsePacket() {
    }

    /**
     * 成功, 无返回结果
     * @return
     */
    public static ResponsePacket onSuccess() {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setError(BizExceptionEnum.SUCCESS.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.SUCCESS.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    /**
     * 成功, 返回自定义的实体
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponsePacket<T> onSuccess(T data) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setError(BizExceptionEnum.SUCCESS.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.SUCCESS.getErrMsg());
        responsePacket.setData(data);
        return responsePacket;
    }

    /**
     * 错误, 无返回结果, 错误码为抛出异常(1)
     * @return
     */
    public static ResponsePacket onError() {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setError(BizExceptionEnum.THROW_EXCEPTION.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.THROW_EXCEPTION.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    /**
     * 错误, 无返回结果, 错误码为自定义的业务异常
     * @param bizExceptionEnum
     * @return
     */
    public static ResponsePacket onError(BizExceptionEnum bizExceptionEnum) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setError(bizExceptionEnum.getErrCode());
        responsePacket.setMsg(bizExceptionEnum.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    /**
     * 错误, 有返回结果, 错误码为自定义的业务异常+自定义的实体
     * @param bizExceptionEnum
     * @param data
     * @return
     */
    public static ResponsePacket onError(BizExceptionEnum bizExceptionEnum,Object data) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setError(bizExceptionEnum.getErrCode());
        responsePacket.setMsg(bizExceptionEnum.getErrMsg());
        responsePacket.setData(data);
        return responsePacket;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
