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
	
	//��dealService��������
	@Autowired
	private DealService dealService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private DriverService driverService;
	@Autowired
	private VipService vipservice;
	//������ҳ��
	@RequestMapping("/admin")
	public String admin(){
		System.out.println("����");
		return "admin";
	}
	//�鿴���еĽ���
	@RequestMapping("/findAllDeal")
	@ApiOperation(value="��ȡ���еĽ���",notes="")
	public String findAllDeal(Model model,int num,HttpSession session){
		System.out.println("==controller==");
		//���������ҳ
		PageHelper.startPage(num, 10);
		List<Deal> deals = dealService.findAllDeal();
		System.out.println(ErrorCode.NO_USER_EXCEPTION);
		logger.info("���еĽ���Ϊ��{}", deals);
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
		//����jobDetail����
				JobDetail jobDetail = JobBuilder.newJob(com.rate.job.HelloJob.class)
						.withIdentity("cronJob").build();
				//ÿ���9:40��������
				CronTrigger cronTrigger = 
						TriggerBuilder.newTrigger().withIdentity("cronTrigger")
						.withSchedule(CronScheduleBuilder
								.cronSchedule("0,10,20,30,40,50 * * * * ? ")).build();
				//Scheduleʵ��
				Scheduler scheduler =  StdSchedulerFactory.getDefaultScheduler();
				scheduler.scheduleJob(jobDetail, cronTrigger);
				scheduler.start();
	}
	//��ҳҪ��������һ��ҳ��
	@RequestMapping("pageDeals")
	@ResponseBody
	public int getPageDeal(Model model,int num,HttpSession session){
		System.out.println(num);
		System.out.println("����");
		Integer pnum = (Integer)session.getAttribute("pnum");
		pnum = num + pnum;
		if (pnum<1) {
			pnum = 1;
		}
		return pnum;
	}
	//����鿴��������ʱ��������,����������
	@ApiOperation(value="��ȡ����",notes="����id��ȡ��Ӧ�Ķ���")
	@ApiImplicitParam(name = "id", value = "�û�ID", required = true, dataType = "Long")
	@RequestMapping(value="findVById/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Vip findVById(@PathVariable("id")int id){
		System.out.println(id);
		Vip vip = vipservice.findVipById(id);
		System.out.println(vip);
		return vip;
	}
	//����鿴��������ʱ��������,����������
		@RequestMapping(value="findGById/{id}",method=RequestMethod.GET)
		@ResponseBody
		public Goods findGById(@PathVariable("id")int id){
			System.out.println(id);
			Goods goods = goodsService.findGoodsById(id);
			System.out.println(goods);
			return goods;
		}
		//����鿴��������ʱ��������,����������
		@RequestMapping(value="findDById/{id}",method=RequestMethod.GET)
		@ResponseBody
		public Driver findDById(@PathVariable("id")int id){
				System.out.println(id);
				Driver driver = driverService.findDriverById(id);
				System.out.println(driver);
				return driver;
		}
		//���л�Դҳ��鿴
		@RequestMapping("allGoods")
		public String findAllG(Model model){
			List<Goods> goods = goodsService.findAllGoods();
			model.addAttribute("goods", goods);
			return "admin-all-goods";
		}
		//�鿴���г���,δ���
		@RequestMapping("allDrivers")
		public String findAllD(Model model){
			return "allDrivers";
		}
		//˾���ӵ�����ӵ����ױ�
		//����Ҫ���������ʱ,ǰ̨����ֱ�Ӱ�˳��д����ֻ��Ҫ��/���ָ�,��̨����˳���������
		@RequestMapping(value="addDeal/{go_id}/{v_id}/{d_id}",method=RequestMethod.POST)
		@ResponseBody
		public boolean addDeal(Deal deal,HttpSession session) {
			//��deal������ӽ���id
			int idCode = IdRandomUtil.createIdCode();
			deal.setDe_id(idCode);
			//��ӡdeal
			System.out.println(deal);
			//�ӵ�����ӵ����ױ���
			boolean addDeal = dealService.addDeal(deal);
			//��Դ���н���Դ״̬ת��Ϊ�ѽӵ�
			System.out.println(deal.getGo_id());
			goodsService.transStatusOrder(deal.getGo_id());
			//�鿴�ҵĽ���
			int d_id = deal.getD_id();
			List<Deal> deals = dealService.findDealById(d_id);
			//���ҵĽ�����ӵ�session
			session.setAttribute("deals", deals);
			System.out.println(deals);
			return addDeal;
		}
		@RequestMapping(value="getMyDeal",method=RequestMethod.GET)
		//˾������ӵ������Լ��Ľӵ�
		public String getMyDeal(HttpSession session,Model model){
			List<Deal> attribute = (List<Deal>) session.getAttribute("deals");
			List<Deal> deals = attribute;
			model.addAttribute("deals", deals);
			return "freight-details";
		}
		@RequestMapping(value="getMyDealD",method=RequestMethod.GET)
		//˾���ڵ�¼ҳ��ֱ�Ӳ鿴�Լ��Ľӵ�
		public String getMyDealD(Model model,HttpSession session){
			Driver driver = (Driver)session.getAttribute("driver");
			System.out.println("���"+driver);
			int d_id = driver.getDr_id();
			List<Deal> deals = dealService.findDealById(d_id);
			model.addAttribute("deals", deals);
			return "freight-details";
		}
		@RequestMapping("removeMyDeal")
		@ResponseBody
		//����ȡ���ӵ���Ĳ���
		public boolean removeMyDeal(Deal deal){
			//�����ױ��еļ�¼ɾ��
			dealService.deleteDealById(deal.getDe_id());
			//�ڻ�Դ����״̬��Ϊ���ӵ�
			goodsService.transStatusNoOrder(deal.getGo_id());
			return true;
		}
		//���������鿴�Ѿ���ɵĽ���
		@RequestMapping("driver-finishDeal")
		public String findFinashedDealByDriver(HttpSession session,Model model){
			Driver driver = (Driver)session.getAttribute("driver");
			List<Deal> deals = dealService.findFinashedDealByDriver(driver.getDr_id());
			model.addAttribute("deals", deals);
			return "driver-findFinishedDeal";
		}
		//�鿴�Լ��Ľ��׵ķ���
		@RequestMapping("findDealByVip")
		public String findDealByVip(HttpSession session,Model model){
			Vip vip = (Vip)session.getAttribute("vip");
			System.out.println("���"+vip);
			List<Deal> deals = dealService.findDealByVip(vip.getId());
			model.addAttribute("deals", deals);
			return "vip-deals";
		}
		//vip��ѯĳ����������ķ���
		@GetMapping(value="shippingDetails/{g_id}")
		@ResponseBody
		public List<Sign> getShippingDetailsByVip(@PathVariable("g_id")int g_id){
			List<Sign> signs = driverService.getShippingDetails(g_id);
			System.out.println(signs);
			return signs;
		}
		//vip������Դ�ķ���
		@RequestMapping("addGoodsByVip")
		@ResponseBody
		public boolean addGoodsByVip(Goods goods,HttpSession session){
			System.out.println(goods);
			//�������id,��������
			int idCode = IdRandomUtil.createIdCode();
			goods.setGo_id(idCode);
			//ȡ���û�id��name��ֵ��goods
			Vip vip = (Vip)session.getAttribute("vip");
			goods.setU_id(vip.getId());
			goods.setU_name(vip.getName());
			goods.setGo_state("���ӵ�");
			goodsService.addGoods(goods);
			return true;
		}
		@RequestMapping("findGoodsByVip")
		//vip�����Լ���Դ�ķ���
		public String findGoodsByVip(HttpSession session,Model model){
			Vip vip = (Vip)session.getAttribute("vip");
			List<Goods> goods = goodsService.findGoodsByVip(vip.getId());
			model.addAttribute("goods", goods);
			System.out.println(goods);
			return "vip-goods";
		}
		//vipɾ���Ѿ���������Ϣ
		@RequestMapping("removeGoodsByVip")
		@ResponseBody
		public boolean removeGoodsByVip(int g_id){
			boolean deleteGoodsById = goodsService.deleteGoodsById(g_id);
			return deleteGoodsById;
		}
}
