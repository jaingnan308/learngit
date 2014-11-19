package com.family.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.family.finance.model.PageBean;

public class PageUtils {
    private static PageBean pageBean = new PageBean();
    
    public static void setInitPagingBean(Map<String, Object> params) {
        if(StringUtils.isNotBlank((String)params.get("page")) && StringUtils.isNotBlank((String)params.get("rows"))){
            Integer l = Integer.valueOf((String)params.get("page"))-1;
            Integer k = Integer.valueOf((String)params.get("rows"));
            pageBean.setStart(l*k);
            pageBean.setLimit(k);
            params.put("pageBean", pageBean);
        }
    }
    
    public static void setSuccessResponse(HttpServletResponse response, String result) throws IOException{
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }
    
    public static void setNoSuccessResponse(HttpServletResponse response, String result) throws IOException{
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }
}