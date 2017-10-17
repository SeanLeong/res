<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="head.jsp"%>

<<script type="text/javascript">
	
	if( document.loginForm.ma.value==""){
		alert("验证码不能为空");
		return false;
	}
	return true;

</script>

		<form method="POST" name="loginForm" onSubmit="return checkUserInfo()" action="resuser.action">
			
			<input type="hidden" name="op" value="forgetsure"> 
			<table width="100%" border="0">
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="12%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
					<td width="44%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">验证码:</td>
					<td valign="top"><input type="text" name="ma" size="19"
						class="input"></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="submit"
						name="Submit" value="确认"></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td height="33" cospan="4">${msg }</td>
					
				</tr>
			</table>
			</form>











<%@ include file="bottom.jsp"%>    