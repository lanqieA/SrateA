package com.rate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.bean.Vip;
import com.rate.mapper.VipMapper;
import com.rate.service.VipService;

@Service
@Transactional
public class VipServiceImpl implements VipService{
	@Autowired
	private VipMapper vipMapper;
	//查询所有信息
	
	//添加vip
	@Override
	public void addVip(Vip vip) {
		vipMapper.addVip(vip);		
	}
	//根据id删除vip
	@Override
	public void deleteVipById(int id) {
		vipMapper.deleteVipById(id);		
	}
	//根据id修改信息
	@Override
	public void updateVip(Vip vip) {
		vipMapper.updateVip(vip);		
	}
	//根据id查找vip
	@Override
	public Vip findVipById(int id) {
		Vip vip = vipMapper.findVipById(id);
		return vip;
	}
	//根据用户名查询vip
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
	@Override
	public List<Map<String, Object>> findAllVip(HashMap<String, String> map) {
		List<Map<String, Object>> allVip = vipMapper.findAllVip(map);
		return allVip;
	}
	
		
}
