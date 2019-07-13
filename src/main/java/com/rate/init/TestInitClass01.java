package com.rate.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.rate.util.IdRandomUtil;

@Component
@Order(2)
public class TestInitClass01 implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("测试初始化服务2,commandLineRunner");
		int createIdCode = IdRandomUtil.createIdCode();
		System.out.println(createIdCode);
		
	}

}
