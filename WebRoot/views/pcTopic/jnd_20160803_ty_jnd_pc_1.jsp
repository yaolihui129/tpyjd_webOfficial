<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>加拿大移民</title>
<meta name="description" content="全景匈牙利-太平洋加达为您移民保驾护航,全景匈牙利,办理从速,太平洋加达移民20多年投资移民经验成功率100%,专业服务顾问团,过万件成功案例,业界首屈一指,详情咨询：400-610-8118.">
<meta name="keywords" content="全景匈牙利" />
<!--系统css-->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--页面css-->
<link href="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/style.css" type="text/css" rel="stylesheet" />
</head>

<body class="bg-grey">
	<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
	<!--header头部结束-->

	<!-- 内容开始 -->
	<div class="imgContainer" style="height:598px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_01.jpg"></div>
	<div class="imgContainer" style="height:82px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_02.jpg">
		<div class="center">
	    	<a href="http://chat.looyuoms.com/chat/chat/p.do?c=20001120&f=10056057&g=10058949" target="_blank" class="btn2"></a>
	    </div>
	</div>
	<div class="imgContainer" style="height:361px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_03.jpg"></div>
	<div class="imgContainer" style="height:895px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_04.jpg"></div>
	<div class="imgContainer" style="height:730px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_05.jpg"></div>
	<div class="imgContainer" style="height:653px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_06.jpg"></div>
	<div class="imgContainer" style="height:1087px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_07.jpg"></div>
	<div class="imgContainer" style="height:713px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_08.jpg">
		<div class="center">
	        <div class="Message-ctr">
	                 <dl>
	                   <dd><input name="name" type="text" class="Message-input" datatype="Require" msg="请输入姓名" placeholder="输入姓名"></dd>
	                   <dd><input name="phone" type="text" class="Message-input" datatype="Mobile" msg="请输入手机号或者手机号码格式不正确" placeholder="输入电话"></dd>
	                   <dd>
	                       <select name="content" headerKey="" headerValue="请选择感兴趣项目">
	                          <option value="">——请选择——</option>
	                          <option value="PEI省提名移民">PEI省提名移民</option>
	                          <option value="曼省省提名">曼省省提名</option>
	                          <option value="萨省提名移民">萨省提名移民</option>
	                          <option value="BC省省提名移民">BC省省提名移民</option>
	                          <option value="魁省投资移民">魁省投资移民</option>
	                          <option value="联邦技术移民">联邦技术移民</option>
	                          <option value="魁省技术移民">魁省技术移民</option>
	                        </select>
	                  	</dd>
	                 </dl>
	                 
	                 
	             <input name="source" type="hidden" value="加拿大移民"/>
	             <input name="url" type="hidden" value="${url}"/>
	             
	             
	                 <div style=" margin-left:28px;"><input type="submit" class="btn1" value="&nbsp;" onclick="submit();"></div>
	        </div>
	    </div>
	</div>
	<div class="imgContainer" style="height:1022px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_09.jpg">
		<div class="center" style="text-align:center;">
	    	<embed src="http://player.youku.com/player.php/sid/XMTUxNTc2NzIyMA==/v.swf" allowFullScreen="true" quality="high" width="755" height="461" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash" style=" border:solid #ccc 2px; margin-top:370px; "></embed>
	    </div>
	</div>
	<div class="imgContainer" style="height:689px; background-color:#fff;" imgUrl="/views/pcTopic/jnd_20160803_ty_jnd_pc_1/jnd_10.jpg"></div>	
	<!-- 内容结束 -->
 	
	<!--footer尾部开始-->
			<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->
	<!--系统js-->
	<script src="/resource/js/system/jquery-2.2.0.min.js"></script>
	<script src="/resource/js/system/layer.js"></script>
	<script src="/resource/js/modules/common.js"></script>
	<script src="/resource/js/modules/yzm.js"></script>
	<script src="/resource/js/modules/formValidate.js"></script>
	<script>
	
	function submit() {
		var name=$("input[name='name']").val(),
			phone=$("input[name='phone']").val(),
			content=$("select[name='content']").val(),
			source=$("input[name='source']").val(),
			url=$("input[name='url']").val();
		
		if(name && phone && content) {
			$.post("/pcTopic/save", {'name':name, 'phone':phone, 'content':content, 'source':source, 'url':url}, function(data) {
				if(data.status==0) {
					$("input[name='name']").val("");
					$("input[name='phone']").val("");
					$("select[name='content']").val("");
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


