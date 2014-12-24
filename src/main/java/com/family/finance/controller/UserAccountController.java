package com.family.finance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.family.finance.model.AccountChartVO;
import com.family.finance.model.easyui.Grid;
import com.family.finance.service.UserAccountService;
import com.family.finance.service.calendar.SendEmailservice;

@Controller
@RequestMapping(value = "/account")
public class UserAccountController {

	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	@Autowired
	@Qualifier("sendEmailservice")
	private SendEmailservice sendEmailservice;
	private Grid grid = new Grid();

	/**
	 * 添加资金账单信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Long addAccount(@RequestParam Map<String, Object> params,@RequestParam("file") CommonsMultipartFile[] myfiles, HttpServletResponse response, HttpServletRequest request){
		System.out.println(params.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", "家庭财务提醒服务");
		String text = "苏超："+"</br>"+"您好!欢迎使用家庭财务管理系统,这里是财务管理系统的邮件服务!</br>"+"有任何问题您可以在这里跟我们联系!";
//		try {
//			sendEmailservice.sendHtmlEmail(map, "306338457@qq.com", text);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//		
		StringBuffer imgsrc = new StringBuffer("");
		for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){  
                System.out.println("文件未上传");
                params.put("imageSrc", "");
            }else{  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件原名: " + myfile.getOriginalFilename());  
                System.out.println("========================================");  
                String oldname = myfile.getOriginalFilename();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                imgsrc.append(sdf.format(date));
                imgsrc.append("-");
                imgsrc.append((int)(Math.random()*100));
                imgsrc.append(oldname.substring(oldname.length()-4));
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\accountImg文件夹中  
                String realPath = request.getSession().getServletContext().getRealPath("/accountImg");  
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                try {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, imgsrc.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				} 
                System.out.println("src="+imgsrc.toString());
                params.put("imageSrc", "/accountImg/"+imgsrc.toString());
            }  
        }  
		return userAccountService.addAccount(params);
	}

	@RequestMapping(value = "/getByCon.do", method= RequestMethod.POST)
	public @ResponseBody Grid queryFinanceByCondition(@RequestParam Map<String, Object> params) throws IOException {
		grid.setRows(userAccountService.queryFinanceByCondition(params));
		grid.setTotal(userAccountService.queryFinanceCountByCondition(params));
		return grid;
	}
	
	/**
	 * 资金统计饼状图
	 * @return
	 */
	@RequestMapping(value = "/chart",method = RequestMethod.POST)
	public @ResponseBody List<AccountChartVO> accountChart(){
		return userAccountService.queryAccountChart();
	}
	
	/**
	 * 资金统计饼状图
	 * @return
	 */
	@RequestMapping(value = "/excelAccount.do",method = RequestMethod.POST)
	public void excelAccount(HttpServletResponse response, HttpServletRequest request){
		System.out.println("我要导出表格了 ");
		
		 Map<String, Object> map = new HashMap<String, Object>();
		 String tempPath = request.getRealPath("") + File.separator + "excelTemplates" + File.separator + "资金账单模板.xlsx";
//	        String tempPath = ServletActionContext.getServletContext().getRealPath("") + File.separator
//	                          + "excelTemplates" + File.separator + "已支付交易单导出模板.xlsx";
	        String realPath = request.getRealPath("/") + File.separator;
	        
            map.put("realPath", realPath);
            map.put("tempPath", tempPath);
		 userAccountService.exportPaidOrderExcel(map, request, response);
	}
	
}
