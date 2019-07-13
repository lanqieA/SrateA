package com.rate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rate.bean.Car;
import com.rate.bean.Driver;
import com.rate.bean.Sign;


public interface DriverService {
	//�������е�driver
	public List<Driver> findAllDriver();
	//����id����һ��˾��
	public Driver findDriverById(int id);
	//����idɾ��һ��˾��
	public void deleteDriverById(int id);
	//���������ʱ�������
	public void recover(Driver driver);
	//������ӵ�������
	public boolean addCar(Car car);
	//����g_id�鿴��������ķ���
	public List<Sign> getShippingDetails(int g_id);
	//�����û����͵绰�������˾��
	public Driver findDriverByNameAndPwd(Driver driver);
}
