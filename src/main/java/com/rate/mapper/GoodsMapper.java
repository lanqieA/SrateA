package com.rate.mapper;

import java.util.List;

import com.rate.bean.Goods;

public interface GoodsMapper {
	//��ѯ���л�Դ
	public List<Goods> findAllGoods();
	//���ݻ�����ɾ��һ������
	public void deleteGoodsById(int go_id);
	//���ݻ����Ų���һ������
	public Goods findGoodsById(int go_id);
	//���һ������
	public void addGoods(Goods goods);
	//��ѯ����δ�ӵ���Դ
	public List<Goods> findNoOrderGoods();
	//�ӵ��󽫻�Դ״̬ת��Ϊ�ѽӵ�
	public void transStatusOrder(int go_id);
	//�˵��󽫻�Դ��Ϊ���ӵ�
	public void transStatusNoOrder(int go_id);
	//�����û�idȥ�����û��Ļ���
	public List<Goods> findGoodsByVip(int u_id);
}
