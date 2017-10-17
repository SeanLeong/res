package com.yc.bean;

import java.io.Serializable;
import java.util.List;

public class PageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 226240627814229755L;
	/**
	 * 分页参数  用于easyUI  还有数据
	 */	
	//easyUI 的datagrid需要的参数  page 和 rows 
	private Integer pages;  	//当前第几页    pages pagesize 用于前端数据显示
	private Integer pagesize;	//每页几条
	private String sort ;  //排序字段
	private String order;  //排序 desc/asc
	
	private Integer rows; //easyUI 的datagrid需要的参数  page 和 rows 
	private Integer page;
	
	

	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	
	
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "PageParam [pages=" + pages + ", pagesize=" + pagesize + ", sort=" + sort + ", order=" + order + "]";
	}
	
	
	

}
