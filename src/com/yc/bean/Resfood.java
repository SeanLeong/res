package com.yc.bean;

import java.io.Serializable;

public class Resfood extends PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1761740603887513485L;
	private int fid;
	private String fname;
	private Double normprice;
	private Double realprice;
	private String detail;
	private String fphoto;
	
	//因为有空的情况  所以用double
	private Double minPrice;
	private Double maxPrice;
	
	
	
	
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Double getNormprice() {
		return normprice;
	}
	public void setNormprice(Double normprice) {
		this.normprice = normprice;
	}
	public Double getRealprice() {
		return realprice;
	}
	public void setRealprice(Double realprice) {
		this.realprice = realprice;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getFphoto() {
		return fphoto;
	}
	public void setFphoto(String fphoto) {
		this.fphoto = fphoto;
	}
	@Override
	public String toString() {
		return "Resfood [fid=" + fid + ", fname=" + fname + ", normprice=" + normprice + ", realprice=" + realprice
				+ ", detail=" + detail + ", fphoto=" + fphoto + "]";
	}
	
	

}
