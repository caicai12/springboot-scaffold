package com.zhh.common.utils;

import com.zhh.entity.UploadResult;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @Description RestTemplate工具类
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/1/2 11:11
 */
@Component
public class RestTemplateUtils {
    //文件上传第三方
    public static UploadResult uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
        UploadResult uploadResult = null;
        String httpUrl = "http://ip:port" + "/File/Upload";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
        headers.setContentType(type);

        //请求参数封装
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        //上传者,自定义参数
        form.add("creator", "admin");
        //文件名，自定义参数
        form.add("FileName", file.getOriginalFilename());

        //文件资源封装
        String tempPath =  request.getServletContext().getRealPath("/") + System.currentTimeMillis();
        File f = new File(tempPath);
        if(!f.exists()){
            f.mkdirs();
        }
        File tempFile = new File(f.getPath() + "/" + file.getOriginalFilename());
        if(!tempFile.exists()){
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        FileSystemResource resource = new FileSystemResource(tempFile);
        //文件
        form.add("Files", resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(form, headers);
        uploadResult = restTemplate.postForObject(httpUrl, requestEntity,UploadResult.class);
        return uploadResult;
    }

    //POST请求第三方
    public static String postRequest(String httpUrl,String jsonString){
        String responseResult = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonString, headers);
        responseResult = restTemplate.postForObject(httpUrl, requestEntity,String.class);
        return responseResult;
    }

}
