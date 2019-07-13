package com.rate.bean;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Goods implements Serializable{
	private int go_id;
	private int u_id;
	private String u_name;
	private String go_info;
	private String go_price;
	private String start_city;
	private String start_info;
	private String end_city;
	private String end_info;
	private String car_type;
	private String car_len;
	private Date sub_time;
	private String go_state;
	public Goods() {
		super();
	}
	public Goods(int go_id, int u_id, String u_name, String go_info, String go_price, String start_city,
			String start_info, String end_city, String end_info, String car_type, String car_len, Date sub_time,
			String go_state) {
		super();
		this.go_id = go_id;
		this.u_id = u_id;
		this.u_name = u_name;
		this.go_info = go_info;
		this.go_price = go_price;
		this.start_city = start_city;
		this.start_info = start_info;
		this.end_city = end_city;
		this.end_info = end_info;
		this.car_type = car_type;
		this.car_len = car_len;
		this.sub_time = sub_time;
		this.go_state = go_state;
	}
	public int getGo_id() {
		return go_id;
	}
	public void setGo_id(int go_id) {
		this.go_id = go_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getGo_info() {
		return go_info;
	}
	public void setGo_info(String go_info) {
		this.go_info = go_info;
	}
	public String getGo_price() {
		return go_price;
	}
	public void setGo_price(String go_price) {
		this.go_price = go_price;
	}
	public String getStart_city() {
		return start_city;
	}
	public void setStart_city(String start_city) {
		this.start_city = start_city;
	}
	public String getStart_info() {
		return start_info;
	}
	public void setStart_info(String start_info) {
		this.start_info = start_info;
	}
	public String getEnd_city() {
		return end_city;
	}
	public void setEnd_city(String end_city) {
		this.end_city = end_city;
	}
	public String getEnd_info() {
		return end_info;
	}
	public void setEnd_info(String end_info) {
		this.end_info = end_info;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getCar_len() {
		return car_len;
	}
	public void setCar_len(String car_len) {
		this.car_len = car_len;
	}
	public Date getSub_time() {
		return sub_time;
	}
	public void setSub_time(Date sub_time) {
		this.sub_time = sub_time;
	}
	public String getGo_state() {
		return go_state;
	}
	public void setGo_state(String go_state) {
		this.go_state = go_state;
	}
	@Override
	public String toString() {
		return "Goods [go_id=" + go_id + ", u_id=" + u_id + ", u_name=" + u_name + ", go_info=" + go_info
				+ ", go_price=" + go_price + ", start_city=" + start_city + ", start_info=" + start_info + ", end_city="
				+ end_city + ", end_info=" + end_info + ", car_type=" + car_type + ", car_len=" + car_len
				+ ", sub_time=" + sub_time + ", go_state=" + go_state + "]";
	}
	
}
