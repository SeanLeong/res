package com.yc.bean;

import java.io.Serializable;

public class Resorderitem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3645641378976298660L;

	private int roiid;
	private String roid;
	private int fid;
	private double dealprice;
	private int num;
	public int getRoiid() {
		return roiid;
	}
	public void setRoiid(int roiid) {
		this.roiid = roiid;
	}
	public String getRoid() {
		return roid;
	}
	public void setRoid(String roid) {
		this.roid = roid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public double getDealprice() {
		return dealprice;
	}
	public void setDealprice(double dealprice) {
		this.dealprice = dealprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	
	
}
