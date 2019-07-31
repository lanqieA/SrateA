package com.rate.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.rate.util.IdRandomUtil;

@Component
@Order(1)
public class TestInitClass implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("≤‚ ‘1,commandLineRenner");
		int createIdCode = IdRandomUtil.createIdCode();
		System.out.println(createIdCode);
		System.out.println("≤‚ ‘ÃÌº”");
	}

}
