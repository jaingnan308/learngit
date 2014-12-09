package com.family.finance.service.impl.calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.family.finance.model.Calendar;
import com.family.finance.service.calendar.CalendarService;
import com.family.finance.service.calendar.MailRemindService;
import com.family.finance.service.calendar.SendEmailservice;

@Service("mailRemindService")
public class MailRemindServiceImpl implements MailRemindService {
	
	private CalendarService calendarService;
	private SendEmailservice sendEmailservice;
	
	public SendEmailservice getSendEmailservice() {
		return sendEmailservice;
	}
	public void setSendEmailservice(SendEmailservice sendEmailservice) {
		this.sendEmailservice = sendEmailservice;
	}
	public CalendarService getCalendarService() {
		return calendarService;
	}
	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@Override
	public void sendEmailRemind() {
		System.out.println("########################我是邮件提醒##########################");
		System.out.println("########################我是邮件提醒##########################");
		System.out.println("########################我是邮件提醒##########################");
		System.out.println("########################我是邮件提醒##########################");
		System.out.println("########################我是邮件提醒##########################");
		List<Calendar>  list = calendarService.getRemindByThisTime();
		for(Calendar calendar : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("subject", "家庭财务提醒服务");
			String address = "469763044@qq.com";
			String htmlText = "您好，这是家庭财务管理的测试邮件,</br> 您有一个日程提醒 标题为：" + calendar.getTitle()
			 + "</br> 内容为 ： "+ calendar.getDetails() + "</br> 开始时间为：" + calendar.getStart();
			try {
				sendEmailservice.sendHtmlEmail(map, address, htmlText);
				System.out.println("########################已经发送一条邮件服务##########################");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
