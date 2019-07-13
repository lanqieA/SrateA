package com.rate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rate.bean.Deal;
public interface DealMapper {
	//查找所有的交易数据
	public List<Deal> findAllDeal();
	//司机接单后添加到交易表中
	public void addDeal(Deal deal);
	//根据车主id查找交易
	public List<Deal> findDealById(int d_id);
	//根据交易id删除交易记录
	public void deleteDealById(int de_id);
	//车手查看自己已经完成的交易
	public List<Deal> findFishedDealByDriver(int dr_id);
	//vip登录后查看自己的交易
	public List<Deal> findDealByVip(int v_ip);
}
