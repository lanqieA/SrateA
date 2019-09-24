package com.rate.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.rate.bean.Vip;

public interface VipMapper {
	//��ѯ����vip
	@MapKey("id")
	public Map<Integer, Vip> findAllVip();
	//���vip
	public void addVip(Vip vip);
	//����idɾ��vip
	public void deleteVipById(int id);
	//����id�޸�vip
	public void updateVip(Vip vip);
	//����id����vip
	public Vip findVipById(int id);
	//����username����vip
	public Vip findVipByUsername(String username);
	//��¼����
	public Vip findVipByNameAndPwd(Vip vip);
}
