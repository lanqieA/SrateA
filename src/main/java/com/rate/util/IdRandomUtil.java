package com.rate.util;

import java.util.Random;

public class IdRandomUtil {
	/**
	 * ��a-z  A-Z  0-9��
	 * ����һ����λ�����id
	 */
	public static int createIdCode(){
		String code = "0123456789";
		String idString = "";	
		for(int i=0;i<6;i++){
			idString += code.charAt(new Random().nextInt(code.length()));
	//		System.out.println(idString);
		}
		int id = Integer.parseInt(idString);
		return id;
	}
	public static void main(String[] args) {
		System.out.println(IdRandomUtil.createIdCode());
	}
}
