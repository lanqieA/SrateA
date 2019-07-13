package com.rate.controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 接收用户填写的,并通过Ajax发送的邮箱
 * 向邮箱发送验证码
 */
@Controller
public class EmailValidateCode {
	@RequestMapping(value="validateEmail",method=RequestMethod.POST)
	@ResponseBody
	protected void validateEmail(HttpSession session,String userEmail) throws ServletException, IOException {
		System.out.println("userEmail="+userEmail);		
		//构建邮件客户端对象
		HtmlEmail email = new HtmlEmail();
		//设置邮箱服务器地址
		email.setHostName("smtp.163.com");
		//设置服务器邮箱以及授权码(写自己开通服务器功能的邮箱地址)
		email.setAuthentication("18091189700@163.com","T19950107f");
		//设置邮件统一编码
		email.setCharset("UTF-8");
		try {
			//发件人
			email.setFrom("18091189700@163.com","rate平台");
			//收件人
			email.addTo(userEmail);
			//邮件标题
			email.setSubject("您的邮箱动态码,请注意查收");
			//邮件内容
			//生成验证码
			String emailCode = StringUtil.createValidateCode();
			email.setMsg("你的注册码为:"+emailCode);			
			//获取session对象,将邮箱验证码存入
			session.setAttribute("emailCode",emailCode);
			//发送
			email.send();
			//一分钟之后,从session中移除邮箱验证码
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					session.removeAttribute("emailCode");
				}
			}, 1000*60);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
