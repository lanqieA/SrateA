package com.rate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.bean.Goods;
import com.rate.mapper.GoodsMapper;
import com.rate.service.GoodsService;
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsMapper goodsMapper;
	@Override
	public List<Goods> findAllGoods() {
		return goodsMapper.findAllGoods();
	}
	@Override
	public boolean deleteGoodsById(int go_id) {
		goodsMapper.deleteGoodsById(go_id);
		return true;
	}
	@Override
	public Goods findGoodsById(int go_id) {
		return goodsMapper.findGoodsById(go_id);
	}
	@Override
	public boolean addGoods(Goods goods) {
		goodsMapper.addGoods(goods);
		return true;
	}
	//�鿴����δ�ӵ��Ļ�Դ
	@Override
	public List<Goods> findNoOrderGoods() {
		List<Goods> noOrderGoods = goodsMapper.findNoOrderGoods();
		return noOrderGoods;
	}
	//˾���ӵ���״̬ת���ķ���
	@Override
	public void transStatusOrder(int go_id) {
		goodsMapper.transStatusOrder(go_id);
	}
	//˾���˵���״̬ת��Ϊ���ӵ�
	@Override
	public void transStatusNoOrder(int go_id) {
		goodsMapper.transStatusNoOrder(go_id);
	}
	//�û������Լ���id�����Լ��Ļ�Դ
	@Override
	public List<Goods> findGoodsByVip(int u_id) {
		List<Goods> vips = goodsMapper.findGoodsByVip(u_id);
		return vips;
	}
}
