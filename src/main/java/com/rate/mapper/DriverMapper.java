package com.rate.mapper;

import java.util.List;

import com.rate.bean.BlackDriver;
import com.rate.bean.Car;
import com.rate.bean.Driver;
import com.rate.bean.Sign;

public interface DriverMapper {
	//查找所有的driver
	public List<Driver> findAllDriver();
	//根据id查找一个司机
	public Driver findDriverById(int id);
	//根据id删除一个司机
	public void deleteDriverById(int id);
	//解除黑名单,添加到driver
	public void recover(Driver driver);
	//将车添加到车表中
	public void addCar(Car car);
	//查看货物的货运详情
	public List<Sign> getShippingDetails(int g_id);
	//根据用户名和电话号码查找司机
	public Driver findDriverByNameAndPwd(Driver driver);
}
