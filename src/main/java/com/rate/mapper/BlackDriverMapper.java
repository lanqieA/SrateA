package com.rate.mapper;

import java.util.List;

import com.rate.bean.BlackDriver;

public interface BlackDriverMapper {
	//�������е�blackDriver
	public List<BlackDriver> findAllBlackDriver();
	//���һ�����󵽺�����
	public void addBlack(BlackDriver blackDriver);
	//����id����һ������������
	public BlackDriver findBlackById(int id);
	//����idɾ��һ������������
	public void deleteBlackById(int id);
}
