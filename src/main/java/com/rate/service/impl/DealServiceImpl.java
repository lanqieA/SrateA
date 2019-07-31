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
	//查找所有的deal
	@Override
	public List<Deal> findAllDeal(){
		System.out.println("service");
		return dealMapper.findAllDeal();
	}
	//司机接单后添加到deal表的方法
	@Override
	public boolean addDeal(Deal deal) {
		dealMapper.addDeal(deal);
		return true;
	}
	//根据车主id查找自己所有的交易
	@Override
	public List<Deal> findDealById(int d_id) {
		List<Deal> deals = dealMapper.findDealById(d_id);
		return deals;
	}
	//根据id删除交易记录
	@Override
	public void deleteDealById(int de_id) {
		dealMapper.deleteDealById(de_id);
	}
	//车主根据自己的id查找已经完成的订单的方法
	@Override
	public List<Deal> findFinashedDealByDriver(int dr_id) {
		List<Deal> deals = dealMapper.findFishedDealByDriver(dr_id);
		return deals;
	}
	//vip登录后查看自己所有交易的方法
	@Override
	public List<Deal> findDealByVip(int v_id) {
		List<Deal> deals = dealMapper.findDealByVip(v_id);
		return deals;
	}	
}
