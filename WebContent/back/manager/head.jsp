<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//localhost:8080/Online/
%>
<base href="<%=basePath%>">

<!-- 引用easyUI的css文件js使用script引入 css使用link href引入 -->
<link rel="stylesheet" type="text/css" href="back/manager/easyui15/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="back/manager/easyui15/themes/icon.css">
<script type="text/javascript" src="back/manager/easyui15/jquery.min.js"> </script>
<script type="text/javascript" src="back/manager/easyui15/jquery.easyui.min.js"> </script>
<script type="text/javascript" src="back/manager/easyui15/jquery.edatagrid.js"> </script>





