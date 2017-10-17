package com.yc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;



public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		//指定数据库并连接					url   user pwd 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "a");
		//预处理就是传入要执行的sql语句
		PreparedStatement pst = conn.prepareStatement("select * from students where sid = 3");
		//结果集  通过执行预处理得到的数据
		ResultSet rs = pst.executeQuery();
		//取出元数据 即数据库中一部分数据
		ResultSetMetaData rsd = rs.getMetaData();
		//这个判断必须有  结果集有数据才可以进一步操作
		if(rs.next()){ 
			for( int i=0;i<rsd.getColumnCount();i++){			
				System.out.println(  rsd.getColumnName(i+1)+"="+rs.getObject( rsd.getColumnName(i+1)  ) );
			}
		}
		//数据删除 更新也是一样的操作了
		pst = conn.prepareStatement("delete from students where sid = ?", 1);
		pst.setString(1, "2");
		pst.executeUpdate();
		
		
		
		
	}
}
