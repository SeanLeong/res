package com.yc.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.yc.bean.Resuser;

public interface ResuserBiz {
	
	/**
	 * 用户登录和注册
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Resuser login(Resuser user) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException;
	
	//注册
	public Resuser reg(Resuser user) throws Exception;
	
	//忘记密码
	public Resuser forget(Resuser user) throws Exception;
	
}
