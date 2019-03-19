package com.zhh.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.nio.charset.Charset;
import java.util.List;

/**
 * JSON处理工具类
 */
public class JsonUtils {
    /**
     * 将JSON文件解析为Java返回
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> transferJsonToEntity(String fileName,Class<T> clazz){
        String filePath = "classpath:json/" + fileName + ".json";
        List<T> list = null;
        //解析Json
        try {
            String jsonData = FileUtils.readFileToString(ResourceUtils.getFile(filePath), Charset.forName("UTF-8"));
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray(fileName);
            list = JSON.parseArray(jsonArray.toString(), clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
