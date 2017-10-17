package com.yc.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Resfood;
import com.yc.web.entity.CartItem;


@WebServlet("/resorder.action")
public class ResorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( "orderOne".equals(op)){
			orderOne(request,response);
		}else if("clearCart".equals(op)){ //清空购物车
			clearCart(request,response);
		}
		
		
		
		
	}
	//清空购物车  个人思路 从session中判断是否有商品 如果有则移除所有商品
	private void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先从session中取出购物车Map 取出商品的编号
		Map<Integer,CartItem> cart = new HashMap<Integer,CartItem>();
		HttpSession session = request.getSession();
		
		//判断session中是否有商品
		if( session.getAttribute("cart")!=null){
			cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		}
		//清空Map中是数据
		cart.clear();
		//最后跳转页面
		response.sendRedirect("shopCart.jsp");
		
		/**
		 * 老师的代码
		 * HttpSession session = request.getSession();
		   session.removeAttribute("cart");
		   request.getRequestDispatcher("shopCart.jsp").forward(request, response);
		 */

		
		
		
	}
	/**
	 * 购物车思路
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void orderOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先从session中取出购物车Map 取出商品的编号
		Map<Integer,CartItem> cart = new HashMap<Integer,CartItem>();
		HttpSession session = request.getSession();
		 
		//判断session中是否有商品   	114行
		if( session.getAttribute("cart")!=null){
			cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		}
		
		//查找商品id
		int fid = Integer.parseInt( request.getParameter("fid") );
		//从session中取出原来存在当前页面的resfood   在resfoodServlet中有保存
		List<Resfood> resfoodList = (List<Resfood>) session.getAttribute("resfoodList");
		
		//从resfoodList中取出fid代表的商品
		Resfood resfood = null;
		//首先判断是否有resfoodList
		if( resfoodList!=null){
			for( Resfood rf :resfoodList){
				if( rf.getFid()==fid){
					resfood = rf;
					break;
				}
			}
			
		}
		
		//判断购物车是否已经买过  根据Map中的键去判断
		CartItem ci = null;
		if( cart.containsKey(fid)){ //根据fid 查找
			ci = cart.get( fid);
			ci.setNum( ci.getNum()+1); //数量+1
			
		}else{ //购物车没有该商品 则新建一个
			ci = new CartItem();
			ci.setNum(1);
		}
		ci.setResfood(resfood); //
		
		//将ci存入map中
		cart.put(fid, ci);
		
		//在存入session中
		session.setAttribute("cart", cart);
		
		//最后跳转页面  跳转页面应该继续首页  继续购买！
		response.sendRedirect("index.jsp");
	//	request.getRequestDispatcher("shopCart.jsp").forward(request, response); 每次刷新数据都会被在提交一次
		
		
	}

}
