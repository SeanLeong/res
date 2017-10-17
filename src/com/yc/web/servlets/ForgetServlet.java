package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.biz.impl.ResuserBizImpl;
import com.yc.mail.Sendmail;
import com.yc.utils.RequestUtil;

/**
 * Servlet implementation class ForgetServlet
 */
@WebServlet("/forget.action")
public class ForgetServlet extends BaseServlet implements Servlet {
	

	private static final long serialVersionUID = 1950892060242272546L;
	
	
	private ResuserBiz resuserBiz = new ResuserBizImpl();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if("yan".equals(op)){
				yan(request,response);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}


	/**
	 * 验证码
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void yan(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//获取数据  username pwd repwd email 验证码  但是用户类没有验证码，所以用request.getPattrt("ma");
		//Resuser user = RequestUtil.getParemeter(request, Resuser.class);
		
		
	
		Resuser user = new Resuser();
		user.setEmail("972206847@qq.com");
		//激活邮件发送准备
		
		Sendmail sm = new Sendmail(user);
		
		String  s  ;
		//判断用
		
		
		sm.run();
		//发送
		//sm.start();
		
	}

}
