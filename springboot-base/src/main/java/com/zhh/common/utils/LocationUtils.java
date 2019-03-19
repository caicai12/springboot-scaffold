package com.zhh.common.utils;

/**
 * 位置工具类
 */
public class LocationUtils {
    //地球半径
    private static final double EARTH_RADIS = 6378.137;

    private static double rad(double d){
        return d*Math.PI/180.0;
    }

    /**
     * 通过经纬度计算距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1,double lng1, double lat2, double lng2){
        if(lat1==0.0 && lng1 ==0.0 || lat2 ==0.0 && lng2 ==0){
            return 0;
        }
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    /**
     * 将距离转成米或者公里单位显示
     * @param distance
     * @return
     */
    public static String convertToDistanceMeter(double distance){
        String result;
        if (distance < 0){
            result = "未知距离";
        }else if (distance >= 0 && distance < 1){
            result = (distance * 1000) + "米";
        }else{
            result = distance+ "公里";
        }
        return result;
    }

}
