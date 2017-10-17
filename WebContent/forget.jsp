<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>


<!-- 使用ajax处理获取验证码请求 -->


<script language="javascript">

	function checkUserInfo() {
	 if(document.loginForm.username.value==""){
	    alert("用户名不能为空");
	    return false;
	 }
	 if(document.loginForm.pwd.value==""){
	    alert("密码不能为空");
	    return false;
	 }
	 if(document.loginForm.repwd.value==""){
		    alert("确认密码不能为空");
		    return false;
		 }

	 if(document.loginForm.pwd.value!=document.loginForm.repwd.value){
		 alert("两次密码不一致");
		 return false;
	 }	 

	 if(document.loginForm.ma.value==""){
		    alert("验证码不能为空");
		    return false;
	}
	 return true;
	}
	
	
	function yan(){
		$.ajax({
			type:"POST",
			url:"forget.action",
			data:"op=yan",
			dataType:"json", 
		//	success:()
			
		})
	
	}
	
	

	
</script>

		<form method="POST" name="loginForm" onSubmit="return checkUserInfo()" action="resuser.action">
			
			<input type="hidden" name="op" value="forget"> 
			<table width="100%" border="0">
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="12%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
					<td width="44%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">用户名：</td>
					<td valign="top"><input type="text" name="username" size="19"
						class="input"></td>
					<td>&nbsp;</td>
				</tr>

				
				
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">密&nbsp;&nbsp;码：</td>
					<td valign="top"><input type="password" name="pwd" size="20"
						class="input"></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">确认密码：</td>
					<td valign="top"><input type="password" name="repwd" size="20"
						class="input"></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">邮箱</td>
					<td valign="top"><input type="text" name="email" size="20"
						class="input"></td>
					<td>&nbsp;</td>
				</tr>




				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="submit"
						name="Submit" value="获取验证码"></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td height="33" cospan="4">${msg }</td>
					
				</tr>
			</table>
			</form>









<%@ include file="bottom.jsp"%>