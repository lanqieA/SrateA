package com.rate.bean;

import java.io.Serializable;
import java.sql.Date;

public class Car implements Serializable{
	private int car_id;
	private String aim;
	private String car_type;
	private String car_city;
	private Date firstTime;
	private String car_length;
	private String phone;
	private String car_des;
	private String car_img;
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getCar_city() {
		return car_city;
	}
	public void setCar_city(String car_city) {
		this.car_city = car_city;
	}
	public Date getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	public String getCar_length() {
		return car_length;
	}
	public void setCar_length(String car_length) {
		this.car_length = car_length;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCar_des() {
		return car_des;
	}
	public void setCar_des(String car_des) {
		this.car_des = car_des;
	}
	public String getCar_img() {
		return car_img;
	}
	public void setCar_img(String car_img) {
		this.car_img = car_img;
	}
	public Car(int car_id, String aim, String car_type, String car_city, Date firstTime, String car_length,
			String phone, String car_des, String car_img) {
		super();
		this.car_id = car_id;
		this.aim = aim;
		this.car_type = car_type;
		this.car_city = car_city;
		this.firstTime = firstTime;
		this.car_length = car_length;
		this.phone = phone;
		this.car_des = car_des;
		this.car_img = car_img;
	}
	public Car() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Car [car_id=" + car_id + ", aim=" + aim + ", car_type=" + car_type + ", car_city=" + car_city
				+ ", firstTime=" + firstTime + ", car_length=" + car_length + ", phone=" + phone + ", car_des="
				+ car_des + ", car_img=" + car_img + "]";
	}
	
}
