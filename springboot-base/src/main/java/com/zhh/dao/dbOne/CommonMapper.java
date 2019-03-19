package com.zhh.dao.dbOne;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Description 公用模块数据访问接口层
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/27 19:22
 */
@Repository
public interface CommonMapper {
    List<JSONObject> listBannerInfo(Integer bannerType);

}
