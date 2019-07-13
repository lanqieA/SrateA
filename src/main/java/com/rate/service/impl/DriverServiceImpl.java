package com.rate.service.impl;

import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.bean.Car;
import com.rate.bean.Driver;
import com.rate.bean.Sign;
import com.rate.mapper.DriverMapper;
import com.rate.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{
	@Autowired
	private DriverMapper driverMapper;
	//查找所有司机
	@Override
	public List<Driver> findAllDriver() {
		List<Driver> drivers = driverMapper.findAllDriver();
		return drivers;
	}
	//根据id查找driver
	@Override
	public Driver findDriverById(int id) {
		Driver driver = driverMapper.findDriverById(id);
		return driver;
	}
	//根据id删除driver
	@Override
	public void deleteDriverById(int id) {
		driverMapper.deleteDriverById(id);
	}
	@Override
	public void recover(Driver driver) {
		driverMapper.recover(driver);
	}
	//将车添加到车表中
	@Override
	public boolean addCar(Car car) {
		driverMapper.addCar(car);
		return true;
	}
	//根据货物id,查看货运详情的方法
	@Override
	public List<Sign> getShippingDetails(int g_id) {
		List<Sign> signs = driverMapper.getShippingDetails(g_id);
		return signs;
	}
	//车主登录的方法
	@Override
	public Driver findDriverByNameAndPwd(Driver driver) {
		System.out.println("service层");
		Driver driver2 = driverMapper.findDriverByNameAndPwd(driver);
		return driver2;
	}
	
}
