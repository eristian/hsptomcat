package com.hspedu.utils;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  11:28
 *
 * @author Mamenchisat
 * @verison 1.0
 */
public class WebUtils {
    /**
     * 将一个字符串数字转成一个int，如果转换失败就返回传入的defaultval
     * @param strNum
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strNum,int defaultValue){
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println(strNum + "格式不对，转换失败");
        }
        return defaultValue;
    }
}
