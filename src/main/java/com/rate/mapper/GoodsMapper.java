package com.rate.mapper;

import java.util.List;

import com.rate.bean.Goods;

public interface GoodsMapper {
	//查询所有货源
	public List<Goods> findAllGoods();
	//根据货物编号删除一条数据
	public void deleteGoodsById(int go_id);
	//根据货物编号查找一条数据
	public Goods findGoodsById(int go_id);
	//添加一条数据
	public void addGoods(Goods goods);
	//查询所有未接单货源
	public List<Goods> findNoOrderGoods();
	//接单后将货源状态转换为已接单
	public void transStatusOrder(int go_id);
	//退单后将货源改为待接单
	public void transStatusNoOrder(int go_id);
	//根据用户id去查找用户的货物
	public List<Goods> findGoodsByVip(int u_id);
}
