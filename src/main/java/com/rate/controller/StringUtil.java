package com.rate.controller;

import java.util.Random;

/**
 * �ַ���������
 */
public class StringUtil {
	/**
	 * ��a-z  A-Z  0-9��
	 * ����һ����λ�������֤��
	 */
	public static String createValidateCode(){
		StringBuffer sb = new StringBuffer();
		for(char c1='a';c1<='z';c1++){
			if(c1=='l' || c1=='o')continue;
			sb.append(c1);
		}
		for(char c='A';c<='Z';c++){
			if(c=='O' || c=='I')continue;
			sb.append(c);
		}
		for(int i=0;i<10;i++){
			if(i==0 || i==1)continue;
			sb.append(i);
		}
		sb.append("������Ÿ�Ƽ����޹�˾");
		
		String code = "";
		for(int i=0;i<4;i++){
			int index = new Random().nextInt(sb.length());
			code += sb.charAt(index);
		}
		return code;
	}
	public static void main(String[] args) {
		System.out.println(createValidateCode());
	}
}


