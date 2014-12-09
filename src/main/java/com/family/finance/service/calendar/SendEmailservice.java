package com.family.finance.service.calendar;

import java.util.Map;

import javax.mail.MessagingException;

public interface SendEmailservice {
	
	public void sendHtmlEmail(Map<String, Object> map, String address, String htmlText) throws MessagingException;

}
