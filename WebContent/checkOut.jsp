<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="head.jsp"%>

			<table cellspacing=1 cellpadding=4 width="92%" border=0
				align="CENTER" bgcolor="#c0c0c0">
				<tr bgcolor="#dadada">
					<td colspan="5" height="25" align=center><font color="#000000">请确认支付和配送信息</font></td>
				</tr>
				<tr bgcolor="#ffffff">
					<td width="22%" align="RIGHT"><font color="#000000">订 餐
					人：</font></td>
					<td colspan=4 width="78%"><input type="text" name="name"
						size="46" maxlength="20"></td>
				</tr>
				<tr bgcolor="#ffffff">
					<td width="22%" align="RIGHT"><font color="#000000">送货地址：</font></td>
					<td colspan=4 width="78%"><input type="text" name="addr"
						size="46" maxlength="200"></td>
				</tr>

				<tr bgcolor="#ffffff">
					<td width="22%" height="31" align="RIGHT"><font
						color="#000000">联系电话：</font></td>
					<td colspan=4 width="78%" height="31"><input type="text"
						name="tel" size="46" maxlength="13"></td>
				</tr>

				<tr bgcolor="#ffffff">
					<td width="22%" align="RIGHT"><font color="#000000">电子邮件：</font></td>
					<td colspan=4 width="78%"><input type="text" name="email"
						size="46" maxlength="50"></td>
				</tr>


				<tr bgcolor="#ffffff">
					<td width="22%" height="31" align="right"><font
						color="#000000">订单附言：</font></td>
					<td colspan=4 width="78%" height="31"><textarea name="bz"
						cols="45" rows="6"></textarea></td>
				</tr>
				<tr bgcolor="#dadada">
					<td colspan="5" height="12" align="center"><input type=submit
						value="确认以上信息无误,提交" name=Submit></td>
				</tr>
			</table>















<%@ include file="bottom.jsp"%>