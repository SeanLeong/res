package com.yc.dao;

import java.sql.SQLException;

public class Test2 {
	public static void main(String[] args) throws SQLException {
		
		DbHelper db = new DbHelper();
		String sql = "select * from students where sid = 2";
		System.out.println(  db.findSingleObject(sql, null) );
		
	}
}
