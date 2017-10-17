package com.yc.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.web.entity.CartItem;
import com.yc.web.entity.JsonModel;

public interface ResorderBiz {
	
	/**
	 * 查询订单的方法
	 * 		条件resorder 
	 * 			1.根据下单人的编号  userID
	 * 			2.默认排序  根据ordertime 降序排序
	 * 			3.查全部
	 * 			4.分页 
	 * @param resorder
	 * @return
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List<Resorder> findResorder(Resorder resorder) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException;
	
	
	public Integer findCount(Resorder resorder) throws SQLException;
	
	public JsonModel<Resorder > find(Resorder resorder) throws Exception;
	
	
	/**
	 * 处理订单	//下订单的时候处理      需要到订单   购物车   用户
	 * @param resorder
	 * @param cart
	 * @param resuser
	 * @throws SQLException 
	 */
	public void makeOrder( Resorder resorder,Map<Integer,CartItem> cart,Resuser resuser) throws SQLException;
	
	
}
