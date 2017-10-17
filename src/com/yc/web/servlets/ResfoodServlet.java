package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import com.yc.biz.impl.ResfoodBizImpl;
import com.yc.utils.RequestUtil;
import com.yc.web.entity.JsonModel;


@WebServlet("/resfood.action")
public class ResfoodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ResfoodBiz resfoodBiz = new ResfoodBizImpl();
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//查找菜品
			if("findResfood".equals(op)){
				findResfood(req,resp);
			}
			//显示详情
			else if( "showDetail".equals(op)){
				showDetail(req,resp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			super.outJson(e.getMessage(), resp);
		}
	}

	private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException, SQLException {
		Resfood resfood=RequestUtil.getParemeter(req, Resfood.class);
		Resfood rf = resfoodBiz.findById(resfood.getFid());
		//存session
		HttpSession session=req.getSession();
		session.setAttribute("foods", rf);  //存入session中！ 
		
		//最后跳转到details页面
		req.getRequestDispatcher("details.jsp").forward(req, resp);
		
		
		
	}
	
	/**
	 * 查找所有商品信息
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void findResfood(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//获取来自地址栏的pages 和pagesize 
		Resfood resfood=RequestUtil.getParemeter(req, Resfood.class);
		//然后根据page 和 pagesize去查找  JsonModel 就是json的数据格式
		JsonModel<Resfood> jsonModel = resfoodBiz.find(resfood);
		
		//把查到的信息都存入session中
		HttpSession session = req.getSession();
		//是把jsonModel中的所有商品信息存入 所以是Rows
		session.setAttribute("resfoodList", jsonModel.getRows());
		
		//因为pageBean中用到了泛型,而gson 中不能直接解析泛型数据,所有必须在这里使用TypeToken来告诉gson如何解析泛型
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel<Resfood>>() {
		}.getType();
		String jsonStr=gson.toJson(jsonModel,jsonType);
		super.outJsonStr(jsonStr,resp);
		
		
	}

}
