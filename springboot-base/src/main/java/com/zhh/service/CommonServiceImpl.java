package com.zhh.service;

import com.alibaba.fastjson.JSONObject;
import com.zhh.dao.dbOne.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description 公用模块服务实现层
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/21 15:11
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    @Cacheable(value = "dataCache", key = "'banners_'+#bannerType")
    public List<JSONObject> listBannerInfo(Integer bannerType) {
        return commonMapper.listBannerInfo(bannerType);
    }

}
