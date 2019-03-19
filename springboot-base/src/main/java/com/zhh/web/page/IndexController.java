package com.zhh.web.page;

import com.zhh.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 首页路由
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/21 10:45
 */
@Controller
public class IndexController extends BaseController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model){
        Map<String,Object> resDataPack = new HashMap<String,Object>();
        //获取当前系统时间
        Calendar calendar = Calendar.getInstance();
        String yearStr = calendar.get(Calendar.YEAR)+"年";
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = month < 10 ? "0" + month + "月" : month + "月";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day + "日" : day + "日";
        resDataPack.put("currentSysDate",yearStr + monthStr + dayStr);
        model.addAttribute("resDataPack",resDataPack);
        return "/index";
    }

}
