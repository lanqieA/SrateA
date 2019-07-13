package com.rate.bean;

import java.sql.Date;

public class Sign {
	private int s_id;
	private int g_id;
	private String n_city;
	private Date s_time;
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getN_city() {
		return n_city;
	}
	public void setN_city(String n_city) {
		this.n_city = n_city;
	}
	public Date getS_time() {
		return s_time;
	}
	public void setS_time(Date s_time) {
		this.s_time = s_time;
	}
	public Sign(int s_id, int g_id, String n_city, Date s_time) {
		super();
		this.s_id = s_id;
		this.g_id = g_id;
		this.n_city = n_city;
		this.s_time = s_time;
	}
	public Sign() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Sign [s_id=" + s_id + ", g_id=" + g_id + ", n_city=" + n_city + ", s_time=" + s_time + "]";
	}
}
