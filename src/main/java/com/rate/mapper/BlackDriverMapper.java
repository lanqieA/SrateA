package com.rate.mapper;

import java.util.List;

import com.rate.bean.BlackDriver;

public interface BlackDriverMapper {
	//查找所有的blackDriver
	public List<BlackDriver> findAllBlackDriver();
	//添加一个对象到黑名单
	public void addBlack(BlackDriver blackDriver);
	//根据id查找一个黑名单对象
	public BlackDriver findBlackById(int id);
	//根据id删除一个黑名单对象
	public void deleteBlackById(int id);
}
