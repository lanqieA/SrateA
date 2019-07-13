package com.rate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rate.bean.BlackDriver;
import com.rate.bean.Driver;
import com.rate.service.BlackDriverService;
import com.rate.service.DriverService;

@Controller
public class BlackDriverController {
	@Autowired
	private BlackDriverService blackDriverService;
	//司机业务
	@Autowired
	private DriverService driverService;
	//所有黑名单司机
	@RequestMapping("allBlackDriver")
	public String findAllBlackDriver(Model model){
		List<BlackDriver> blackDrivers = blackDriverService.findAllBlackDriver();
		model.addAttribute("blackDrivers", blackDrivers);
		System.out.println(blackDrivers);
		return "admin-all-blackDriver";
	}
	//将司机添加到黑名单,同时在driver中删除
	@RequestMapping(value="addBlack/{id}",method=RequestMethod.POST)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.REPEATABLE_READ)
	public boolean addBlack(@PathVariable("id") int id){
		//先进行查找
		Driver driver = driverService.findDriverById(id);
		//将driver中的数据删除
		driverService.deleteDriverById(id);
		//封装blackDriver对象
		BlackDriver blackDriver = new BlackDriver(driver.getDr_id(),
												driver.getDr_name(), 
												driver.getDr_driverage(), 
												driver.getDr_drive(), 
												driver.getDr_amount(), 
												driver.getDr_phone(),
												driver.getDr_evaluate());
		//将对象添加到黑名单
		boolean addBlack = blackDriverService.addBlack(blackDriver);
		return addBlack;
	}
	//移除黑名单,同时添加到driver
	@Transactional(propagation=Propagation.REQUIRED,
			isolation=Isolation.REPEATABLE_READ)
	@RequestMapping(value="recoverDriver/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean recover(@PathVariable("id") int id){
		//在黑名单中找到对象
		BlackDriver blackDriver = blackDriverService.findBlackById(id);
		//创建对象
		Driver driver = new Driver(
				blackDriver.getDr_id(),
				blackDriver.getDr_name(), 
				blackDriver.getDr_driverage(), 
				blackDriver.getDr_drive(), 
				blackDriver.getDr_amount(), 
				blackDriver.getDr_phone(),
				blackDriver.getDr_evaluate());
		//在driver中添加此对象
		driverService.recover(driver);
		System.out.println("添加");
		//删除黑名单中对象
		boolean deleteBlack = blackDriverService.deleteBlack(id);
		return deleteBlack;//返回是否已经删除
	}
}
