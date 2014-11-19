package com.family.utils;

import java.util.HashMap;
import java.util.Map;
import flexjson.JSONSerializer;

/**
 * JSON转换工具类
 * 
 * @author trunks
 * @version $Id: JsonUtils.java, v 0.1 2012-3-7 下午04:49:24 trunks Exp $
 */
public class JsonUtil {

    private static JSONSerializer jsonSerializer = new JSONSerializer();

    /**
     * 转换JSON对象
     * @param excludes 不包含的属性
     */
    public static String writeObjectJson(Object obj, String... excludes) {
        return new JSONSerializer().exclude(excludes).deepSerialize(obj);
    }

    /**
     * 转换JSON对象为Ext表单提交成功的方式<br />
     * 
     * 对于com.hosane.cms.dao.PageInfo对象，直接调用writeObjectJson即可<br /><br />
     * ex.{"success":"false","errors":{"name":"名称已存在"}}
     * 
     * @param obj 错误信息，若用map，Key若为表单项的name，则会自动在对应表单项显示错误信息
     * @param success true为result，false为errors
     */
    public static String writeFormObjectJson(Object obj, boolean success) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        if (success) {
            map.put("result", obj);
        } else {
            map.put("errors", obj);
        }
        return jsonSerializer.deepSerialize(map);
    }

    /**
     * 转换分页查询结果
     * 
     * @param list 结果皆
     * @param total 记录数
     */
    public static String writePageJson(Object list, Object total) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("total", total);
        return jsonSerializer.deepSerialize(map);
    }

    /**
     * 无转换分页查询结果
     * 
     * @param list 结果皆
     */
    public static String writeNoPageJson(Object list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        return jsonSerializer.deepSerialize(map);
    }
    
}