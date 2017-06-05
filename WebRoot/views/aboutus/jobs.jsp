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

<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/news.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
</head>

<body class="bg-grey">
	<div class="tpy-header">
		<!--header头部开始-->
		<%@ include file="/views/nav/header.jsp"%>
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
							href="#">诚聘英才</a>
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
					<script src="/common/about/6"></script>
				</ul>
				<div class="about-bg">
					<img src="/resource/images/about-bg.png">
				</div>
			</div>
			<form id="searchForm" method="post">
				<div class="col-xs-10 about-main">

					<h2 class="profile-title">
						诚聘英才<span>Join us</span>
					</h2>
					<div class="join-search">
						<div class="search">
							<select class="search-select purpose" id="company" name="company">
								<option value=""
									<c:if test="${empty company }">  class="dd" </c:if>>所属公司</option>
								<c:forEach items="${coms}" var="item" varStatus="status">
									<option value="${item.dict_code }"
										<c:if test="${item.dict_code == company }"> class="dd" selected = "selected" </c:if>>${item.name }</option>
								</c:forEach>
							</select> <select class="search-select houseArea" id="city" name="city">
								<option <c:if test="${empty city }">  class="dd" </c:if>
									value="">工作地点</option>
								<c:forEach items="${cityList}" var="item" varStatus="status">
									<option value="${item.dict_code }"
										<c:if test="${item.dict_code == city }"> class="dd" selected = "selected"</c:if>>${item.name }</option>
								</c:forEach>
							</select> <input type="text" id="keys" name="keys" value="${keys }"
								class="select-input" placeholder="请输入职位关键字">
							<button class="btn" id="btnSearch" name="btnSearch">
								<span>搜索</span>
							</button>
						</div>
					</div>
					<ul>
						<c:if test="${not empty list }">
							<c:forEach items="${list}" var="item" varStatus="status">
								<li class="join-list"><a
									href="/about-us/jobs/detail/${item.or_id }">
										<div class="join-main">
											<h4>${item.position }</h4>
											<p class="join-adress">
												<span>工作地点：<%-- ${item.sheng } --%>${item.shi }</span><span>招聘人数：${item.nums }</span>
											</p>
											<span class="company-name">${item.comName }</span>
										</div>
										<p class="join-time">
											<fmt:formatDate value="${item.edit_time }"
												pattern="yyyy.MM.dd " />
											发布
										</p>
								</a></li>
							</c:forEach>
						</c:if>
						<c:if test="${ empty list }">
                    	对不起，暂无任何职位
                    </c:if>
					</ul>


				</div>
				<c:if test="${not empty list }">
					<!-- 分页 pageBar start -->
					<input type="hidden" id="page" name="page" value="1" /> ${pageBar}
					<!-- 分页 pageBar end -->
				</c:if>

			</form>
		</div>
	</div>
	<!--中间container结束-->
		<!--footer尾部开始-->
		<%@ include file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->


	<script src="/resource/js/system/jquery-2.2.0.min.js"
		type="text/javascript"></script>
	<script data-main="/resource/js/project/main-join"
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

		//触发搜索
		$("#btnSearch").click(function(e) {
			doRequest();
		});
	</script>
</body>
</html>
