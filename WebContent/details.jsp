<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="head.jsp" %>
<!-- 使用el表达式的方式 -->


			<TABLE cellSpacing=0 cellPadding=0 width="96%" align=center border=0>
				<TBODY>
					<TR>
						<TD>

						<TABLE cellSpacing=1 cellPadding=1 width="100%" align=center
							bgColor=#c0c0c0 border=0>
							<TBODY>
								<TR bgColor=#dadada>

									<TD width="100%" align="center">我学我会网上点餐系统用户请直接登录</TD>
								</TR>
							</TBODY>
						</TABLE>
						<BR>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="90" height="90" valign="top"><img
						src="images/${foods.fphoto }" border="0" width="80" height="80"></td>
					<td valign="top">
					<table width="98%" border="0" cellspacing="1" cellpadding="0"
						align="center">
						<tr>
							<td><strong>${foods.fname }</strong></td>
						</tr>
						<tr>
							<td height="21">原价：<strike>人民币${foods.normprice }元</strike><br>
							<font color="#ff0000">现价：人民币${foods.realprice }元</font><br>
							美味可口！</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="30">编号: ${foods.fid }</td>
					<td>
					<table width="145" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td align="center" width="70"><a href=#
								onClick="window.open('shop_cart.asp?id=500047&nowmenuid=500001','shopcart','width=580,height=250,resizable=no,scrollbars=yes')"><img
								src="images/buy_cn.gif" border=0></a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<P align=center><STRONG><FONT size=4>详细资料</FONT></STRONG></P>
			<HR align=center width="100%" color=#000000 noShade SIZE=1>

			<P align=center><IMG
				style="BORDER-LEFT-COLOR: #000000; BORDER-BOTTOM-COLOR: #000000; WIDTH: 232px; BORDER-TOP-COLOR: #000000; POSITION: static; HEIGHT: 172px; BORDER-RIGHT-COLOR: #000000; align: "
				height=294 alt="" hspace=0 src="images/${foods.fphoto }" width=350
				border=0></P>
			<P align=center><FONT size=3>${foods.detail }</FONT></P>
			<br>
			</td>
		</tr>
	



<%@ include file="bottom.jsp" %>