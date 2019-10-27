package com.rate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.rate.config.Config;

@SpringBootApplication
@EnableConfigurationProperties({Config.class})
@MapperScan(basePackages = {"com.rate.mapper"})
public class App extends SpringBootServletInitializer{
	public static void main (String[] args) throws Exception{
		SpringApplication.run(App.class, args);
	}
	@Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	     return application.sources(App.class);
	}
}