package com.family.finance.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExcelBaseAction {
    private final static Log logger = LogFactory.getLog(ExcelBaseAction.class);

    public ExcelBaseAction() {

    }

    public static void exportFile(HttpServletResponse response, HttpServletRequest request,
                                  File file, boolean isDel) {
    	response.reset();
        OutputStream out = null;
        InputStream in = null;
        // 获得文件
        try {
            String filename = URLEncoder.encode(file.getName(), "UTF-8");
            String fileType = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            // 定义输出类型(下载)
            if (fileType.toLowerCase().endsWith("zip")) {
                response.setContentType("application/x-zip-compressed");
            } else if (fileType.toLowerCase().endsWith("rar")) {
                response.setContentType("application/octet-stream");
            } else if (fileType.toLowerCase().endsWith("doc")) {
                response.setContentType("application/msword");
            } else if ((fileType.toLowerCase().endsWith("xls"))
                       || (fileType.toLowerCase().endsWith("csv"))) {
                response.setContentType("application/ms-excel ");
            } else if (fileType.toLowerCase().endsWith("pdf")) {
                response.setContentType("application/pdf");
            } else if (fileType.toLowerCase().endsWith("jpg")
                       || fileType.toLowerCase().endsWith("jpeg")
                       || fileType.toLowerCase().endsWith("png")) {
                response.setContentType("image/jpeg");
            } else {
                response.setContentType("application/x-msdownload");
            }
            response.setContentType("application/vnd.ms-excel");//设置正确的输出类型
            if (request.getHeader("user-agent").indexOf("MSIE") != -1) {
                filename = URLEncoder.encode(file.getName(), "UTF-8");
            } else {
                filename = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
            }
            // 定义输出类型(下载)
            response.setHeader("Location", filename);

            // 定义输出文件
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            in = new FileInputStream(file.getPath());
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) != -1) {
                bos.write(buffer, 0, i);
            }
            out = response.getOutputStream();
            out.write(bos.toByteArray());
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("导出文件失败!", e);
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (isDel) {
                    // 删除文件,删除前关闭所有的Stream.
                    if (file.isFile() && file.exists()) {
                        boolean result = file.delete();
                        if (!result) {
                            if (logger.isInfoEnabled()) {
                                logger.info("删除文件失败!");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("导出在关闭IO流时异常!", e);
                }
            }
        }
    }

    /**
     * FTP附件下载
     * @param response
     * @param request
     * @param in
     */
    public static void downLoadFtpFile(HttpServletRequest request, HttpServletResponse response,
                                       InputStream in, String fileType, String fileName) {
        OutputStream out = null;
        // 获得文件
        try {
            // 定义输出类型(下载)
            if (fileType.toLowerCase().endsWith("zip")) {
                response.setContentType("application/x-zip-compressed");
            } else if (fileType.toLowerCase().endsWith("rar")) {
                response.setContentType("application/octet-stream");
            } else if (fileType.toLowerCase().endsWith("doc")) {
                response.setContentType("application/msword");
            } else if ((fileType.toLowerCase().endsWith("xls"))
                       || (fileType.toLowerCase().endsWith("csv"))) {
                response.setContentType("application/ms-excel ");
            } else if (fileType.toLowerCase().endsWith("pdf")) {
                response.setContentType("application/pdf");
            } else if (fileType.toLowerCase().endsWith("jpg")
                       || fileType.toLowerCase().endsWith("jpeg")
                       || fileType.toLowerCase().endsWith("png")) {
                response.setContentType("image/jpeg");
            } else {
                response.setContentType("application/x-msdownload");
            }
            String filename = URLEncoder.encode(fileName, "UTF-8");
            if (request.getHeader("user-agent").indexOf("MSIE") != -1) {
                filename = URLEncoder.encode(fileName, "UTF-8");
            } else {
                filename = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Location", filename);

            // 定义输出文件
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[2024];
            int i = -1;
            while ((i = in.read(buffer)) != -1) {
                bos.write(buffer, 0, i);
            }
            out = response.getOutputStream();
            out.write(bos.toByteArray());
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("ftp下载附件失败!", e);
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("导出在关闭IO流时异常!", e);
                }
            }
        }
    }
}
