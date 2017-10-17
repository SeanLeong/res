	//当页面一加载就显示所有菜单
	$(document).ready(function(){
		
		show(1);
	});
	
	
	function show(pages){

		//发出异步请求 	pages=1 pageSize=;
		$.ajax({
			type: "POST",
			url:"resfood.action",
			data:"pages="+pages+"&pagesize=6&op=findResfood",
			dataType:"json",	//替换eval('('+xmlHttp.responeseText+')')
			success:function(data){		 //data就是jsonmodel返回的数据 jsonmodel中有pages pagesize total rows
				$("#pagediv").html("");
				var str="";
				var total = parseInt( data.total);
				var pagesize = parseInt( data.pagesize);
				var totalPage=Math.ceil(data.total/data.pagesize);
				if(totalPage>0){
					str+="[共"+total+"条记录,每页"+pagesize+"条&nbsp; 第"+data.pages+"页/共"+totalPage+"页   ] ";
					str+="&nbsp;<a href='javascript:void(0)' onclick='show(1)'>第一页</a>";
					
					if(data.pages>1){
						str+="&nbsp;<a href='javascript:void(0)' onclick='show("+(data.pages-1)+")'>上一页</a>";
					}else{
						str+="&nbsp;<a href='javascript:void(0)' onclick='show(1)'>上一页</a>";
					}
					
					if(data.pages<totalPage){
						str+="&nbsp;<a href='javascript:void(0)' onclick='show("+(data.pages+1)+")'>下一页</a>";
					}else{
						str+="&nbsp;<a href='javascript:void(0)' onclick='show("+totalPage+")'>下一页</a>";
					}
					str+="&nbsp;<a href='javascript:void(0)' onclick='show("+totalPage+")'>最后一页</a>";
				}
				$("#pagediv").html(str); 
				//显示数据函数
				showFoodList( data.rows);
				
				
				
			}
		});
	}
	
	function showFoodList(rows){
		var str="";
		$("#foodlist").html("");
		if( rows==null||rows.length<=0){
			$("#foodlist").html("暂无数据");
			return ;
		}
		
		for( var i=0;i<rows.length;i++){
			var food = rows[i];
			if( i%2==0){
				str+="<tr>";
			}
			str+="<td>";
			
			str+="<TABLE height='100%' cellSpacing=1 cellPadding=2 border=0><TBODY><TR><TD vAlign=top width=90 height=90><A href='resfood.action?op=showDetail&fid="+food.fid+"' target=_blank><IMG height=80 alt=点击图片查看内容 src='images/"+ food.fphoto+"' width=80 border=0></A></TD>";
			str+="<TD vAlign=top><TABLE cellSpacing=1 cellPadding=0 width='100%' align=center border=0><TBODY><TR><TD><A href=# target=_blank><STRONG>"+ food.fname+"</STRONG></A></TD>";
			str+="</TR><TR><TD height=21><FONT color=#ff0000>现价：人民币"+food.realprice+"元</FONT><BR><a href='#'>美味可口</a>！</TD>";
			str+="</TR></TBODY></TABLE></TD></TR><TR><TD height=28>编号: "+food.fid+"</TD><TD>";
			str+="<TABLE cellSpacing=1 cellPadding=0 width=145 border=0><TBODY><TR><TD ><a href='resorder.action?op=orderOne&fid="+food.fid+"'><img src='images/buy_cn.gif' ></a></TD><TD align=middle width=70><A href='resfood.action?op=showDetail&fid="+food.fid+"' target=_blank><IMG src='images/detail_cn.gif' border=0></A></TD>";
			str+=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE>";
			
			str+="</td>";
			if(i%2==1){
				str+="</tr>";
			}
		}
		
		$("#foodlist").html(str);
	}
	
	
	
	
	
	