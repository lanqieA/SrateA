package com.rate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rate.bean.Goods;

@Service
public interface GoodsService {
	//查询所有货源信息
	public List<Goods> findAllGoods();
	//根据编号删除一条货源信息
	public boolean deleteGoodsById(int go_id);
	//根据编号查找一条货物信息
	public Goods findGoodsById(int go_id);
	//添加一条数据
	public boolean addGoods(Goods goods);
	//查看所有未接单的货源
	public List<Goods> findNoOrderGoods();
	//司机接单后将状态转换为已接单
	public void transStatusOrder(int go_id);
	//司机退单后将状态装换位待接单
	public void transStatusNoOrder(int go_id);
	//根据用户id查找自己的货源
	public List<Goods> findGoodsByVip(int u_id);
}
