package com.yc.biz.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import com.yc.dao.DbHelper;
import com.yc.web.entity.JsonModel;

public class ResfoodBizImpl implements ResfoodBiz {

	private DbHelper db = new DbHelper();


	@Override
	public List<Resfood> findResfood(Resfood resFood) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		String sql = "select  fid, fname,normprice, realprice,fphoto  from resfood  where 1=1";
		List<Object> params = new ArrayList<Object>();
		
		if(resFood!=null ){
			//如果名称不为空  则根据名称查找
			if( resFood.getFname()!=null&&!"".equals( resFood.getFname())){
				sql+=" and  fname like ?";
				params.add( "%"+resFood.getFname()+"%" );
			}
			if( resFood.getMinPrice()!=null&&resFood.getMaxPrice()!=null){
				sql+=" and realprice between ? and ? ";
				params.add( resFood.getMinPrice());
				params.add( resFood.getMaxPrice());
			}	

		}
		
		//排序字段
		if( resFood.getSort()!=null && resFood!=null){
			sql+=" order by "+resFood.getSort()+" "+resFood.getOrder()+" ";
		}	
		//分页  只要有pages则就可以分页   这个是前端的分页
		if( resFood.getPages()!=null && resFood!=null){
			int start=(resFood.getPages()-1)*resFood.getPagesize();
			sql+=" limit "+start+" , "+resFood.getPagesize();
		}
		
		//用于easyUI分页需要的参数 因为easyUI 需要的是page参数名
		if( resFood.getPage()!=null && resFood!=null){
			int start=(resFood.getPage()-1)*resFood.getRows();
			sql+=" limit "+start+" , "+resFood.getRows();
		}
		
		
	//	System.out.println( sql);
		List<Resfood> list  = db.findObject(sql, params, Resfood.class);
		
		return list;
	}
	
	
	//查询菜品数量  根据价格和名称查找呀  
	@Override
	public Integer findCount(Resfood resFood) throws SQLException {
		String sql = "select count(*) from resfood where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		
		if( resFood!=null){
			if( resFood.getFname()!=null&&!"".equals(resFood.getFname())){
				sql+=" and fname like ? ";
				params.add( "%"+resFood.getFname()+"%");
			}
			if(resFood.getMinPrice()!=null&&resFood.getMaxPrice()!=null ){
				sql+=" and realprice between ? and ?";
				params.add( resFood.getMinPrice());
				params.add( resFood.getMaxPrice());
			}
			
		}
		int count =(int)db.getCount(sql, null);
		return count;
	}
	
	/**
	 * 查到数据封装成类似pageBean json格式的数据   便于浏览器读取数据
	 */
	@Override
	public JsonModel<Resfood> find(Resfood resFood) throws Exception {
		//总共有多少条信息
		int total = findCount(resFood);
		//查询所有信息
		List<Resfood> list = findResfood(resFood);
		
		JsonModel<Resfood> jsonModel = new JsonModel<Resfood>();
		
		if( resFood!=null&&resFood.getPages()!=null){
			jsonModel.setPages(resFood.getPages());
			jsonModel.setPagesize(resFood.getPagesize());
		}

		jsonModel.setRows(list);
		jsonModel.setTotal(total); //这个必须最后设置
		
		return jsonModel;
	}
	
	//通过id查找所有信息包括detail
	@Override
	public Resfood findById(Integer fid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		String sql ="select  fid, fname,normprice, realprice,fphoto ,detail from resfood  where fid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add( fid);
		List<Resfood> list = db.findObject(sql, params, Resfood.class);
		if( list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

}
