package com.yc.web.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.yc.bean.Resfood;
import com.yc.biz.impl.AddResfoodBizImpl;


@WebServlet("/back/addresfood.action")
public class AddResfoodServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2481921557769233666L;
	
	private String filepath;  //真实文件位置tomcat在硬盘上的位置
	private String allowedFilesList="jpg,png,bmp,gif";
	private String deniedFilesList="bat,sh,exe,class,html,js,css";
	private long maxFileSize = 2000000;
	private long totleMaxFileSize = 4*maxFileSize;
	//用来插入数据
	private AddResfoodBizImpl adBiz = new AddResfoodBizImpl();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		if(config.getInitParameter("allowedFilesList")!=null){
			allowedFilesList = config.getInitParameter("allowedFilesList");
		}
		if(config.getInitParameter("deniedFilesList")!=null){
			deniedFilesList = config.getInitParameter("deniedFilesList");
		}
		if(config.getInitParameter("maxFileSize")!=null){
			maxFileSize = Long.parseLong(config.getInitParameter("maxFileSize"));
		}
		if(config.getInitParameter("totleMaxFileSize")!=null){
			totleMaxFileSize = Long.parseLong(config.getInitParameter("totleMaxFileSize"));
		}
		super.init(config);   //调用父类的init方法读取基础配置
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//有图片上传功能 使用smartupload
		//要回调的客户端的javascript函数编号
		SmartUpload su = new SmartUpload();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		try {
			PageContext context = JspFactory.getDefaultFactory().getPageContext(this, req, resp, null, true, 8*1024, true);
			su.initialize(context);
			su.setCharset("utf-8");
			
			//定义允许上传文件类型
			su.setAllowedFilesList(allowedFilesList);
			//不允许上传文件类型
			su.setDeniedFilesList(deniedFilesList);
			//单个文件最大限制
			su.setMaxFileSize(maxFileSize);
			//所有上传文件的总容量限制
			su.setTotalMaxFileSize(totleMaxFileSize);
			
			su.upload();
			//这个request是jspsmartupload组件的request对象，而不是j2ee的HttpServletRequest
			//spsmartupload组件的request对象是继承自  HttpServletRequest
			Request request = su.getRequest();
			String fname = request.getParameter("fname");
			Double normprice =Double.parseDouble( request.getParameter("normprice") ) ;
			Double realprice =Double.parseDouble( request.getParameter("realprice") ) ;		
			String detail= request.getParameter("detail");
			String pic = "";
			
			
			if(su.getFiles().getCount()>0){
					
				com.jspsmart.upload.File file = su.getFiles().getFile(0);
				//设置文件在服务器的保存位置
				//取filepath所指的tomcat的硬盘路径;
				//  = ""../news_uploadpics/";
				//tomcat中news项目的路径：C：\tomcat\apache-tomcat-7.0.48\webapps\news
				String tomcatwebroot = this.getServletConfig().getServletContext().getRealPath("/");//news路径
				File newsroot = new File(tomcatwebroot); //取news的父路径
				//C：\tomcat\apache-tomcat-7.0.48\webapps
				File tomcatRootFile = newsroot.getParentFile();//C：\tomcat\apache-tomcat-7.0.48\webapps/news_uploadpics
				
				filepath = tomcatRootFile+"/online_uploadpics/";//网上访问图片的路径
				String webUrl="../online_uploadpics/"; //../news_uploadpics/2017/05/xxx.jpg
				
				DateFormat df = new SimpleDateFormat("yyyy/MM/");
				String timeDir = df.format(new Date());
				filepath+=timeDir;
				
				webUrl+=timeDir;
				//判断文件夹是否存在，不存在，即创建
				File f = new File(filepath);
				if(f.exists()==false){
					f.mkdirs();
				}
				//拼装要保存的文件的新文件名
				String fileName = genFileName(file.getFileName());
				filepath += fileName;	//C：\tomcat\apache-tomcat-7.0.48\webapps/news_uploadpics/2017/05/xxx.jpg
				webUrl+=fileName;		//../news_uploadpics/2017/05/xxx.jpg
				file.saveAs(filepath, SmartUpload.SAVE_PHYSICAL);  //以物理路径来保存
				pic = webUrl;
			}
			
			System.out.println( pic );
			
			
			Resfood food = new Resfood();
			food.setFname(fname);
			food.setNormprice(normprice);
			food.setRealprice(realprice);
			food.setDetail(detail);
			food.setFphoto(pic);
			
			//写入数据
			adBiz.addResfood(food);
			//跳转页面
			resp.sendRedirect("manager/resfood/addResfood.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();		
	}
	
	

	private String genFileName(String fileName){
		String[] strs=fileName.split("\\.");
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		String prefix=df.format(new Date());
		return prefix+"."+strs[1];
	}
	
	
	
}
