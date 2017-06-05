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
<title>太平洋出国|移民国家</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/country.css" type="text/css" />
</head>
<body>
	<div class="tpy-header">
		<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
		<!--header头部结束-->
	</div>
	
	<div class="tpy-other-banner">
		<!--other banner开始-->
		<img src="/resource/images/other-banner.jpg" alt="" />
		<div class="other-breadline">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						当前位置：<a href="/">首页</a><span>></span>
						&nbsp;&nbsp;${pos_nav_name }
						<span>></span>&nbsp;&nbsp;${article.title }
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--other banner结束-->


	<div class="container tpy-container">
		<!--中间container开始-->
		<div class="row">
			<h2 class="col-xs-12 country-article-title">${article.title }</h2>
			<ul class="col-xs-12 country-article-sign">
				<li><fmt:formatDate value="${article.publish_time }"
						pattern="yyyy年MM月dd日 " /></li>
				<li>来源：${article.source }</li>
				<li>作者：${article.author }</li>
				<li>阅读：${article.click_count }</li>
			</ul>
			<div class="col-xs-12 country-article-body">${article.contents }
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

