package com.zhh.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: mock数据
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/28 10:18
 */
public class DataSource {
    private static Map<String, Map<String, String>> data = new HashMap<>();

    // 静态代码块，随类的加载而加载，而且仅加载一次
    static {
        Map<String, String> data1 = new HashMap<>();
        data1.put("password","123");
        data1.put("role", "user");
        data1.put("permission", "view");

        Map<String, String> data2 = new HashMap<>();
        data2.put("password","456");
        data2.put("role", "admin");
        data2.put("permission", "view,edit");

        data.put("jack", data1);
        data.put("jane", data2);
    }

    // 对外暴露共有方法
    public static Map<String, Map<String, String>> getData() {
        return data;
    }
}
