package com.zhh.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 页面路由
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/22 15:53
 */
@Controller
public class PageRouteController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model){
        return "/index";
    }
}
