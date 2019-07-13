package com.rate.mapper;

import java.util.List;

import com.rate.bean.BlackDriver;
import com.rate.bean.Car;
import com.rate.bean.Driver;
import com.rate.bean.Sign;

public interface DriverMapper {
	//�������е�driver
	public List<Driver> findAllDriver();
	//����id����һ��˾��
	public Driver findDriverById(int id);
	//����idɾ��һ��˾��
	public void deleteDriverById(int id);
	//���������,��ӵ�driver
	public void recover(Driver driver);
	//������ӵ�������
	public void addCar(Car car);
	//�鿴����Ļ�������
	public List<Sign> getShippingDetails(int g_id);
	//�����û����͵绰�������˾��
	public Driver findDriverByNameAndPwd(Driver driver);
}
