package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.ResorderBiz;
import com.yc.biz.impl.ResorderBizImpl;
import com.yc.utils.LogUtil;
import com.yc.utils.RequestUtil;
import com.yc.web.entity.CartItem;


@WebServlet("/cust/resorder.action")
public class CustResorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private ResorderBiz rBiz = new ResorderBizImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if( "makeOrder".equals(op)){
				makeOrder(request,response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}



	private void makeOrder(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException, SQLException {
		/**
		 * 思路  先获取购物车中信息存入一个Map
		 * 获取用户信息
		 * 调用业务逻辑
		 * 跳转页面 
		 * 
		 * 为了安全，我们应该考虑到购物车是否为空，以及用户是否为空！
		 */
		//取出登录用户信息  登录是存在session中的
		HttpSession session = request.getSession();
		Resuser resuser = (Resuser)session.getAttribute("cust");
		
		
		//session  取出购物车的信息
		Map<Integer,CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		if( cart!=null){
			try{
				//取出订单信息 request
				Resorder resorder = RequestUtil.getParemeter(request, Resorder.class);
				//调用业务逻辑
				rBiz.makeOrder(resorder, cart, resuser);
				//成功处理  则将购物车的信息清除
				
				session.removeAttribute("cart");
			}catch(Exception e){
				LogUtil.logger.error(e.getMessage());
				//失败处理 保存一个信息
				session.setAttribute("msg", "make order faild");
			}
			
		}else{
			
		}

		//跳转页面
		response.sendRedirect("../seeYou.jsp");
		
		
		
		
	}

}
