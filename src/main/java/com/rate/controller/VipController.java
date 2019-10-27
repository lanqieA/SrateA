package com.rate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rate.bean.Vip;
import com.rate.service.VipService;
import com.rate.util.IdRandomUtil;

@Controller
@RequestMapping("/vip")
public class VipController {
	@Autowired
	private VipService vipService;

	//查询所有vip
	@RequestMapping("/findAllVip")
	@ResponseBody
	public List<Map<String, Object>> findAllVip() {
		HashMap<String, String> tHashMap = new HashMap<>();
		tHashMap.put("id", "8");
		List<Map<String, Object>> vips = vipService.findAllVip(tHashMap);
		HashMap<String, String> hashMap = new HashMap<>();
		for (Map<String, Object> map : vips) {
			Set<Entry<String,Object>> entrySet = map.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				hashMap.put(entry.getKey(), String.valueOf(entry.getValue()));
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		}
		System.out.println(vips);
		System.out.println("---------------");
		System.out.println(hashMap);
		return vips;
	}
	//添加vip

	@RequestMapping("/addVip")
	@ResponseBody
	public boolean addVip(Vip vip) {		
		vip.setId(IdRandomUtil.createIdCode());	
		System.out.println(vip);
		vipService.addVip(vip);		
		return true;
	}

	//根据id删除vip
	@RequestMapping("/deleteVipById")
	@ResponseBody
	public boolean deleteVipById(int id) {
		System.out.println("id="+id);
		vipService.deleteVipById(id);
		return true;
	}
	
	@RequestMapping("/updateVip")
	@ResponseBody
	public boolean updateVip(Vip vip){
		System.out.println(vip);
		vipService.updateVip(vip);
		return true;
	}
	//根据id查找vip
	@RequestMapping("/findVipById")
	@ResponseBody
	public Vip findVipById(int id,HttpSession session){
		Vip vip = vipService.findVipById(id);
		session.setAttribute("vip", vip);
		return vip;
	}
	//根据username查找vip
	@RequestMapping("/findVipByUsername")
	@ResponseBody
	public boolean findVipByUsername(String username){
		Vip vip = vipService.findVipByUsername(username);
		if (vip==null) {
			return false;
		}
		return true;
	}

	//vip登录的方法

	@RequestMapping("/findVipByUsernameAndPwd")
	@ResponseBody
	public Vip findVipByUsernameAndPwd(Vip vip,HttpSession session,HttpServletRequest request){
		Vip v = vipService.findVipByNameAndPwd(vip);
		if (v==null) {
			return null;
		}
		System.out.println(v);
		
		//将找到的vip存储到session
		session.setAttribute("vip", v);
		return v;
	}
}
