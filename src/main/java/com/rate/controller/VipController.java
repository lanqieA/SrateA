package com.rate.controller;

import java.util.List;

import javax.mail.Session;
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
	//��ѯ����vip
	@RequestMapping("/findAllVip")
	@ResponseBody
	public List<Vip> findAllVip() {
		List<Vip> vips = vipService.findAllVip();
		return vips;
	}
	//���vip
	@RequestMapping("/addVip")
	@ResponseBody
	public boolean addVip(Vip vip) {		
		vip.setId(IdRandomUtil.createIdCode());	
		System.out.println(vip);
		vipService.addVip(vip);		
		return true;
	}
	//����idɾ��vip
	@RequestMapping("/deleteVipById")
	@ResponseBody
	public boolean deleteVipById(int id) {
		System.out.println("id="+id);
		vipService.deleteVipById(id);
		return true;
	}
	//����id�޸���Ϣ
	
	@RequestMapping("/updateVip")
	@ResponseBody
	public boolean updateVip(Vip vip){
		System.out.println(vip);
		vipService.updateVip(vip);
		return true;
	}
	//����id����vip
	@RequestMapping("/findVipById")
	@ResponseBody
	public Vip findVipById(int id,HttpSession session){
		Vip vip = vipService.findVipById(id);
		session.setAttribute("vip", vip);
		return vip;
	}
	//����username����vip
	@RequestMapping("/findVipByUsername")
	@ResponseBody
	public boolean findVipByUsername(String username){
		Vip vip = vipService.findVipByUsername(username);
		if (vip==null) {
			return false;
		}
		return true;
	}
	//vip��¼�ķ���
	@RequestMapping("/findVipByUsernameAndPwd")
	@ResponseBody
	public Vip findVipByUsernameAndPwd(Vip vip,HttpSession session){
		Vip v = vipService.findVipByNameAndPwd(vip);
		if (v==null) {
			return null;
		}
		System.out.println(v);
		//���ҵ���vip�洢��session
		session.setAttribute("vip", v);
		return v;
	}
}
