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
	//˾��ҵ��
	@Autowired
	private DriverService driverService;
	//���к�����˾��
	@RequestMapping("allBlackDriver")
	public String findAllBlackDriver(Model model){
		List<BlackDriver> blackDrivers = blackDriverService.findAllBlackDriver();
		model.addAttribute("blackDrivers", blackDrivers);
		System.out.println(blackDrivers);
		return "admin-all-blackDriver";
	}
	//��˾����ӵ�������,ͬʱ��driver��ɾ��
	@RequestMapping(value="addBlack/{id}",method=RequestMethod.POST)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.REPEATABLE_READ)
	public boolean addBlack(@PathVariable("id") int id){
		//�Ƚ��в���
		Driver driver = driverService.findDriverById(id);
		//��driver�е�����ɾ��
		driverService.deleteDriverById(id);
		//��װblackDriver����
		BlackDriver blackDriver = new BlackDriver(driver.getDr_id(),
												driver.getDr_name(), 
												driver.getDr_driverage(), 
												driver.getDr_drive(), 
												driver.getDr_amount(), 
												driver.getDr_phone(),
												driver.getDr_evaluate());
		//��������ӵ�������
		boolean addBlack = blackDriverService.addBlack(blackDriver);
		return addBlack;
	}
	//�Ƴ�������,ͬʱ��ӵ�driver
	@Transactional(propagation=Propagation.REQUIRED,
			isolation=Isolation.REPEATABLE_READ)
	@RequestMapping(value="recoverDriver/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean recover(@PathVariable("id") int id){
		//�ں��������ҵ�����
		BlackDriver blackDriver = blackDriverService.findBlackById(id);
		//��������
		Driver driver = new Driver(
				blackDriver.getDr_id(),
				blackDriver.getDr_name(), 
				blackDriver.getDr_driverage(), 
				blackDriver.getDr_drive(), 
				blackDriver.getDr_amount(), 
				blackDriver.getDr_phone(),
				blackDriver.getDr_evaluate());
		//��driver����Ӵ˶���
		driverService.recover(driver);
		System.out.println("���");
		//ɾ���������ж���
		boolean deleteBlack = blackDriverService.deleteBlack(id);
		return deleteBlack;//�����Ƿ��Ѿ�ɾ��
	}
}
