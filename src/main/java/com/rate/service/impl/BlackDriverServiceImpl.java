package com.rate.service.impl;

import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.bean.BlackDriver;
import com.rate.bean.Driver;
import com.rate.mapper.BlackDriverMapper;
import com.rate.mapper.DriverMapper;
import com.rate.service.BlackDriverService;
import com.rate.service.DriverService;

@Service
@Transactional
public class BlackDriverServiceImpl implements BlackDriverService{
	@Autowired
	private BlackDriverMapper blackDriverMapper;
	@Override
	public List<BlackDriver> findAllBlackDriver() {
		List<BlackDriver> blackDrivers = blackDriverMapper.findAllBlackDriver();
		return blackDrivers;
	}
	//添加到黑名单
	@Override
	public boolean addBlack(BlackDriver blackDriver) {
		blackDriverMapper.addBlack(blackDriver);
		return true;
	}
	//根据id查找黑名单对象
	@Override
	public BlackDriver findBlackById(int id) {
		BlackDriver blackDriver = blackDriverMapper.findBlackById(id);
		return blackDriver;
	}
	@Override
	public boolean deleteBlack(int id) {
		blackDriverMapper.deleteBlackById(id);
		return true;
	}
	
}
