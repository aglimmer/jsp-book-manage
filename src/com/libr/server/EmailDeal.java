package com.libr.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailDeal
 */
@WebServlet("/libr/EmailDeal")
public class EmailDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String codes;
    public static boolean isSuccess;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailDeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String emails=URLDecoder.decode(request.getParameter("email"),"utf-8");
		isSuccess = false;
		codes = "";
		SendEmail(emails);
		PrintWriter out = response.getWriter();
		if(isSuccess) {
			out.print("已成功向"+emails+"发送验证码"+codes);
		}else {
			out.print("验证码发送失败");
		}
		
		System.out.println("servlet被调用");
//		System.out.print("<p>已成功向"+emails+"发送验证码"+codes+"</p>");
				
	}
	public static void SendEmail(String to) {
			String from = "2220940197@qq.com";
			String password = "atpovqpughpmeahh";
//			String to = "3223782091@qq.com";
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
				codes = getCodes();
				message.setContent("<h4>尊敬的用户：<br><p>&nbsp;&nbsp;你好，这是一封来自系统服务器的消息，你此次操作的验证码是："+codes+
				",验证码有效时间60s,请勿告诉他人！<br>致学图书馆将为你提供优质的图书检索、借阅、查询等服务，欢迎访问！</p></h4>", "text/html;charset=utf-8");
				System.out.println("正在发送...");
				Transport.send(message);
		//		System.out.println("发送成功！");
				isSuccess = true;
			} catch (AddressException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				isSuccess = false;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				isSuccess = false;
			}
			
			
		}
	public static String getCodes() {
		Random rand = new Random();
		String str="";
		for(int i = 0;i<6;i++) {
		str+=rand.nextInt(10);
	}
		return str;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
