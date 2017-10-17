package com.yc.biz.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.dao.DbHelper;
import com.yc.utils.Encrypt;

public class ResuserBizImpl implements ResuserBiz {
	
	private DbHelper db = new DbHelper();
	//登录  思路
	/**
	 * 1.
	 * 2.查数据库是否存在用户和核对密码
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	@Override
	public Resuser login(Resuser user) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		String sql = "select * from resuser where username = ? and pwd=? ";
		List<Object> params = new ArrayList<Object>();
		params.add( user.getUsername());
		params.add(Encrypt.md5AndSha( user.getPwd()  ));
		
		//查到信息返回一个集合 然后返回集合第一个元素
		List<Resuser> list = db.findObject(sql, params, Resuser.class);
		if( list==null||list.size()<=0){
			return null;
		}else{
			return list.get(0);
		}
		
		
	}

	
	
	/**
	 * 注册 思路
	 * 1.账户名和密码不能为空
	 * @throws Exception 
	 * 
	 */
	@Override
	public Resuser reg(Resuser user) throws Exception {
		
//		if( user.getUsername()==null){
//			throw new Exception("注册账户不能为空");
//		}
//		if( user.getPwd()==null){
//			throw new Exception("密码不能为空");
//		}
//		if( user.getPwd()!=user.getRepwd()){
//			throw new Exception("两次密码不一致");
//		}
		
		//把信息插入数据库
		String sql= "insert into resuser(username,pwd,email) values(?,?,?) ";
		List<Object> params = new ArrayList<Object>();
		params.add( user.getUsername());
		params.add( Encrypt.md5AndSha( user.getPwd() ));
		params.add(user.getEmail());
		db.doUpdate(sql, params);
		

		

		return user;
	}


	//忘记密码  写sql语句的
	@Override
	public Resuser forget(Resuser user) throws Exception {
		//获取用户名  是否存在
		
		//判断条件  验证码，用户密码问题
/*		if( user==null){
			throw new  Exception("用户不能为空");
		}
		if( user.getPwd()==null){
			throw new  Exception("密码不能为空");
		}*/
//		if( user.getRepwd()!=user.getPwd()){
//			throw new  Exception("两次密码不一致");
//		}
		
		
		//更新数据库
		String sql = "update resuser  set pwd = ? where username = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add( Encrypt.md5AndSha( user.getPwd()));
		params.add( user.getUsername());
		
		db.doUpdate(sql, params);
	
		return user;
	}

	

}
