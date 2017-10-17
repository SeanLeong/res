<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>


<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFFF" height="100%">
	<tr valign="top">
		<td>
		<table width="98%" border="0" cellspacing="1" cellpadding="2"
			align="center">
			<tr valign="bottom">
				<td height="30"><img
					src=""> <font
					color="#000000">您的购物车中有以下商品</font></td>
			</tr>
		</table>
		<table width="98%" border="0" cellspacing="2" cellpadding="0"
			align="center">
			<tr bgcolor=#808000>
				<td height="1" bgcolor="#999999"></td>
			</tr>
		</table>
		<table width="98%" border="0" cellspacing="2" cellpadding="0"
			align="center">
			<tr>
				<td height="5"></td>
			</tr>
		</table>
		<table width="98%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
				<table width="100%" border="0" align="CENTER" cellpadding="2"
					cellspacing="1" bgcolor="#c0c0c0">
					<tr bgcolor="#dadada">
						<td height="22" width="50">
						<div align="CENTER"><font color="#000000">编号</font></div>
						</td>
						<td width="610" height="22">
						<div align="CENTER"><font color="#000000">商品名称</font></div>
						</td>
						<td height="22" width="104">
						<div align="CENTER"><font color="#000000">单价</font></div>
						</td>
						<td height="22" width="100">
						<div align="CENTER"><font color="#000000">数量</font></div>
						</td>
						<td width="116" height="22">
						<div align="CENTER"><font color="#000000">金额</font></div>
						</td>
					</tr>

				<c:set var="totalPrice" value="0" />  <!-- set 设置值的作用 -->
				<c:set var="totalNum" value="0" />  <!-- set 设置值的作用 -->
				<c:forEach items="${cart }" var="ci">  <!-- cast来自ResfoodServlet中是session -->

					<tr bgcolor="#ffffff">
						<td width="50" align="center" height="22"><font
							color="#000000">${ci.key }</font></td>
						<td align="center" height="22"><font color="#000000">${ci.value.resfood.fname }</font>
						<input type="hidden" name="fid" value="${ci.value.resfood.fid }"></td>
						<td width="104" align="center" height="22"><font
							color="#000000">￥${ci.value.resfood.realprice }</font></td>
						<td width="100" class="hh" align="center" height="22">${ci.value.num }</td>
						<td width="116" class="bb" align="center" height="22"><font
							color="#000000">￥${ci.value.smallcount }</font></td>
					</tr>
					<c:set var="totalPrice" value="${totalPrice+ci.value.smallcount }"/> 
					<c:set var="totalNum" value="${totalNum+ci.value.num }"/> 
					
				</c:forEach>


					<tr bgcolor="#dadada">
						<td width="50" height="22" align="center"><font
							color="#000000">合计</font></td>
						<td height="22" align="center"><font color="#000000"></font></td>
						<td width="104" height="22" align="center"><font
							color="#000000"></font></td>
						<td width="100" class="hh" height="22" align="center"><font
							color="#000000"></font>${totalNum }</td>
						<td width="116" class="bb" align="center" height="22"><font
							color="#000000">￥${totalPrice } </font></td>
					</tr>


				</table>
				<br>
				<table width="300" border="0" cellspacing="1" cellpadding="4"
					align="CENTER" bgcolor="#c0c0c0">
					<tr bgcolor="#dadada">
						<td height="10" align="center"><a href="resorder.action?op=clearCart"><font
							color="#000000">清空购物车</font></a></td>
						<td height="10" align="center" style="cursor:hand" >
							<a href="index.jsp"> <font color="#000000">继续购物</font> </a>
						</td>
						<td height="10" align="center" style="cursor:hand" ><a href="cust/pay.jsp"><font
							color="#000000">生成订单</font></a></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>



<%@ include file="bottom.jsp"%>