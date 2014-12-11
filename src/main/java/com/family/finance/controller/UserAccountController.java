package com.family.finance.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody Long addAccount(@RequestParam Map<String, Object> params, HttpServletResponse response){
		System.out.println(params.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", "家庭财务提醒服务");
		String text = "苏超："+"</br>"+"您好!欢迎使用家庭财务管理系统,这里是财务管理系统的邮件服务!</br>"+"有任何问题您可以在这里跟我们联系!";
//		try {
//			sendEmailservice.sendHtmlEmail(map, "306338457@qq.com", text);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
		
		int b = 0;
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			in = new FileInputStream((String) params.get("imgsrc"));
			out = new FileOutputStream("E:\\study\\JAVA\\hello2.jpg");
			while((b=in.read()) != -1){
				out.write(b);
			}
			in.close();
			out.close();
		}catch(Exception e){
			System.out.println("找不到指定文件");
		}
		System.out.println("文件已复制");
		return userAccountService.addAccount(params);
	}

	@RequestMapping(value = "/getAll")
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
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)  
    public String upload(@RequestParam("file") CommonsMultipartFile[] files,@RequestParam Map<String, Object> params, HttpServletRequest request){  
        for(int i = 0;i<files.length;i++){  
            System.out.println("fileName---------->" + files[i].getOriginalFilename());  
          
            if(!files[i].isEmpty()){  
                int pre = (int) System.currentTimeMillis();  
                try {  
                    //拿到输出流，同时重命名上传的文件  
                    FileOutputStream os = new FileOutputStream("D:/" + new Date().getTime() + files[i].getOriginalFilename());  
                    //拿到上传文件的输入流  
                    FileInputStream in = (FileInputStream) files[i].getInputStream();  
                      
                    //以写字节的方式写文件  
                    int b = 0;  
                    while((b=in.read()) != -1){  
                        os.write(b);  
                    }  
                    os.flush();  
                    os.close();  
                    in.close();  
                    int finaltime = (int) System.currentTimeMillis();  
                    System.out.println(finaltime - pre);  
                      
                } catch (Exception e) {  
                    e.printStackTrace();  
                    System.out.println("上传出错");  
                }  
        }  
        }  
        return "/success";  
    }
}
