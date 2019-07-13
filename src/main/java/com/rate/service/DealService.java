package com.rate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.bean.Deal;
import com.rate.mapper.DealMapper;

@Service
public interface DealService {
	//�������е�deal
	public List<Deal> findAllDeal();
	//˾���ӵ�����ӵ����׵ķ���
	public boolean addDeal(Deal deal);
	//���ݳ���id�����Լ����еĽ���
	public List<Deal> findDealById(int d_id);
	//����idɾ��һ�����׼�¼
	public void deleteDealById(int de_id);
	//���ָ����Լ�id�����Ѿ���ɵĽ���
	public List<Deal> findFinashedDealByDriver(int dr_id);
	//vip��¼������Լ����н��׵ķ���
	public List<Deal> findDealByVip(int v_id);
}
