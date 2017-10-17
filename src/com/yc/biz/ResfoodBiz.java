package com.yc.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.yc.bean.Resfood;
import com.yc.web.entity.JsonModel;

public interface ResfoodBiz {
	
	/**
	 * 查询菜品的方法  不能带detail字段
	 * 条件  food ：
	 * 		1.根据菜品名模糊查询
	 * 		2.根据现价的范围查
	 * 		3.排序
	 * 		4.全部
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List<Resfood> findResfood(Resfood resFood) throws Exception;
	
	public Integer findCount(Resfood resFood) throws SQLException;
	
	//用啦输出前端数据   把查到的数据放入jsonmodel 用于ajax显示数据
	public JsonModel<Resfood > find(Resfood resFood) throws Exception;
	
	/**
	 * 根据id查询菜品  用来查详情
	 * @param fid
	 * @return
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Resfood findById(Integer fid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException;
	
	
}
