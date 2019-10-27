package com.rate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rate.bean.Vip;

@Service
public interface VipService {
	//��ѯ�����û���Ϣ
	public List<Map<String, Object>> findAllVip(HashMap<String, String> map);
	//���vip
	public void addVip(Vip vip);
	//����idɾ��id
	public void deleteVipById(int id);
	//����id�޸���Ϣ
	public void updateVip(Vip vip);
	//����id����vip
	public Vip findVipById(int id);
	////�����û�����ѯvip
	public Vip findVipByUsername(String username);
	//��¼����
	public Vip findVipByNameAndPwd(Vip vip);
}
