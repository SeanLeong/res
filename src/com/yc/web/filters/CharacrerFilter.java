package com.yc.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 使用注解 相当于我们写xml配置文件修改  
 * <filter> 
 * </filter>
 * 
 * <filter-mapping> 
 * </filter-mapping>
 * 
 * 主注解是Tomcat7.0以后的版本  大于or等于才可以使用
 * @author 浪客鹏
 *
 */
@WebFilter("*.action")
public class CharacrerFilter implements Filter {
	
	private String charset = "utf-8";
	
	


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		String config = fConfig.getInitParameter("utf-8");
		if(config!=null ){
			config="utf-8";
		}
	}
}
