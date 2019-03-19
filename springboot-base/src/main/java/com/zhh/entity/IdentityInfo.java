package com.zhh.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 身份信息
 * @Author
 * @Version V1.0
 * @Date 2018/12/26 9:52
 */
@Getter
@Setter
@ToString
public class IdentityInfo implements Serializable {

    private static final long serialVersionUID = -6406266334383100312L;

    // 用户ID
    private int userId;

    // 登录时间
    public Date loginTime;

}
