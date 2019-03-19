package com.zhh.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 分页参数对象
 * @param <T>
 */
@ApiModel(value = "分页响应模型", description = "分页响应模型封装")
public class PagingResponse<T> implements Serializable {

    private static final long serialVersionUID = -5011017060626302349L;

    // 当前页
    @ApiModelProperty(value = "当前页即查询第几页", required = false, position = 1, example = "1")
    private int offset;

    // 每页的数量
    @ApiModelProperty(value = "分页大小", required = false, position = 2, example = "10")
    private int limit;

    @ApiModelProperty(value = "总记录数", required = false, position = 3)
    private long total;

    @ApiModelProperty(value = "总页数", required = false, position = 4)
    private int pages;

    @ApiModelProperty(value = "结果集", required = false, position = 5)
    private List<T> list;

    public PagingResponse() {}

    public PagingResponse(int offset, int limit, long total, List<T> list) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.list = list;
        this.setTotal(total);
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        // 计算总页数
        int temp = (int)total/limit;
        if(total%limit!=0){
            this.setPages(temp+1);
        }else{
            this.setPages(temp);
        }
    }
}
