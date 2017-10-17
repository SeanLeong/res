package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.biz.impl.ResuserBizImpl;
import com.yc.mail.Sendmail;
import com.yc.utils.RequestUtil;
import com.yc.web.entity.JsonModel;


@WebServlet("/resuser.action")
public class ResuserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private ResuserBiz resuserBiz = new ResuserBizImpl();
	
	//登录成功后的操作
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用户登录
		try {
			if("login".equals(op)){
				login(request,response);
			}else if("loginout".equals(op)){
				loginout(request,response);
			}else if( "reg".equals(op)){
				reg(request,response);
			}else if("forget".equals(op)){
				forget(request,response);
			}else if("forgetsure".equals(op)){
				forgetsure(request,response);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}


	/**
	 * 忘记密码进一步操作  获取页面验证码进行判断
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void forgetsure(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Resuser user = new Resuser();
		//获取忘记操作的用户信息  在forget的session中forgetuser
		HttpSession session = request.getSession();
		user = (Resuser) session.getAttribute("forgetuser"); //返回的对象是一个用户对象
		
		
		//获取页面验证码
		String ma = request.getParameter("ma");
		System.out.println(ma+"-----");
		//与sendmail中的生成的验证码核对
		Sendmail sm = new Sendmail(user);   //使用makema又生成了一个新的验证码
		//因为每调用一次都是随机生成一个呀
		
		if( ma.equals( session.getAttribute("makema") )){
			//数据库操作
			user = resuserBiz.forget(user);	
			//成功后跳转页面
			response.sendRedirect("index.jsp");
		}else{
			throw new Exception("验证码错误！");
		}
		

		
	}



	/**
	 * 忘记密码
	 * 思路  获取页面上用户的信息包括用户名，密码，和邮箱验证码
	 * 调用业务逻辑
	 * 跳转页面
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void forget(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Resuser user = RequestUtil.getParemeter(request, Resuser.class);
		
		HttpSession session = request.getSession();
		//把这些信息存起来用于forget-sure
		session.setAttribute("forgetuser", user);
		//先查找是否有该用户
		
		if( user!=null){ //说明成功
			//跳转到输入验证码页面面
			request.getRequestDispatcher("forget-sure.jsp").forward(request, response);
		}else{
			throw new Exception("用户不存在");
		}
		
		//发送邮件哦
		Sendmail sm = new Sendmail(user);
		//获取发送邮件的验证码存入session中
		session.setAttribute("makema", sm.makema());
		//System.out.println(sm.makema() );  //这个验证吗
		sm.run();
		
	}
	
	
	
	
	
	/**
	 * 注册思路 1.验证码要正确
	 * 		2.用户名和密码不能为空
	 * 		3.密码和确认密码需要一致
	 * 		
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void reg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//封装信息成一个user
		Resuser user = RequestUtil.getParemeter(request, Resuser.class);
		//获取验证码
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");
		
		//获取用户输入的验证码
		String valcode = request.getParameter("valcode");
		if( rand.equals(valcode)==false){ //验证码不相等
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/reg.jsp").forward(request, response);
		}else{
			user = resuserBiz.reg(user); //调用注册的方法
			if( user!=null){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				
			}else{
				request.setAttribute("msg", "请确认用户名和密码");
				request.getRequestDispatcher("/reg.jsp").forward(request, response);
			}
			
			
		}
		
	}

	private void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cust");
		//JsonModel jsonModel = new JsonModel();
		//jsonModel.setCode(1);
		//super.outJsonStr(jsonModel, response);
		response.sendRedirect("index.jsp");
				
		
	}

	/**
	 * 判断登录
	 * @param request
	 * @param response
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException, SQLException {
		Resuser user = RequestUtil.getParemeter(request, Resuser.class);
		//判断验证码
		//获取session中取出标准验证码
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");  //image.jsp中已经存入session
		//获取用户是验证码
		String valcode = request.getParameter("valcode");
		if( rand.equals(valcode)==false){ //如果不相等
			//提示错误信息
			request.setAttribute("msg", "验证码错误");
			//跳转到登录页面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			user = resuserBiz.login(user); //登录实现去判断是否符合登录条件
			if(user!=null){ //登录成功
				//存入用户 //登录成功应该用session记录下来 
				session.setAttribute("cust", user);
				//跳转首页
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			}else{ 
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			}
		}
		
		
	}

}
