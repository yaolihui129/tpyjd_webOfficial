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

<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/news.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/about.css">
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
							href="/about-us/success-case">成功案例</a>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!--other banner结束-->
	<div class="container tpy-container">
		<!--中间container开始-->
		<form id="searchForm" method="post">
		<div class="row margin-auto about-all">
			<div class="col-xs-2 about-nav">
				<div class="company-logo">
					<img src="/resource/images/about-logo.png">
				</div>
				<ul>
					<script src="/common/about/3"></script>
				</ul>
				<div class="about-bg">
					<img src="/resource/images/about-bg.png">
				</div>
			</div>
			
			<div class="col-xs-10 about-main">
				<h2 class="profile-title">
					成功案例<span>Successful case</span>
				</h2>
				<ul>
					<c:forEach items="${list}" var="item" varStatus="status">
						<li class="successful-list">
							<a href="/about-us/success-cases/detail/${item.info_id }">
									<dl>
										<dt class="country-timestamp case-img">
											<img src="${item.list_img }">
										</dt>
										<dd class="case-content">
											<p class="case-title">${item.title}</p>
											<p class="case-main">${item.summary }</p>
										</dd>
										<div class="clear"></div>
									</dl>
							</a>
						</li>
					</c:forEach>
				</ul>
				
			</div>
			
			<!--<c:if test="${not empty list }"> -->
				<!-- 分页 pageBar start -->
					<input type="hidden" id="page" name="page" value="1" /> ${pageBar}
				<!-- 分页 pageBar end -->
			<!--</c:if> -->
		</div>
		</form>
	</div>
	<!--中间container结束-->
		<!--footer尾部开始-->
		<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->
	<script data-main="/resource/js/project/main"
		src="/resource/js/system/require.js"></script>
	<script type="text/javascript">
		//绑定翻页
		$('.country-new-page a').each(function(index, element) {
			if ($(element).attr("data-page") != undefined) {
				$(this).click(function(e) {
					$("#page").val($(element).attr("data-page"));
					doRequest();
				});
			}
		});

		//提交查询请求
		function doRequest() {
			$("#searchForm").submit();
		}
	</script>
</body>
</html>
