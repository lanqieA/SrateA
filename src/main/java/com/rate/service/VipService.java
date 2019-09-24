package com.rate.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rate.bean.Vip;

@Service
public interface VipService {
	//查询所有用户信息
	public Map<Integer, Vip> findAllVip();
	//添加vip
	public void addVip(Vip vip);
	//根据id删除id
	public void deleteVipById(int id);
	//根据id修改信息
	public void updateVip(Vip vip);
	//根据id查找vip
	public Vip findVipById(int id);
	////根据用户名查询vip
	public Vip findVipByUsername(String username);
	//登录方法
	public Vip findVipByNameAndPwd(Vip vip);
}
