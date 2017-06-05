<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<title>希腊购房-爱琴海专题</title>
	<!--系统css-->
	<link rel="stylesheet" href="/resource/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
    <!--页面css-->
	<link href="/views/pcTopic/index20160621/style.css" rel="stylesheet" type="text/css" />
	<link href="http://www.pacificimmi.cn/htmlzt/images20160109/topic.css" type="text/css" rel="stylesheet" />
</head>

<body class="bg-grey">
	<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
	<!--header头部结束-->

	<div class="imgContainer" style="height:599px;" imgUrl="/views/pcTopic/index20160621/img1.jpg">
    </div>
    <div class="imgContainer" style="height:100px;" imgUrl="/views/pcTopic/index20160621/img2.jpg">
    	<a href="http://tb.53kf.com/code/client/10141314/1]" class="onlineBtn1">在线咨询>></a>
        <!--<div class="textContainer">	
            <p class="text">出国能否改变民族命运？</p>
            <p class="title" style="font-size:90px;padding-top:0.5em;padding-bottom:0.5em;font-weight:100;">6月25日</p>
            <p class="text">北京建国门外大街长富宫饭店</p>
            <p class="text">一层西侧芙蓉厅东厅权威解读</p>
        </div>-->
    </div>
    <div class="imgContainer" style="height:589px;" imgUrl="/views/pcTopic/index20160621/img3.jpg">
    </div>
    <div class="imgContainer" style="height:643px;" imgUrl="/views/pcTopic/index20160621/img4.jpg">
    </div>
    <div class="imgContainer" style="height:641px;" imgUrl="/views/pcTopic/index20160621/img5.jpg">
    </div>
    <div class="imgContainer" style="height:1130px;" imgUrl="/views/pcTopic/index20160621/img6.jpg">
    </div>
    <div class="imgContainer" style="height:983px;" imgUrl="/views/pcTopic/index20160621/img7.jpg">
    </div>
    <div class="imgContainer" style="height:1343px;" imgUrl="/views/pcTopic/index20160621/img8.jpg">
    	<a href="http://tb.53kf.com/code/client/10141314/1]" class="onlineBtn2"></a>
        <a href="http://tb.53kf.com/code/client/10141314/1]" class="onlineBtn2" style="top:1205px;"></a>
        <div class="readMoreContainer">
        	
        	<div class="readMoreBtn"></div>
            <div class="readMoreText"></div>
        </div>
    </div>
    <div class="imgContainer" style="height:1408px;" imgUrl="/views/pcTopic/index20160621/img9.jpg">
    </div>
    <div class="imgContainer" style="height:588px;" imgUrl="/views/pcTopic/index20160621/img10.jpg">
    </div>
    <p class="videoTitle" style="color:#2e73b8;">成就客户，用我们的专业和服务实现客户梦想，让客户过上更加幸福的生活！</p>
    <div class="videoContainer">
        <embed style="margin-left:73px;" src="http://player.youku.com/player.php/sid/XMTUzNTYxNDg3Mg==/v.swf" allowfullscreen="true" quality="high" width="368" height="218" align="middle" allowscriptaccess="always" type="application/x-shockwave-flash">
        <embed style="margin-left:102px;" src="http://player.youku.com/player.php/sid/XMTUzMzEzMjIyOA==/v.swf" allowfullscreen="true" quality="high" width="368" height="218" align="middle" allowscriptaccess="always" type="application/x-shockwave-flash">
    </div>
 	
	<!--footer尾部开始-->
			<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->
	<script src="/resource/js/system/jquery-2.2.0.min.js"></script>
	<script src="/resource/js/system/layer.js"></script>
	<script src="/resource/js/modules/common.js"></script>
	<script src="/resource/js/modules/yzm.js"></script>
	<script src="/resource/js/modules/formValidate.js"></script>
	<!--右侧漂浮 begin-->
	<script type="text/javascript" src="http://www.pacificimmi.cn/Asset/js/WebBaseJs.js"></script>
	<!--右侧漂浮 end-->
	<script>
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


