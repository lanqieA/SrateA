package com.rate.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
@Component
public class TestInitClass03ServletcontextListener implements ServletContextAware{

	@Override
	public void setServletContext(ServletContext arg0) {
		System.out.println("servletcontexttaware");
		
	}

	

}
