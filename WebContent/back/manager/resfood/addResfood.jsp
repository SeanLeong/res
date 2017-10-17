<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../head.jsp"%>  <!-- easyUI需要引用的东西都在这个头里面 -->    



<script>


	$("#addBtn").click(function(){
		$.ajax({
			type:"POST",
			url:"back/addresfood.action",
			data:$("#addResfood").serialize(),
			dataType:"json",
			success:function(data){
				if(data.code == 1){
					$.messager.alert('Warning','添加成功');
				}else{
					$.messager.alert('Warning','添加失败,'+data.errorMsg());
				}
			}
		});

	})
	
	//用于图片上传
	//判断浏览器是否支持FileReader接口
	function showUploadImg(obj,picid){
		if (typeof FileReader == 'undefined') {
		   $.messager.alert('Warning','当前浏览器不支持FileReader接口');
		   //使选择控件不可操作
		   obj.setAttribute("disabled","disabled");
		   return;
		}

		var file = obj.files[0];
		var reader = new FileReader();
		reader.onload = function (e) {
		    $("#"+picid).attr("src",e.target.result);
		}
		reader.readAsDataURL(file)
	}
	
</script>




<title>增加菜品</title>
</head>
<body>
		<!-- 上传图-->
	<form id="addResfood" action="back/addrefood.action" method="post" id="addResfood"  >
		<!-- 隐藏域 -->
		
		<div style="text-align:left">	

			菜名<input type="text" name="fname" size="20" class="input"><br/>
			原价<input type="text" name="normprice" size="20" class="input"><br/>
			现价<input type="text" name="realprice" size="20" class="input"><br/>
			详情<input type="text" name="detail" size="20" class="input"><br/>	
		
			
			图片：<input type="file" name="pic" id="pic"  onchange="showUploadImg(this,'showpic')" accept="image/*"//><br/>
			<input type="button" value="隐藏图片" onclick="document.getElementById('showpic').style.display = 'none';"/>
            <input type="button" value="显示图片" onclick="document.getElementById('showpic').style.display = 'block';"/>
            <br/>
			<img id="showpic" /><br/>
			
			<input type="submit" value="增加" id="addBtn">	
		</div>
	</form>

</body>
</html> 