package com.zhh.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Description 公用模块服务接口层
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/21 15:11
 */
public interface CommonService {
    /**
     * 查询Banner信息列表
     * @param bannerType
     * @return list
     */
    List<JSONObject> listBannerInfo(Integer bannerType);
}
