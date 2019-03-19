package com.zhh.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Description 通过uuid生成16位唯一号
 * @Author zhouhui
 * @Version V1.0
 * @Date 2019/1/16 15:04
 */
public class RandomIdUtils {
    public static String getUniqueIdByUuid(){
        int first = new Random(10).nextInt(8) + 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

}
