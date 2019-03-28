package com.zhh.common.enums;

/**
 * 业务异常类型
 */
public enum BizExceptionEnum {

    // 公共错误码
    SUCCESS(0, "操作成功"),
    AUTHENTICATION_EXCEPTION(1,"shiro认证错误"),
    AUTHORIZATION_EXCEPTION(2,"shiro权限不足"),
    USERNAME_NOT_EXSIT(3,"用户名不存在"),
    PASSWORD_NOT_CORRECT(4,"密码错误"),
    NOT_LOGIN(5,"尚未登录"),
    UNSUPPORTEDENCODING_EXCEPTION(6,"token加密错误"),
    JWTDECODE_EXCEPTION(7,"token解密错误"),
    TOKEN_EXPIRED_EXCEPTION(8,"token过期"),
    THROW_EXCEPTION(500, "抛出异常");

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
