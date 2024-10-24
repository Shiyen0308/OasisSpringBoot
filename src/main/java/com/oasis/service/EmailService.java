package com.oasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	@Autowired
    private JavaMailSender mailSender;
	
	public void  SendCheckPassword (String toEmail,String code) {
		 SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("會員註冊驗證碼");
	        message.setText("您的驗證碼是：" + code + "，五分鐘內有效。");
	        mailSender.send(message);
	}
	
}
