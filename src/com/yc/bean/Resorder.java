package com.yc.bean;

import java.io.Serializable;
import java.util.UUID;

public class Resorder extends PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9097778186075146503L;
	private String roid;
	private Integer userid;
	private String address;
	private String tel;
	private String ordertime;
	private String delivertime;
	private String ps;
	private Integer status;
	
	/**
	 * 提供一个生成订单编号的方法
	 * @return
	 */
	public String getUUIDRoid(){
		return UUID.randomUUID().toString();
	}
	
	
	public String getRoid() {
		return roid;
	}
	public void setRoid(String roid) {
		this.roid = roid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getDelivertime() {
		return delivertime;
	}
	public void setDelivertime(String delivertime) {
		this.delivertime = delivertime;
	}
	public String getIns() {
		return ps;
	}
	public void setIns(String ins) {
		this.ps = ins;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	public String getPs() {
		return ps;
	}


	public void setPs(String ps) {
		this.ps = ps;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
	
	

}
