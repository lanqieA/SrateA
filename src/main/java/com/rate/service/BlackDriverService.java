package com.rate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rate.bean.BlackDriver;
import com.rate.bean.Driver;


public interface BlackDriverService {
	//查找所有的driver
	public List<BlackDriver> findAllBlackDriver();
	//添加一个对象到黑名单
	public boolean addBlack(BlackDriver blackDriver);
	//根据id查找一个黑名单对象
	public BlackDriver findBlackById(int id);
	//根据id删除一个黑名单对象
	public boolean deleteBlack(int id);
}
