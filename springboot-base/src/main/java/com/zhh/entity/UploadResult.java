package com.zhh.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description 文件上传响应
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/1/2 11:35
 */
@Getter
@Setter
@ToString
public class UploadResult implements Serializable{
    private static final long serialVersionUID = -4292772545278381600L;

    private int error;

    private String msg;

    private String fileId;

}
