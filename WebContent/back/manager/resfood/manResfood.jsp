<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../head.jsp"%>  <!-- easyUI需要引用的东西都在这个头里面 -->    
    
<script type="text/javascript">
	$(function(){
		$('#manResfoodTable').edatagrid({
			url:'resfood.action?op=findResfood',	//查询时加载的URL
			pagination:true,//显示分页
			pageSize: 6,	   //默认分页的条数
			pageList:[6,10,15,20,25,30,35,40,45,50,60,100],//可选分页条数
			fitColumns:true,//自适应列
			fit:true,//自动补全
			title:"菜品管理",
			idField:"fid",//标识，会记录我们选中的一行的id，不一定是id，通常是主键
			rownumbers:"true",//显示行号
			nowrap:"true",//不换行显示
			sortName:"fid",//排序的列 这个参数会传到7后台的servlet上，所以要有后台对应的接受
			sortOrder:"asc",//排序方式
			singleSelect:true,
			
			//以上的四种增删改查操作，只要失败，都会回调这个onError
			onError:function(a,b){
					$.messager.alert("错误","操作失败");
			},
			columns:[[{
				field:'fid',
				title:'菜单编号',
				width:100,
				align:'center',
				hidden:'true'
			},{
				field:'fname',
				title:'菜品名',
				width:100,
				align:'center'
			},{
				field:'normprice',
				title:'原价',
				width:100,
				align:'center'
			},{
				field:'realprice',
				title:'现价',
				width:100,
				align:'center'
			}]]
		})
	});

</script>
<title>菜品管理</title>
</head>
<body>
	
	<table id="manResfoodTable"></table>

</body>
</html>