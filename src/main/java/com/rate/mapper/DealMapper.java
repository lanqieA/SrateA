package com.rate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rate.bean.Deal;
public interface DealMapper {
	//�������еĽ�������
	public List<Deal> findAllDeal();
	//˾���ӵ�����ӵ����ױ���
	public void addDeal(Deal deal);
	//���ݳ���id���ҽ���
	public List<Deal> findDealById(int d_id);
	//���ݽ���idɾ�����׼�¼
	public void deleteDealById(int de_id);
	//���ֲ鿴�Լ��Ѿ���ɵĽ���
	public List<Deal> findFishedDealByDriver(int dr_id);
	//vip��¼��鿴�Լ��Ľ���
	public List<Deal> findDealByVip(int v_ip);
}
