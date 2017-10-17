<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<script>

	$(function(){
		var resfoodTreeData=[{
			"text":"菜单管理",
			"state":"closed",
			"children":[{
				"text":"菜品维护",
				"attributes":{  //attributes属性的意思
					"url":"<iframe width='100%' height='100%' src='back/manager/resfood/manResfood.jsp'/>"
				}
			},{
				"text":"增加菜单",
				"attributes":{
					"url":"<iframe width='100%' height='100%' src='back/manager/resfood/addResfood.jsp'/>"
				}
				
				
			}]
		}];
		//展示数据函数
		showTree("resfoodTree",resfoodTreeData);
		
	})
	
	function showTree(treeId,data){
		$("#"+treeId).tree({
			data:data,  //将这里改成：url:"right.action" 这个地址会得到一个上面  treeData这样的字符串
			onClick:function(node){
				if(node.attributes){
					openTab(node);
				}
			}
		});	
	}
	//打开树
	function openTab(node){
		if( $("#mainTt").tabs("exists",node.text) ){
			$("#mainTt").tabs("select",node.text);
		}else{
			$("#mainTt").tabs("add",{
				title:node.text,
				select:true,
				closed:true,
				url: node.attributes.url,
				tools:[{
					iconCls:'icon-cancel',
					handler:function(){
						$(this).panel("closed");
					}
				}],
				//href:node.attributes.url
				content:node.attributes.url
			})
		}
		
		
	}




</script>




<title> 订餐系统后台管理</title>
</head>
<body class="easyui-layout layout panel-noscroll">
		<div data-options="region:'north'" style="height:100px"></div>
		<div data-options="region:'south',split:true" style="height:50px"></div>
		<div data-options="region:'east',split:true" title="East" style="width:200px"></div>
		
		<!-- 加颗树 -->
		<div data-options="region:'west',split:true" title="West" style="width:150px">
			<div class="easyui-accordion" style="width:500px;height:300px;">
			
				<div title="菜单管理" style="overflow:auto;padding:10px;">
					<ul id="resfoodTree" class="easyui-tree" data-options="animate:true,state:closed,fit:true"></ul>	
    			</div>
    			<div title="用户管理" style="overflow:auto;padding:10px;">
					<ul id="resuserTree" class="easyui-tree" data-options="animate:true,state:closed,fit:true">555</ul>	
    			</div>
    			<div title="管理员管理" style="overflow:auto;padding:10px;">
					<ul id="resmanagerTree" class="easyui-tree" data-options="animate:true,state:closed,fit:true">555</ul>	
    			</div>
			</div>
		</div>
	
	
	
		<div data-options="region:'center',title:'主操作区',iconCls:'icon-ok'">
			<!--  tabs区，有很多的tabs -->
			<div id="mainTt" class="easyui-tabs" fit="true" border="false">
				<div title="欢迎来到后台管理">管理员，你好</div>
			</div>
		</div>
	
</body>
</html>