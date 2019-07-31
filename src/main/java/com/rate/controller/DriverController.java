package com.rate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.internet.ContentType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rate.bean.Car;
import com.rate.bean.Deal;
import com.rate.bean.Driver;
import com.rate.bean.Goods;
import com.rate.service.DriverService;
import com.rate.service.GoodsService;
import com.rate.util.FileController;
import com.rate.util.IdRandomUtil;

@Controller
public class DriverController {
	@Autowired
	private DriverService driverService;
	@Autowired
	private GoodsService goodsService;
	//�������л�Դ����Ϣ
	@RequestMapping("allDriver")
	public String findAllDriver(Model model){
		List<Driver> drivers = driverService.findAllDriver();
		model.addAttribute("drivers", drivers);
		return "admin-all-driver";
	}
	//������¼���ҳ��
	@RequestMapping("driver-all-good")
	public String driverAllGoods(Model model){
		List<Goods> goods = goodsService.findNoOrderGoods();
		model.addAttribute("goods", goods);
		System.out.println(goods);
		return "driver-all-goods";
	}
	@RequestMapping(value="driver-findGood/{go_id}",method=RequestMethod.GET)
	@ResponseBody
	//����������������鿴��ǰ�Ļ�������
	public Goods findGoodByDriver(@PathVariable int go_id){
		System.out.println(go_id);
		Goods goods = goodsService.findGoodsById(go_id);
		System.out.println(goods);
		return goods;
	}
	@RequestMapping(value="addCar",method=RequestMethod.POST)
	@ResponseBody
	//��������������Ϣ
	public boolean addCar(Car car) throws IllegalStateException, IOException{
		int idCode = IdRandomUtil.createIdCode();
		car.setCar_id(idCode);
		System.out.println(car);
		//������ӵ�����
		boolean addCar = driverService.addCar(car);
		return addCar;
	}
	//������¼�ķ���
	@RequestMapping("driver-login")
	@ResponseBody
	public Driver findDriverByNameAndPwd(String username,String password,HttpSession session){
		System.out.println(username+"/"+password);
		Driver driver = new Driver();
		driver.setDr_name(username);
		driver.setDr_phone(Long.parseLong(password));
		Driver d = driverService.findDriverByNameAndPwd(driver);
		session.setAttribute("driver", d);
		return d;
	}
	@RequestMapping("unLogin")
	public String unLogin(HttpSession session){
		session.removeAttribute("driver");
		return "redirect:driverLogin.html";
	}
	@RequestMapping(value="admin-login",method=RequestMethod.POST)
	@ResponseBody
	//����Ա��¼�ķ���
	public boolean adminLogin(@RequestParam(value="username")String username,@RequestParam(value="password")String password,HttpSession session){
		if (username.equals("admin")&&password.equals("admin")) {
			session.setAttribute("admin", "admin");
			return true;
		}
		return false;
	}
}
