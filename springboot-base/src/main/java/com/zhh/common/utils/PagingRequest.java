package com.zhh.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页参数对象
 */
@ApiModel(value = "分页参数模型",description = "分页参数模型封装")
public class PagingRequest implements Serializable {
    private static final long serialVersionUID = 2425707217324862509L;

    /**
     * 查询页(查询第几页)
     */
    @ApiModelProperty(value = "查询页(查询第几页)", required = false, position = 1,example = "1")
    private int offset;

    /**
     * 分页大小(每页条数)
     */
    @ApiModelProperty(value = "分页大小(每页条数)", required = false, position = 2,example = "10")
    private int limit;

    public PagingRequest() {
        this.offset = 1;
        this.limit = 10;
    }

    public PagingRequest(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * 默认分页<br/>
     * 默认第一页，每页大小10条
     * @return 分页参数
     */
    public static PagingRequest createPaging() {
        return new PagingRequest();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
