package com.rate.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import javax.websocket.server.PathParam;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.rate.advice.errorCode.ErrorCode;
import com.rate.bean.Deal;
import com.rate.bean.Driver;
import com.rate.bean.Goods;
import com.rate.bean.Sign;
import com.rate.bean.Vip;
import com.rate.service.DealService;
import com.rate.service.DriverService;
import com.rate.service.GoodsService;
import com.rate.service.VipService;
import com.rate.util.IdRandomUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;



@Controller
@Api(value="dealController",description="The Deal Controller Description")
public class DealController {
	private Logger logger = LoggerFactory.getLogger(DealController.class);
	
	//对dealService进行依赖
	@Autowired
	private DealService dealService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private DriverService driverService;
	@Autowired
	private VipService vipservice;
	//访问主页面
	@RequestMapping("/admin")
	public String admin(){
		System.out.println("测试");
		return "admin";
	}
	//查看所有的交易
	@RequestMapping("/findAllDeal")
	@ApiOperation(value="获取所有的交易",notes="")
	public String findAllDeal(Model model,int num,HttpSession session){
		System.out.println("==controller==");
		//开启物理分页
		PageHelper.startPage(num, 10);
		List<Deal> deals = dealService.findAllDeal();
		System.out.println(ErrorCode.NO_USER_EXCEPTION);
		logger.info("所有的交易为：{}", deals);
		try {
			testJob();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("deals", deals);	
		session.setAttribute("pnum", num);
		return "admin-all-deals";
	}
	private void testJob() throws SchedulerException{
		//创建jobDetail对象
				JobDetail jobDetail = JobBuilder.newJob(com.rate.job.HelloJob.class)
						.withIdentity("cronJob").build();
				//每天的9:40触发任务
				CronTrigger cronTrigger = 
						TriggerBuilder.newTrigger().withIdentity("cronTrigger")
						.withSchedule(CronScheduleBuilder
								.cronSchedule("0,10,20,30,40,50 * * * * ? ")).build();
				//Schedule实例
				Scheduler scheduler =  StdSchedulerFactory.getDefaultScheduler();
				scheduler.scheduleJob(jobDetail, cronTrigger);
				scheduler.start();
	}
	//分页要求来给你一个页数
	@RequestMapping("pageDeals")
	@ResponseBody
	public int getPageDeal(Model model,int num,HttpSession session){
		System.out.println(num);
		System.out.println("来了");
		Integer pnum = (Integer)session.getAttribute("pnum");
		pnum = num + pnum;
		if (pnum<1) {
			pnum = 1;
		}
		return pnum;
	}
	//点击查看交易详情时返回数据,方法待返回
	@ApiOperation(value="获取订单",notes="根据id获取对应的订单")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value="findVById/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Vip findVById(@PathVariable("id")int id){
		System.out.println(id);
		Vip vip = vipservice.findVipById(id);
		System.out.println(vip);
		return vip;
	}
	//点击查看货物详情时返回数据,方法待返回
		@RequestMapping(value="findGById/{id}",method=RequestMethod.GET)
		@ResponseBody
		public Goods findGById(@PathVariable("id")int id){
			System.out.println(id);
			Goods goods = goodsService.findGoodsById(id);
			System.out.println(goods);
			return goods;
		}
		//点击查看车手详情时返回数据,方法待返回
		@RequestMapping(value="findDById/{id}",method=RequestMethod.GET)
		@ResponseBody
		public Driver findDById(@PathVariable("id")int id){
				System.out.println(id);
				Driver driver = driverService.findDriverById(id);
				System.out.println(driver);
				return driver;
		}
		//所有货源页面查看
		@RequestMapping("allGoods")
		public String findAllG(Model model){
			List<Goods> goods = goodsService.findAllGoods();
			model.addAttribute("goods", goods);
			return "admin-all-goods";
		}
		//查看所有车主,未完成
		@RequestMapping("allDrivers")
		public String findAllD(Model model){
			return "allDrivers";
		}
		//司机接单后添加到交易表
		//当需要传多个参数时,前台可以直接按顺序写进来只需要用/来分割,后台按照顺序接收数据
		@RequestMapping(value="addDeal/{go_id}/{v_id}/{d_id}",method=RequestMethod.POST)
		@ResponseBody
		public boolean addDeal(Deal deal,HttpSession session) {
			//给deal对象添加交易id
			int idCode = IdRandomUtil.createIdCode();
			deal.setDe_id(idCode);
			//打印deal
			System.out.println(deal);
			//接单后添加到交易表中
			boolean addDeal = dealService.addDeal(deal);
			//货源表中将货源状态转换为已接单
			System.out.println(deal.getGo_id());
			goodsService.transStatusOrder(deal.getGo_id());
			//查看我的交易
			int d_id = deal.getD_id();
			List<Deal> deals = dealService.findDealById(d_id);
			//将我的交易添加到session
			session.setAttribute("deals", deals);
			System.out.println(deals);
			return addDeal;
		}
		@RequestMapping(value="getMyDeal",method=RequestMethod.GET)
		//司机点击接单后获得自己的接单
		public String getMyDeal(HttpSession session,Model model){
			List<Deal> attribute = (List<Deal>) session.getAttribute("deals");
			List<Deal> deals = attribute;
			model.addAttribute("deals", deals);
			return "freight-details";
		}
		@RequestMapping(value="getMyDealD",method=RequestMethod.GET)
		//司机在登录页面直接查看自己的接单
		public String getMyDealD(Model model,HttpSession session){
			Driver driver = (Driver)session.getAttribute("driver");
			System.out.println("获得"+driver);
			int d_id = driver.getDr_id();
			List<Deal> deals = dealService.findDealById(d_id);
			model.addAttribute("deals", deals);
			return "freight-details";
		}
		@RequestMapping("removeMyDeal")
		@ResponseBody
		//车主取消接单后的操作
		public boolean removeMyDeal(Deal deal){
			//将交易表中的记录删除
			dealService.deleteDealById(deal.getDe_id());
			//在货源表中状态改为待接单
			goodsService.transStatusNoOrder(deal.getGo_id());
			return true;
		}
		//车主点击后查看已经完成的交易
		@RequestMapping("driver-finishDeal")
		public String findFinashedDealByDriver(HttpSession session,Model model){
			Driver driver = (Driver)session.getAttribute("driver");
			List<Deal> deals = dealService.findFinashedDealByDriver(driver.getDr_id());
			model.addAttribute("deals", deals);
			return "driver-findFinishedDeal";
		}
		//查看自己的交易的方法
		@RequestMapping("findDealByVip")
		public String findDealByVip(HttpSession session,Model model){
			Vip vip = (Vip)session.getAttribute("vip");
			System.out.println("获得"+vip);
			List<Deal> deals = dealService.findDealByVip(vip.getId());
			model.addAttribute("deals", deals);
			return "vip-deals";
		}
		//vip查询某个货运详情的方法
		@GetMapping(value="shippingDetails/{g_id}")
		@ResponseBody
		public List<Sign> getShippingDetailsByVip(@PathVariable("g_id")int g_id){
			List<Sign> signs = driverService.getShippingDetails(g_id);
			System.out.println(signs);
			return signs;
		}
		//vip发布货源的方法
		@RequestMapping("addGoodsByVip")
		@ResponseBody
		public boolean addGoodsByVip(Goods goods,HttpSession session){
			System.out.println(goods);
			//随机生成id,付给货物
			int idCode = IdRandomUtil.createIdCode();
			goods.setGo_id(idCode);
			//取得用户id和name赋值给goods
			Vip vip = (Vip)session.getAttribute("vip");
			goods.setU_id(vip.getId());
			goods.setU_name(vip.getName());
			goods.setGo_state("待接单");
			goodsService.addGoods(goods);
			return true;
		}
		@RequestMapping("findGoodsByVip")
		//vip查找自己货源的方法
		public String findGoodsByVip(HttpSession session,Model model){
			Vip vip = (Vip)session.getAttribute("vip");
			List<Goods> goods = goodsService.findGoodsByVip(vip.getId());
			model.addAttribute("goods", goods);
			System.out.println(goods);
			return "vip-goods";
		}
		//vip删除已经发布的信息
		@RequestMapping("removeGoodsByVip")
		@ResponseBody
		public boolean removeGoodsByVip(int g_id){
			boolean deleteGoodsById = goodsService.deleteGoodsById(g_id);
			return deleteGoodsById;
		}
}
