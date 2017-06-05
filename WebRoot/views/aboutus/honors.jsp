<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
	name="viewport" id="viewport">
<meta name="format-detection" content="telephone=no" />

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--<meta name="renderer" content="webkit">-->
<title>太平洋出国|关于我们</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/assess.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/about.css">
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
</head>

<body class="bg-grey">
	<div class="tpy-header">
		<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
		<!--header头部结束-->
	</div>
	<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--banner 开始-->
	<%@ include  file="/views/nav/banner.jsp"%>
	<!--banner 结束-->

	<!-- <div class="tpy-other-banner">
		other banner开始
		<img src="/resource/images/other-banner.jpg" alt="" />
		<div class="other-breadline">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						当前位置：<a href="/">首页</a><span>></span><a href="#">关于我们</a><span>></span><a
							href="#">荣誉资质</a>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!--other banner结束-->
	<div class="container tpy-container">
		<!--中间container开始-->
		<div class="row margin-auto">
			<div class="col-xs-2 about-nav">
				<div class="company-logo">
					<img src="/resource/images/about-logo.png">
				</div>
				<ul>
					<script src="/common/about/5"></script>
				</ul>
				<div class="about-bg">
					<img src="/resource/images/about-bg.png">
				</div>
			</div>
			<div class="col-xs-10 about-main">
				<h2 class="profile-title">
					荣誉资质<span>Honorary certificate</span>
				</h2>
				<div class="dynamics-content">
					<c:forEach items="${honorList}" var="item" varStatus="status">
						<h2 class="honoraty-title">
							<span></span>${item.title }</h2>
						<div class="clear"></div>
						<div class="qualification">
							<c:forEach items="${item.imgs}" var="image">
								<img src="${image.img_url }">
							</c:forEach>

						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!--中间container结束-->
		<!--footer尾部开始-->
		<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->

	<script data-main="/resource/js/project/main"
		src="/resource/js/system/require.js"></script>
</body>
</html>
