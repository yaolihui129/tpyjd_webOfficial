<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>德国蓝卡项目</title>
<!--系统css-->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--页面css-->
<link href="/views/pcTopic/deguo_20161115_pc_2/style.css" rel="stylesheet" type="text/css" />
</head>

<body class="bg-grey">
	<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
	<!--header头部结束-->

	<div class="imgContainer" style="height:600px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_01.jpg"></div>
    <div class="imgContainer" style="height:250px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_02.jpg"></div>
    <div class="imgContainer" style="height:592px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_03.jpg"></div>
    <div class="imgContainer" style="height:270px; background-color:#f7f7f7;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_04.jpg"></div>
    <div class="imgContainer" style="height:550px; background-color:#f7f7f7;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_05.jpg">
    	<div class="center">
        	<a href="http://tb.53kf.com/code/client/10141314/1]" target="_blank" class="deguo2-05"></a>
        </div>
    </div>
    <div class="imgContainer" style="height:519px; background-color:#f7f7f7;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_06.jpg"></div>
    <div class="imgContainer" style="height:235px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_07.jpg"></div>
    <div class="imgContainer" style="height:340px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_08.jpg"></div>
    <div class="imgContainer" style="height:538px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_09.jpg"></div>
    <div class="imgContainer" style="height:270px; background-color:#fff;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_10.jpg"></div>
    <div class="imgContainer" style="height:270px; background-color:#f7f7f7;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_11.jpg"></div>
    <div class="imgContainer" style="height:270px; background-color:#f7f7f7;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/deguo2_12.jpg"></div>
    <div class="imgContainer" style="height:592px; background-color:#f7f7f7;" imgUrl="/views/pcTopic/deguo_20161115_pc_2/Germany_09.jpg">
    	<div class="center">
	             <dl>
	               <dd><input name="name" type="text" class="Message-input" datatype="Require" placeholder="请输入姓名"></dd>
	               <dd><input name="phone" type="text" class="Message-input" datatype="Mobile" placeholder="请输入电话"></dd>
	               <dd><textarea name="content" type="text" class="Message-input1" datatype="message" placeholder="请输入留言"></textarea></dd>
	               <dd><input type="submit" class="btn1" value=" " onclick="submit();"></dd>
	             </dl>
	             
	             <input name="source" type="hidden" value="德国蓝卡项目"/>
	             <input name="url" type="hidden" value="${url}"/>
	             
	             
    	</div>
    </div>
 	
	<!--footer尾部开始-->
			<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->
	<script src="/resource/js/system/jquery-2.2.0.min.js"></script>
	<script src="/resource/js/system/layer.js"></script>
	<script src="/resource/js/modules/common.js"></script>
	<script src="/resource/js/modules/yzm.js"></script>
	<script src="/resource/js/modules/formValidate.js"></script>
	<script>
	
	function submit() {
		var name=$("input[name='name']").val(),
			phone=$("input[name='phone']").val(),
			content=$("textarea[name='content']").val(),
			source=$("input[name='source']").val(),
			url=$("input[name='url']").val();
		if(name && phone) {
			$.post("/pcTopic/save", {'name':name, 'phone':phone, 'content':content, 'source':source, 'url':url}, function(data) {
				if(data.status==0) {
					$("input[name='name']").val("");
					$("input[name='phone']").val("");
					$("textarea[name='content']").val("");
					alert(data.msg);
				} else {
					alert(data.msg);
				}
			});
		} else {
			alert("请填写必须项");
		}
	}
	
	
	$(function(){
		(function(){
			function loadImage(){
				var imgContainers=$(".imgContainer");
				var length=imgContainers.length;
				var index=0;
				var item=null;
				var imgUrl=null;
				getImg();
				function getImg(){
					item=imgContainers.eq(index);
					imgUrl=item.attr("imgUrl");
					if(imgUrl!=null || imgUrl!=undefined)
					{
						var image=new Image();
						image.onload=function(){
							item.css({"opacity":"1"});
							item.css({"background-image":"url("+imgUrl+")","opacity":"1"});
							index++;
							if(index<length)
							{
								getImg();
							}
						}
						image.src=imgUrl;
					}
					else
					{
						index++;
						if(index<length)
						{
							getImg();
						}
					}
				}
			}
			loadImage();
		})();
	});
	</script>
	</body>
</html>


