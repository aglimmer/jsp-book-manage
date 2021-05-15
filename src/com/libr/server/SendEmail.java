package com.libr.server;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void main(String[] args) {
		String from = "2220940197@qq.com";
		String password = "atpovqpughpmeahh";
		String to = "3223782091@qq.com";
		// 指定发送邮件的主机
		String host = "smtp.qq.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth",true);
		Session session = Session.getDefaultInstance(properties,new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		 // 创建默认的 MimeMessage 对象
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			 // Set Subject: 头部头字段
			message.setSubject("关于来自致学图书馆的通知","utf-8");
	//		message.setText("天长地久有时尽，此恨绵绵无绝期");
			message.setContent("<h4>尊敬的用户：<br><p>&nbsp;&nbsp;你好，这是一封来自系统服务器的消息，你此次操作的验证码是：132244，"+
			"验证码有效时间90s,请勿告诉他人！<br>致学图书馆将为你提供优质的图书检索、借阅、查询等服务，欢迎访问！</p></h4>", "text/html;charset=utf-8");
			System.out.println("正在发送...");
			Transport.send(message);
			System.out.println("发送成功！");
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
