package com.family.utils;

/**
 * String工具类
 * 
 * @author suchao
 * 
 */
public class StringUtil {

    /**
     * 格式化字符串
     * 
     * 例：formateString("xxx{0}bbb",1) = xxx1bbb
     * 
     * @param str
     * @param params
     * @return
     */
    public static String formateString(String str, String... params) {
        for (int i = 0; i < params.length; i++) {
            str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
        }
        return str;
    }
    
    public static void main(String args[]){
        StringUtil St=new StringUtil();
        String str = StringUtil.formateString("您好{0}", "suning");
        System.out.println(str);
    }
}
