package com.rate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.bean.Vip;
import com.rate.mapper.VipMapper;
import com.rate.service.VipService;

@Service
public class VipServiceImpl implements VipService{
	@Autowired
	private VipMapper vipMapper;
	//��ѯ������Ϣ
	@Override
	public List<Vip> findAllVip() {		
		return vipMapper.findAllVip();
	}
	//���vip
	@Override
	public void addVip(Vip vip) {
		vipMapper.addVip(vip);		
	}
	//����idɾ��vip
	@Override
	public void deleteVipById(int id) {
		vipMapper.deleteVipById(id);		
	}
	//����id�޸���Ϣ
	@Override
	public void updateVip(Vip vip) {
		vipMapper.updateVip(vip);		
	}
	//����id����vip
	@Override
	public Vip findVipById(int id) {
		Vip vip = vipMapper.findVipById(id);
		return vip;
	}
	//�����û�����ѯvip
	@Override
	public Vip findVipByUsername(String username) {
		Vip vip = vipMapper.findVipByUsername(username);
		return vip;
	}
	@Override
	public Vip findVipByNameAndPwd(Vip vip) {
		Vip nvip = vipMapper.findVipByNameAndPwd(vip);
		return nvip;
	}		
}
