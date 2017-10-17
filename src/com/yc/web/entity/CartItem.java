package com.yc.web.entity;

import java.io.Serializable;

import com.yc.bean.Resfood;

public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Resfood resfood;
	private Integer num;
	private Double smallcount;
	
	
	public Resfood getResfood() {
		return resfood;
	}
	public void setResfood(Resfood resfood) {
		this.resfood = resfood;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	//Javabean的特性呀 小计直接计算即可  不用设置
	public Double getSmallcount() {
		return num*resfood.getRealprice();
	}

	
	
	
}
