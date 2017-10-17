package com.yc.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/cust/*")
public class CustRightFilter implements Filter {





	/**
	 * 思路
	 * 1.取出session
	 * 2.从session中取出登录的用户cust 判断是否存在  如果存在即登录 否则为未登录
	 * 3.没登了则跳转登录页面
	 * 4.登录成功则继续操作
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//需要的是HTTPServlet 所以需要进行强转转换
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if( session.getAttribute("cust")==null){
			req.getRequestDispatcher("../login.jsp").forward(req, response);
		}else{
			chain.doFilter(request, response);
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}


	
	public void destroy() {
		
		
	}



}
