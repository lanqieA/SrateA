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
	//��������˾��
	@Override
	public List<Driver> findAllDriver() {
		List<Driver> drivers = driverMapper.findAllDriver();
		return drivers;
	}
	//����id����driver
	@Override
	public Driver findDriverById(int id) {
		Driver driver = driverMapper.findDriverById(id);
		return driver;
	}
	//����idɾ��driver
	@Override
	public void deleteDriverById(int id) {
		driverMapper.deleteDriverById(id);
	}
	@Override
	public void recover(Driver driver) {
		driverMapper.recover(driver);
	}
	//������ӵ�������
	@Override
	public boolean addCar(Car car) {
		driverMapper.addCar(car);
		return true;
	}
	//���ݻ���id,�鿴��������ķ���
	@Override
	public List<Sign> getShippingDetails(int g_id) {
		List<Sign> signs = driverMapper.getShippingDetails(g_id);
		return signs;
	}
	//������¼�ķ���
	@Override
	public Driver findDriverByNameAndPwd(Driver driver) {
		System.out.println("service��");
		Driver driver2 = driverMapper.findDriverByNameAndPwd(driver);
		return driver2;
	}
	
}
