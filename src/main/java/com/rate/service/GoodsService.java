package com.rate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rate.bean.Goods;

@Service
public interface GoodsService {
	//��ѯ���л�Դ��Ϣ
	public List<Goods> findAllGoods();
	//���ݱ��ɾ��һ����Դ��Ϣ
	public boolean deleteGoodsById(int go_id);
	//���ݱ�Ų���һ��������Ϣ
	public Goods findGoodsById(int go_id);
	//���һ������
	public boolean addGoods(Goods goods);
	//�鿴����δ�ӵ��Ļ�Դ
	public List<Goods> findNoOrderGoods();
	//˾���ӵ���״̬ת��Ϊ�ѽӵ�
	public void transStatusOrder(int go_id);
	//˾���˵���״̬װ��λ���ӵ�
	public void transStatusNoOrder(int go_id);
	//�����û�id�����Լ��Ļ�Դ
	public List<Goods> findGoodsByVip(int u_id);
}
