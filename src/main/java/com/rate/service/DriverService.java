package com.rate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rate.bean.Car;
import com.rate.bean.Driver;
import com.rate.bean.Sign;


public interface DriverService {
	//查找所有的driver
	public List<Driver> findAllDriver();
	//根据id查找一个司机
	public Driver findDriverById(int id);
	//根据id删除一个司机
	public void deleteDriverById(int id);
	//解除黑名单时重新添加
	public void recover(Driver driver);
	//将车添加到车表中
	public boolean addCar(Car car);
	//根据g_id查看货运详情的方法
	public List<Sign> getShippingDetails(int g_id);
	//根据用户名和电话号码查找司机
	public Driver findDriverByNameAndPwd(Driver driver);
}
