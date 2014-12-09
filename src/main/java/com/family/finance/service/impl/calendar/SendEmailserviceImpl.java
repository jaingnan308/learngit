package com.family.finance.service.impl.calendar;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.family.finance.service.calendar.SendEmailservice;

@Service("sendEmailservice")
public class SendEmailserviceImpl implements SendEmailservice {
	
	private JavaMailSender    javaMailSender;

	@Override
	public void sendHtmlEmail(Map<String, Object> map, String address,
			String htmlText) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
        helper.setTo(address); // 邮件接收地址  
        helper.setFrom("shui_jiangnan@sina.cn"); // 邮件发送地址,这里必须和xml里的邮件地址相同一致  
        helper.setSubject(String.valueOf(map.get("subject"))); // 主题  
        helper.setText(htmlText, true); // 邮件内容，注意加参数true，表示启用html格式  
        javaMailSender.send(msg);
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	

}
