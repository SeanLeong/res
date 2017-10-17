

	var xmlHttp;
	//創建一個函数来创建写目录http对象
	function createXMLHttpRequest() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {  //判断不同的浏览器，这个是IE
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function doAjax(queryString,handleStateChange,url){
		createXMLHttpRequest(); //创建了xmlhttprequest对象
		xmlHttp.onreadystatechange = handleStateChange; //绑定回调函数（服务器有回传结果时，由浏览器自动回调函数
		//使用post请求方式
		
		xmlHttp.open("POST",url);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//传递参数的编码
		xmlHttp.send(queryString); 

		
	}