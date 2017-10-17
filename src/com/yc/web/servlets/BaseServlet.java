package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8242236844561672466L;
	protected  String op;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}


	//每次一操作都获取op的值
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				op = req.getParameter("op");
				super.service(req, resp);
	}



	/*
	 * 只能用于解析非泛型的数据
	 */
	protected void outJson(Object obj,HttpServletResponse resp) throws IOException {
		Gson gson=new Gson();
		String jsonstr=gson.toJson(obj);
		outJsonStr(jsonstr,resp);
	}
	
	protected void outJsonStr(String jsonStr,HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out=resp.getWriter();
		//json数据格式的信息
		System.out.println(jsonStr);		
		out.println(jsonStr);	
		out.flush();
		out.close();
	}
}
