package com.zhh.common.enums;

/**
 * 业务异常类型
 */
public enum BizExceptionEnum {

    // 公共错误码
    SUCCESS(0, "操作成功"),
    THROW_EXCEPTION(1, "抛出异常");

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
