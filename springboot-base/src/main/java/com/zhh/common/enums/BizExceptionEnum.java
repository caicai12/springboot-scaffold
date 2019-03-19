package com.zhh.common.enums;

/**
 * 业务异常类型
 */
public enum BizExceptionEnum {

    // 公共错误码
    SUCCESS(0, "操作成功"),
    THROW_EXCEPTION(1, "抛出异常"),
    SYSTEM_ERROR(2, "系统错误"),
    NOT_FOUND_OBJECT(3, "找不到对象"),
    BUSINESS_ERROR(4, "业务错误"),
    BOUND(5, "下标越界"),
    LOGIN_FAILED(6, "登录失败"),
    NEED_LOGIN(7, "需要登录"),
    OPERATION_FORBID(8, "无权操作"),
    DATA_NOT_EXIST(9,"数据不存在"),
    PARAM_ERROR(10,"参数错误");

    private int errCode;
    private String errMsg;

    BizExceptionEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
