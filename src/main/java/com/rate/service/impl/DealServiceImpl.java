package com.rate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.bean.Deal;
import com.rate.mapper.DealMapper;
import com.rate.service.DealService;
@Service
@Transactional
public class DealServiceImpl implements DealService{
	@Autowired
	private DealMapper dealMapper;
	//�������е�deal
	@Override
	public List<Deal> findAllDeal(){
		System.out.println("service");
		return dealMapper.findAllDeal();
	}
	//˾���ӵ�����ӵ�deal��ķ���
	@Override
	public boolean addDeal(Deal deal) {
		dealMapper.addDeal(deal);
		return true;
	}
	//���ݳ���id�����Լ����еĽ���
	@Override
	public List<Deal> findDealById(int d_id) {
		List<Deal> deals = dealMapper.findDealById(d_id);
		return deals;
	}
	//����idɾ�����׼�¼
	@Override
	public void deleteDealById(int de_id) {
		dealMapper.deleteDealById(de_id);
	}
	//���������Լ���id�����Ѿ���ɵĶ����ķ���
	@Override
	public List<Deal> findFinashedDealByDriver(int dr_id) {
		List<Deal> deals = dealMapper.findFishedDealByDriver(dr_id);
		return deals;
	}
	//vip��¼��鿴�Լ����н��׵ķ���
	@Override
	public List<Deal> findDealByVip(int v_id) {
		List<Deal> deals = dealMapper.findDealByVip(v_id);
		return deals;
	}	
}
