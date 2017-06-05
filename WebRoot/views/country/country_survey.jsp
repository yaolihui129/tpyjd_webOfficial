<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport">
    <meta name="format-detection"content="telephone=no" />   	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--<meta name="renderer" content="webkit">-->
    <title>太平洋出国|移民国家</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/country.css" type="text/css" />
<!--以下页面css-->
	<link rel="stylesheet" href="/resource/css/news.css" type="text/css" />
</head>

<body class="bg-grey">
 			<div class="section fp-auto-height header">
				<!--header头部开始-->
					<%@ include  file="/views/nav/header.jsp"%>
				<!--header头部结束-->
			</div>
			<!--other banner开始-->
		 	<%-- <%@ include  file="/views/nav/banner.jsp"%> --%>
			<!--other banner结束-->
<!-- 当前位置 -->
<div class="news-breadline">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">当前位置:<a href="/">首页</a><span>></span><a href="/country">移民国家</a><span>>${country.countryName}</span></div>
				</div>
			</div>
</div>

	<!--国家介绍开始-->
	<div class="container tpy-container">
		<div class="row margin-auto">
			<div class="col-xs-12 country-intro flex">
				<div class="country-list-img"><img src="${country.stylePicOfficial }" alt="${country.countryName}" /></div>
				<div class="country-list-text">
					<h2>${country.countryName}</h2>
					<p>${country.countryBrief}</p>
				</div>
			</div>
		</div>
	</div>
	<!--国家介绍结束-->
	<!--国家分类介绍begin-->
	<div class="lyx-tab">
		<ul class="lyx-tab-hand">
			<li class="on">国家概况</li>
			<li><a href="/country/project/${country.countryId}">移民项目</a></li>
			<li><a href="/country/policy/${country.countryId}">最新政策</a></li>
		</ul>
		<div class="lyx-tab-container country-item-box">
			<!--国家概况begin-->
			<div class="lyx-tab-box  show">
				<!--国家简介begin-->
				<div class="country-one">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">概况</h2>
							<p class="country-article-body">${country.countrySurvey}</p>
						</div>
					</div>
				</div>
				<!--国家简介begin-->
				<!--热门项目begin-->
				<div class="country-one">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">热门项目</h2>
							<ul class="country-hot-ul">
							<c:forEach items="${projects}" var="project">
								<li>
									<div class="country-list-img"><img src="${project.webProjectImg }" alt="${project.projectName }" /></div>
									<div class="country-list-text">
										<h2><a href="/project/info/${project.projectId}" class="project_name_size">${project.projectName }</a></h2>
										<p>${project.introduce}</p>
									</div>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<!--热门项目end-->
				<!--移民生活begin-->
				<div class="country-one">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">移民生活</h2>
							<div class="lyx-tab">
								<ul class="lyx-tab-hand country-life-hand">
									<c:if test="${not empty country.livingEnvironment }">
										<li class="on">居住环境</li>
									</c:if>
									<c:if test="${not empty country.socialWelfare }">
									<li>社会福利</li>
									</c:if>
									<c:if test="${not empty country.educationalLevel }">
									<li>教育水准</li>
									</c:if>
									<c:if test="${not empty country.investHome }">
									<li>投资置业</li>
									</c:if>
									<c:if test="${not empty country.employmentEnvironment }">
									<li>就业环境</li>
									</c:if>
									<c:if test="${not empty country.passportNationality }">
									<li>护照国籍</li>
									</c:if>
								</ul>
								<div class="lyx-tab-container">
									<c:if test="${not empty country.livingEnvironment }">
									<div class="lyx-tab-box country-article-body show">
										<p>${country.livingEnvironment }</p>
									</div>
									</c:if>
									<c:if test="${not empty country.socialWelfare }">
									<div class="lyx-tab-box country-article-body">
										<p>${country.socialWelfare }</p>
									</div>
									</c:if>
									<c:if test="${not empty country.educationalLevel }">
									<div class="lyx-tab-box country-article-body">
										<p>${country.educationalLevel }</p>
									</div>
									</c:if>
									<c:if test="${not empty country.investHome }">
									<div class="lyx-tab-box country-article-body">
										<p>${country.investHome }</p>
									</div>
									</c:if>
									<c:if test="${not empty country.employmentEnvironment }">
									<div class="lyx-tab-box country-article-body">
										<p>${country.employmentEnvironment }</p>
									</div>
									</c:if>
									<c:if test="${not empty country.passportNationality }">
									<div class="lyx-tab-box country-article-body">
										<p>${country.passportNationality }</p>
									</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--移民生活end-->
			</div>
			<!--国家概况end-->
		</div>
	</div>
	<!--国家分类介绍end-->
	<div class="section fp-auto-height footer">
			<div class="foot">
					<!--footer尾部开始-->
					<%@ include  file="/views/nav/footer.jsp"%>
					<!--footer尾部结束-->
				 <%@include file="/views/nav/loginbox.jsp" %>
			</div>
			<!---->
	</div>
	<script data-main="/resource/js/project/main-country" src="/resource/js/system/require.js"></script>
	<script type="text/javascript" src="/resource/js/system/jquery-2.2.0.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//限制字符个数
		$('.project_name_size').each(function(){
		var maxwidth=35;
		if($(this).text().length>maxwidth){
		$(this).text($(this).text().substring(0,maxwidth));
		$(this).html($(this).html()+'…');
		}
		});
	});
	</script>
	
	</body>
</html>