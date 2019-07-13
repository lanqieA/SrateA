package com.rate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.bean.Deal;
import com.rate.mapper.DealMapper;

@Service
public interface DealService {
	//查找所有的deal
	public List<Deal> findAllDeal();
	//司机接单后添加到交易的方法
	public boolean addDeal(Deal deal);
	//根据车主id查找自己所有的交易
	public List<Deal> findDealById(int d_id);
	//根据id删除一个交易记录
	public void deleteDealById(int de_id);
	//车手根据自己id查找已经完成的交易
	public List<Deal> findFinashedDealByDriver(int dr_id);
	//vip登录后查找自己所有交易的方法
	public List<Deal> findDealByVip(int v_id);
}
