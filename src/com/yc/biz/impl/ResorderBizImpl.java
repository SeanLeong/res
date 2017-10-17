package com.yc.biz.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.yc.bean.Resfood;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.ResorderBiz;
import com.yc.dao.DbHelper;
import com.yc.web.entity.CartItem;
import com.yc.web.entity.JsonModel;

public class ResorderBizImpl implements ResorderBiz {
	
	private DbHelper db = new DbHelper();
	/**
	 * 查找所有信息
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public List<Resorder> findResorder(Resorder resorder) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		String sql = "select * from resorder where 1=1  ";
		List<Object> params = new ArrayList<Object>();
		
		if( resorder!=null){
			if(resorder.getUserid()!=null &&!"".equals(resorder.getUserid())){
				sql+=" and userid = ? ";
				params.add( resorder.getUserid());
			}
			if( resorder.getStatus()!=null&&!"".equals(resorder.getStatus())){
				sql+=" and status = ?";
				params.add( resorder.getStatus());
			}	
		}	
		//排序
		if(resorder!=null&&  resorder.getSort()!=null ){
			sql+=" order by "+resorder.getSort()+" "+resorder.getOrder()+" ";
		}else{
			sql+=" order by  ordertime desc ";
		}
		//分页
		if( resorder!=null&&resorder.getPages()!=null){
			int start=(resorder.getPages()-1)*resorder.getPagesize();
			sql+=" limit "+start+" , "+resorder.getPagesize();
		}
		
		
		
		return db.findObject(sql, params, Resorder.class);
	}
	
	//查找订单条数  根据用户id去查 以及状态
	@Override
	public Integer findCount(Resorder resorder) throws SQLException {
		String sql=" select count(*) from resorder where 1=1";
		List<Object> params = new ArrayList<Object>();
		
		if( resorder!=null ){
			if(resorder.getUserid()!=null&&!"".equals(resorder.getUserid())){
				sql+=" and userid = ? ";
				params.add( resorder.getUserid());
			}
			if( resorder.getStatus()!=null&&!"".equals(resorder.getStatus())){
				sql+=" and status = ? ";
				params.add( resorder.getStatus());
				
			}
			
		}
		int count = (int)db.getCount(sql, params);
		return count;
	}

	@Override
	public JsonModel<Resorder> find(Resorder resorder) throws Exception {
		//总共有多少条信息
		int total = findCount(resorder);
		//查询所有信息
		List<Resorder> list = findResorder(resorder);
		
		JsonModel<Resorder> jsonModel = new JsonModel<Resorder>();
		if(resorder!=null&&resorder.getPages()!=null){
			jsonModel.setPages(resorder.getPages());
			jsonModel.setPagesize(resorder.getPagesize());
			
		}

		jsonModel.setRows(list);
		jsonModel.setTotal(total); //这个必须最后设置
		
		return jsonModel;
	}
	
	/**
	 * 处理订单的方法
	 * @throws SQLException 
	 */
	@Override
	public void makeOrder(Resorder resorder, Map<Integer, CartItem> cart, Resuser resuser) throws SQLException {
			
		//个人理解
		/**
		 * 页面传来数据  生成一个订单编号
		 * 把数据插入数据库  但是可能有多菜需要插入对吧 所以这个时候就需要通过循环取获取每一个菜品信息 并插入数据库
		 * 先把订单resorder插入数据库   插入多条数据是不同的菜呀
		 * 
		 * 
		 */
		
		
		//先生成一个订单编号UUID
		String id = UUID.randomUUID().toString();
		//定义一个List<String> sqls 因为一个用户可能点多个菜 所以是一对多的关系，则数据库插入时要么一起成功要么一起失败
		//定义这条语句需要的参数 List<List<Object>> params 
		List<String> sqls = new ArrayList<String>();	
		List<List<Object>> params = new ArrayList<List<Object>>();
		//1.先根据resorder生成 订单插入语句
		String sql ="insert into resorder(roid,userid,address,tel,ordertime,deliverytime,ps,status)";
				sql+=" values(?,?,?,?,now(), date_add(now(),interval 1 hour),?,1) ";
		List<Object> param = new ArrayList<>();
		param.add( id);
		param.add( resorder.getUserid());
		param.add( resorder.getAddress());
		param.add(resorder.getTel());
		param.add( resorder.getPs());
		
		sqls.add( sql);
		params.add( param);
		
		
		//2.循环cart列表，生成订单项  增加到sqls中
		for(Map.Entry<Integer, CartItem> entry:cart.entrySet()){
			//增加参数
			sql="insert into resorderitem(roid,fid,dealprice,num) values(?,?,?,?)";
			param = new ArrayList();
			param.add( id);
			param.add( entry.getKey());
			param.add( entry.getValue().getResfood().getRealprice());
			param.add( entry.getValue().getNum());
			
			sqls.add(sql);
			params.add(param);
			
		}
		
		
		
		//调用DbHelper 中的doUpdate方法 多条语句那个  //如果成功则  失败则如何
		db.doUpdate(sqls, params);

		//以上是一个事务操作，要么同时成功，要么一起失败
		
	}

}
