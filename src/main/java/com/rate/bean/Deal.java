package com.rate.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Deal implements Serializable{
	private int de_id;
	private int go_id;
	private int v_id;
	private int d_id;
	private String de_state;
	public int getDe_id() {
		return de_id;
	}
	public void setDe_id(int de_id) {
		this.de_id = de_id;
	}
	public int getGo_id() {
		return go_id;
	}
	public void setGo_id(int go_id) {
		this.go_id = go_id;
	}
	public int getV_id() {
		return v_id;
	}
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public String getDe_state() {
		return de_state;
	}
	public void setDe_state(String de_state) {
		this.de_state = de_state;
	}
	public Deal(int de_id, int go_id, int v_id, int d_id, String de_state) {
		super();
		this.de_id = de_id;
		this.go_id = go_id;
		this.v_id = v_id;
		this.d_id = d_id;
		this.de_state = de_state;
	}
	public Deal() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Deal [de_id=" + de_id + ", go_id=" + go_id + ", v_id=" + v_id + ", d_id=" + d_id + ", de_state="
				+ de_state + "]";
	}
	
}
