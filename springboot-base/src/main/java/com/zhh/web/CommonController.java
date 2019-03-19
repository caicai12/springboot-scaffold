package com.zhh.web;

import com.alibaba.fastjson.JSONObject;
import com.zhh.common.utils.ResponsePacket;
import com.zhh.common.utils.RestTemplateUtils;
import com.zhh.entity.UploadResult;
import com.zhh.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description 公用功能模块控制层
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/24 10:02
 */
@RestController
@RequestMapping("/common")
@Api(tags = {"公用功能模块"})
public class CommonController extends BaseController {
    Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @ApiOperation(value = "查询Banner信息")
    @RequestMapping(value="/listBannerInfo", method = RequestMethod.GET)
    public ResponsePacket<List<JSONObject>> listBannerInfo(@RequestParam Integer bannerType) throws Exception {
        List<JSONObject> banners = commonService.listBannerInfo(bannerType);
        return ResponsePacket.onSuccess(banners);
    }

    @ApiOperation(value = "上传图片文件")
    @RequestMapping(value="/uploadImageFile", method = RequestMethod.POST)
    public ResponsePacket<String> uploadImageFile(MultipartFile imageFile) throws Exception {
        UploadResult uploadResult = restTemplateUtils.uploadFile(imageFile,getRequest());
        String fileId = uploadResult.getFileId();
        return ResponsePacket.onSuccess(fileId);
    }

    @ApiOperation(value = "清除缓存信息")
    @RequestMapping(value="/clearAllCaches", method = RequestMethod.GET)
    @CacheEvict(value="dataCache",allEntries=true)
    public ResponsePacket<Void> clearAllCaches() {
        return ResponsePacket.onSuccess();
    }

}
