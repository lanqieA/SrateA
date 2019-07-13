package com.rate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.rate.bean.Goods;
import com.rate.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/findAllGoods")
	@ResponseBody
	public List<Goods> findAllGoods(){
		List<Goods> goods = goodsService.findAllGoods();
		return goods;
	}
	@RequestMapping("deleteGoodsById")
	@ResponseBody
	public boolean deleteGoodsById(int go_id){
		goodsService.deleteGoodsById(go_id);
		return true;
	}
	@RequestMapping("findGoodsById"/*value="findGoodsById/{go_id}"*/)
	@ResponseBody
	public Goods findGoodsById(int go_id){
		System.out.println(1121);
		return goodsService.findGoodsById(go_id);
	}
	@RequestMapping("addUser")
	@ResponseBody
	public boolean addGoods(Goods goods){
		goodsService.addGoods(goods);
		return true;
	}
}
