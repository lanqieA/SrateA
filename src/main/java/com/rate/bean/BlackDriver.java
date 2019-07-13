package com.rate.bean;

import java.io.Serializable;

public class BlackDriver implements Serializable{
	private int dr_id;
	private String dr_name;
	private String dr_driverage;
	private String dr_drive;
	private int dr_amount;
	private long dr_phone;
	private int dr_evaluate;
	public int getDr_id() {
		return dr_id;
	}
	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
	}
	public String getDr_driverage() {
		return dr_driverage;
	}
	public void setDr_driverage(String dr_driverage) {
		this.dr_driverage = dr_driverage;
	}
	public String getDr_drive() {
		return dr_drive;
	}
	public void setDr_drive(String dr_drive) {
		this.dr_drive = dr_drive;
	}
	public int getDr_amount() {
		return dr_amount;
	}
	public void setDr_amount(int dr_amount) {
		this.dr_amount = dr_amount;
	}
	public long getDr_phone() {
		return dr_phone;
	}
	public void setDr_phone(long dr_phone) {
		this.dr_phone = dr_phone;
	}
	public int getDr_evaluate() {
		return dr_evaluate;
	}
	public void setDr_evaluate(int dr_evaluate) {
		this.dr_evaluate = dr_evaluate;
	}
	public BlackDriver(int dr_id, String dr_name, String dr_driverage, String dr_drive, int dr_amount, long dr_phone,
			int dr_evaluate) {
		super();
		this.dr_id = dr_id;
		this.dr_name = dr_name;
		this.dr_driverage = dr_driverage;
		this.dr_drive = dr_drive;
		this.dr_amount = dr_amount;
		this.dr_phone = dr_phone;
		this.dr_evaluate = dr_evaluate;
	}
	public BlackDriver() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BlackDriver [dr_id=" + dr_id + ", dr_name=" + dr_name + ", dr_driverage=" + dr_driverage + ", dr_drive="
				+ dr_drive + ", dr_amount=" + dr_amount + ", dr_phone=" + dr_phone + ", dr_evaluate=" + dr_evaluate
				+ "]";
	}
	
	
	
}
