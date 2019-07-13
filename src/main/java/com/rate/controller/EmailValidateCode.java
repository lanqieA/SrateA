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
 * �����û���д��,��ͨ��Ajax���͵�����
 * �����䷢����֤��
 */
@Controller
public class EmailValidateCode {
	@RequestMapping(value="validateEmail",method=RequestMethod.POST)
	@ResponseBody
	protected void validateEmail(HttpSession session,String userEmail) throws ServletException, IOException {
		System.out.println("userEmail="+userEmail);		
		//�����ʼ��ͻ��˶���
		HtmlEmail email = new HtmlEmail();
		//���������������ַ
		email.setHostName("smtp.163.com");
		//���÷����������Լ���Ȩ��(д�Լ���ͨ���������ܵ������ַ)
		email.setAuthentication("18091189700@163.com","T19950107f");
		//�����ʼ�ͳһ����
		email.setCharset("UTF-8");
		try {
			//������
			email.setFrom("18091189700@163.com","rateƽ̨");
			//�ռ���
			email.addTo(userEmail);
			//�ʼ�����
			email.setSubject("�������䶯̬��,��ע�����");
			//�ʼ�����
			//������֤��
			String emailCode = StringUtil.createValidateCode();
			email.setMsg("���ע����Ϊ:"+emailCode);			
			//��ȡsession����,��������֤�����
			session.setAttribute("emailCode",emailCode);
			//����
			email.send();
			//һ����֮��,��session���Ƴ�������֤��
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
