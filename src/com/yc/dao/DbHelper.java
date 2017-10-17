package com.yc.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.yc.utils.LogUtil;
import com.yc.utils.RequestUtil;

import oracle.sql.BLOB;

public class DbHelper<T>{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//加载驱动
	static{
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverName"));
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
			LogUtil.logger.error(e.getMessage());
		} catch (IOException e) {	
			e.printStackTrace();
			LogUtil.logger.error(e.getMessage());

		}
	}

	//获取数据库连接对象
	public Connection getConn(){
		Connection conn = null;
		try {
			//getConnection(url,properties);
			conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),MyProperties.getInstance());
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
//		//从容器tomcat中取出名为jdbc/bbsdb的联接池中的连接
//		try {
//			Context initCtx = new InitialContext();    //创建一个容器
//			Context envCtx = (Context) initCtx.lookup("java:comp/env");  //查找资源
//			DataSource ds = (DataSource)     //提供要查找的资源名
//			  envCtx.lookup("jdbc/bbsdb");
//
//			conn = ds.getConnection();
//
//			
//		} catch (NamingException e) {
//
//			e.printStackTrace();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
		return conn;		
	}

	//关闭对象
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(null != rs){  //关闭结果集
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		//关闭语句对象
		if(null != pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}

		//关闭连接对象
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public List<T> findObject(String sql,List<Object> params,Class<T> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException{
		List<Map<String,Object>> list = findMultiObject(sql,params);
		
		List<T> listT = new ArrayList<T>();
		
		if(list!=null){
			for(Map<String,Object> map:list){
				T t = RequestUtil.getParemeter(map, c);
				listT.add(t);
			}
		}
		return listT;
	}

	/*
	 * 查看操作：sql语句可以查询出更多条记录
	 */

	public List<Map<String, Object>> findMultiObject(String sql,List<Object> params) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		conn = this.getConn();
		pstmt = conn.prepareStatement(sql);
		this.setparams(pstmt, params);
		rs = pstmt.executeQuery();
		//获取结果集中的所有的列名
		List<String> columnNames = getAllColumnName(rs);
		while(rs.next()){
			map = new HashMap<String, Object>();
			for(String name:columnNames){
				map.put(name, rs.getObject(name));
			}
			list.add(map);
		}
		return list;
	}

	/*
	 * 查询操作
	 *
	 */
	public Map<String, Object> findSingleObject (String sql,List<Object> params) throws SQLException{
		Map<String, Object> map = null;

		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setparams(pstmt, params);
			rs = pstmt.executeQuery();
			//获取数据库中所有的字段名
			List<String> columnNames = getAllColumnName(rs);
			if(rs.next()){
				map = new HashMap<String , Object>();
				for(String name:columnNames){
					
					map.put(name, rs.getObject(name));
				}
			}
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return map;
	}

	/*
	 * 获取结果集中所有列表  获取表中所有字段名
	 */
	public List<String> getAllColumnName(ResultSet rs) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		ResultSetMetaData dd = rs.getMetaData();
		for(int i = 1;i<=dd.getColumnCount();i++){
			columnNames.add(dd.getColumnName(i));
		}
		return columnNames;
	}

	/*
	 * 单条sql语句更新操作：增删改
	 * sql: 语句
	 * params: 传入的参数
	 */

	public int doUpdate(String sql,List<Object> params) throws SQLException{
		int result = 0;
		conn = this.getConn();
		pstmt = conn.prepareStatement(sql);
		//设置参数
		try {
			this.setParams(pstmt, params);
			result = pstmt.executeUpdate();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			//关闭对像
			this.closeAll(conn, pstmt, null);
		}
		return result;

	}

	/*
	 * 多条sql语句的更新操作 批处理 注意：这些sql语句要么一起成功，要么一起失败
	 * sqls
	 * params: 对应每一条sql语句所需要的参数值的集合
	 */
	public int doUpdate(List<String> sqls, List<List<Object>> params) throws SQLException{
		int result = 0;

		try {
			conn = this.getConn();
			//设置事物提交方式为手动提交
			conn.setAutoCommit(false);

			if(null!=sqls&&sqls.size()>0){
				//对sql语句进行循环
				for(int i= 0;i<sqls.size();i++){
					String sql = sqls.get(i);
					pstmt = conn.prepareStatement(sql);
					this.setparams(pstmt, params.get(i)); //第几条sql语句对应list集合中的第一个小list
					result = pstmt.executeUpdate();
				}
			}
			conn.commit();  //手动提交事物
		} catch (SQLException e) {
			conn.rollback();  //事物回滚
		}finally{
			conn.setAutoCommit(true); //恢复事物
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}

	/*
	 * 设置参数
	 * pstmt ： 预编译对象
	 * params ：外部传入的参数值，添加值时顺序一样要和?对应值的顺序一致
	 */

	public void setparams(PreparedStatement pstmt,List<Object> params) throws SQLException{
		if(null!=params&&params.size()>0){
			for(int i =0;i< params.size();i++){
				pstmt.setObject(i+1, params.get(i));  //设置? 值
			}
		}
	}

	/*
	 * 聚合函数查询 select count(*) from emp;
	 */
	public double getCount(String sql,List<Object>params) throws SQLException{

		double result = 0;

		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			setparams(pstmt,params);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1); //获取第一列的值
			}
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return  result;
	}

	public void setParams(PreparedStatement pstmt,List<Object> params) throws SQLException, FileNotFoundException{
		if(params!=null&&params.size()>0){
			//图片必须单独处理，传入的输入流，所以从界面上传入文件对象
			for(int i =0;i< params.size();i++){
				File f = new File("");
				if(params.get(i) instanceof File){//判断参数是否为文件对象
					File file = (File)params.get(i);//强转为文件对象
					InputStream in = new FileInputStream(file);//转为输入流对象
					pstmt.setBinaryStream(i+1, in, (int)file.length());

				}else{
					pstmt.setObject(i+1, params.get(i));  //设置? 值
				}
			}
		}
	}

	// 从数据库读取图片
		public InputStream selectImg(String sql, List<Object> params) throws IOException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			InputStream inputStream = null;
			try {
				conn = getConn();
				pstmt = conn.prepareStatement(sql);
				this.setparams(pstmt, params);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					BLOB blob = (BLOB) rs.getBlob(1);
					inputStream = blob.getBinaryStream();
					return inputStream;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return inputStream;
		}

	}

