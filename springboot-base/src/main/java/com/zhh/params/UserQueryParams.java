package com.zhh.params;

import com.zhh.common.utils.PagingRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description 用户分页查询参数
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/27 19:28
 */
@Getter
@Setter
@ToString
@ApiModel("用户分页查询参数")
public class UserQueryParams extends PagingRequest implements Serializable {
    private static final long serialVersionUID = -6108250428200819136L;

    //用户状态
    @ApiModelProperty(value = "用户状态", required = false, position = 1,example = "空：全部，0：未激活，1:激活")
    private Integer state;

    //用户ID
    @ApiModelProperty(value = "用户ID", required = false,hidden = true,position = 2,example = "123456")
    private Integer userId;

}
