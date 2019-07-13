package com.rate.mapper;

import java.util.List;

import com.rate.bean.Vip;

public interface VipMapper {
	//查询所有vip
	public List<Vip> findAllVip();
	//添加vip
	public void addVip(Vip vip);
	//根据id删除vip
	public void deleteVipById(int id);
	//根据id修改vip
	public void updateVip(Vip vip);
	//根据id查找vip
	public Vip findVipById(int id);
	//根据username查找vip
	public Vip findVipByUsername(String username);
	//登录方法
	public Vip findVipByNameAndPwd(Vip vip);
}
