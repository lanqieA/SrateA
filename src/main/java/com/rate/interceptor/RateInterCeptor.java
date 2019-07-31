/*package com.rate.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rate.bean.Driver;

public class RateInterCeptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		StringBuffer requestURL = request.getRequestURL();
		System.out.println(requestURL);
		for(int i = 0; i < 3; i++){
			requestURL = new StringBuffer(requestURL.substring(requestURL.indexOf("/")+1));
		}
		System.out.println(requestURL);
		HttpSession session = request.getSession();
		Object vip= session.getAttribute("vip");
		System.out.println(vip);
		Driver driver =(Driver)session.getAttribute("driver");
		System.out.println(driver);
		Object admin = session.getAttribute("admin");
		System.out.println(admin);
		String string = String.valueOf(requestURL);
		//À¹½ØÎ´µÇÂ¼µÄÇëÇó
		if (vip==null&&driver==null&&admin==null) {
			System.out.println("À¹½ØÆ÷");
			//×¢²áµÇÂ¼²»À¹½Ø
			if (string.equals("driver-login")||string.equals("admin-login")
					||string.equals("vip/findVipByUsernameAndPwd")||string.equals("vip/findVipByUsername")) {
				System.out.println("ÅÐ¶Ï");
				return true;
			}
			response.sendRedirect("login.html");
			return false;
		}
		System.out.println("aa");
		return true;
	}
}*/