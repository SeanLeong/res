<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.yc.bean.Resuser"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>网上订餐系统</TITLE>

<!-- 基地网址不要忘记 -->

<%
	String path = request.getContextPath(); // /bbs
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<base href="<%=basePath%>">

<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />

<!-- 引入jquery -->
<script  src="js/jquery-1.9.1.js"  charset="utf-8" ></script>


<!-- 使用ajax实现登录退出 -->
<script type="text/javascript" src="js/commonAjax.js"> </script>

<script>
	function logout(){
		//退出后不应该跳转页面  还是留在当前页面  没有清空session中的登录用户的数据
		doAjax(null,handleFunc,"index.jsp");
		
	}	
	function handleFunc(){
		switch( xmlHttp.readyState){
		case 4:
				if( xmlHttp.status==200){
					//获取logindiv的信息
					var obj = document.getElementById("logindiv");
					obj.innerHTML = "&nbsp;&nbsp;&nbsp;<a href='login.jsp'>登录</a> &nbsp;| &nbsp; <A href='reg.jsp'>注册</A> ";				
				}
				break;
		}
		
	}
	
</script>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<table cellSpacing=0 cellPadding=0 width=776 align=center border=0>
	<tr>
		<td width="220" rowspan="3"><img src="images/jb_logo.jpg"
			width="200"></td>
		<td width="556" height="36">&nbsp;</td>
	</tr>
	<tr>
		<TD
			style="FONT-SIZE: 24pt; FILTER: blur(add=1, direction=120, strength=5); WIDTH: 100%; COLOR: #000000; FONT-FAMILY: arial; POSITION: relative"
			noWrap><B><span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我学我会</span>
		网上订餐系统</B></TD>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>

<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
	<TBODY>
		<TR>
			<TD background="images/001.gif" height=42>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD width=0 height=20></TD>
						<TD align=middle><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
							href="#" target=_self><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">网站首页</FONT></A><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
							href="#" target=_self><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">关于我们</FONT></A><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
							href="#" target=_self><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">定餐帮助</FONT></A><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
							href="#" target=_self><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">网上定餐</FONT></A><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
							href="#" target=_self><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">客服中心</FONT></A><FONT
							style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
	<TBODY>
		<TR vAlign=top>
			<TD width=181 background="images/002.gif">
			<TABLE cellSpacing=0 cellPadding=0 width=181 border=0>
				<TBODY>
					<TR>
						<TD><IMG height=234 src="images/left_top.jpg" width=181></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD align=middle height=30><FONT color=#000000> <SCRIPT
							language=javascript src="images/DateTime2.js"></SCRIPT> <SCRIPT
							language=javascript>tick('cn');</SCRIPT> </FONT></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD background="images/003.gif" height=4></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD background="images/003.gif" height=4></TD>
					</TR>
					<TR>
						<TD align=middle>
						<TABLE cellSpacing=0 cellPadding=0 width="94%" border=0>
							<TBODY>
								<TR>
									<TD>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD><IMG height=72 src="images/001.jpg" width=595></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD width="90%">
						
						
						
						
						<div id="logindiv" class='cnt'>	
							
								<c:if test="${cust!=null }">
									&nbsp;&nbsp;&nbsp;欢迎:${cust.username } &nbsp;&nbsp;|<a href=""> 查看历史订单</a> |<a href=""> 修改密码</a> |<a href="resuser.action?op=loginout" >退出 </a>
									
								</c:if>
								<c:if test="${cust==null }">
								　	&nbsp;&nbsp;&nbsp;<a href="login.jsp">登录</a> &nbsp;| &nbsp; <A href="reg.jsp">注册</A> &nbsp;| &nbsp; <A href="forget.jsp">忘记密码</A> 
								</c:if>
									
						</div>
						
						
						
						
						
						
						
						<div class='cnt'><marquee
							style="FONT-SIZE: 13px; COLOR: #0000FF" scrollamount='5'
							direction='left'><IMG height=15 src="images/tp009.gif"
							width=15> 欢迎您使用我学我会网上订餐系统，祝您用餐愉快！</marquee></div>
						&nbsp;&nbsp;</TD>
						
						
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD align=right background="images/004.gif" height=19></TD>
					</TR>
				</TBODY>
			</TABLE>