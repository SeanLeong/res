<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<c:if test="${msg!=null }">
	下单失败，${msg } <a href="javascript:history(-1)">返回上一级</a>
</c:if>
<c:if test="${msg==null }">
	下单成功
</c:if>





<%@ include file="bottom.jsp"%>