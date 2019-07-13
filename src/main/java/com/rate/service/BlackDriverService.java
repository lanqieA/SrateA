package com.rate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rate.bean.BlackDriver;
import com.rate.bean.Driver;


public interface BlackDriverService {
	//�������е�driver
	public List<BlackDriver> findAllBlackDriver();
	//���һ�����󵽺�����
	public boolean addBlack(BlackDriver blackDriver);
	//����id����һ������������
	public BlackDriver findBlackById(int id);
	//����idɾ��һ������������
	public boolean deleteBlack(int id);
}
