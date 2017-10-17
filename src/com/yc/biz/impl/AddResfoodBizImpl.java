package com.yc.biz.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resfood;
import com.yc.dao.DbHelper;

public class AddResfoodBizImpl {
	
	private DbHelper db = new DbHelper();
	//写sql
	public void addResfood(Resfood resfood) throws SQLException{
		
			String sql = "insert into resfood(fname,normprice,realprice,detail) values(?,?,?,?) ";
			List<Object> params = new ArrayList<Object>();
			params.add(resfood.getFname() );
			params.add( resfood.getNormprice());
			params.add( resfood.getRealprice());
			params.add( resfood.getDetail());
		//	params.add( resfood.getFphoto());
			

		
			db.doUpdate(sql, params);
		
	}

}
