<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			<li><a href="/country/survey/${country.countryId}">国家概况</a></li>
			<li class="on">移民项目</li>
			<li><a href="/country/policy/${country.countryId}">最新政策</a></li>
		</ul>
		<div class="lyx-tab-container country-item-box">
			<!--移民项目begin-->
			<div class="lyx-tab-box show">
				<div class="lyx-tab country-one">
					<ul class="lyx-tab-hand country-life-hand">
					<c:forEach items="${counProjects}" var="counProject" varStatus="status">
						<li <c:if test="${status.count == 1}">class="on"</c:if>>${counProject.projectName}</li>
					</c:forEach>
					</ul>
					<div class="lyx-tab-container container">
						<!--投资移民begin-->
						<c:forEach items="${counProjects}" var="coProject" varStatus="status">
						<div  <c:if test='${status.count == 1}'>class="lyx-tab-box show"</c:if><c:if test='${status.count != 1}'>class="lyx-tab-box"</c:if>>
							<!-- 一级项目 -->
							<c:forEach items="${coProject.firstLevels}" var="firstLevel">
							<div class="country-item-intro">
								<h3 class="country-item-title">${firstLevel.project_name}</h3>
								<h3 class="country-item-title2">项目优势</h3>
								<ul class="country-item-advantage">
								<c:set value="${ fn:split(firstLevel.advantage, ';') }" var="advants" />
								<c:forEach items="${advants}" var="advant"> 
									<li>${advant}</li>
								</c:forEach>
								</ul>
								<table class="country-item-table">
									<tr><c:if test="${not empty firstLevel.visa_type }"><th>签证类型</th></c:if>
									<c:if test="${not empty firstLevel.investments }"><th>投资额度</th></c:if>
									<c:if test="${not empty firstLevel.language }"><th>语言要求</th></c:if>
									<c:if test="${not empty firstLevel.live_requirement }"><th>居住要求</th></c:if>
									<c:if test="${not empty firstLevel.qualifications }"><th>学历要求</th></c:if></tr>
									<tr>
										<c:if test="${not empty firstLevel.visa_type }"><td>${firstLevel.visa_type}</td></c:if>
										<c:if test="${not empty firstLevel.investments }"><td>${firstLevel.investments}</td></c:if>
										<c:if test="${not empty firstLevel.language }"><td>${firstLevel.language}</td></c:if>
										<c:if test="${not empty firstLevel.live_requirement }"><td>${firstLevel.live_requirement}</td></c:if>
										<c:if test="${not empty firstLevel.qualifications }"><td>${firstLevel.qualifications}</td></c:if>
									</tr>
								</table>
								<p class="text-right"><a href="/project/info/${firstLevel.project_id}" class="country-seemore">查看详情</a></p>
							</div>
							</c:forEach>
							<ul class="country-hot-ul">
							<c:forEach items="${coProject.secondLevels}" var="secondLevel">
								<li onclick="window.location.href='/project/info/${secondLevel.project_id}'">
									<div class="country-list-img"><img src="${secondLevel.web_project_img}" alt="${secondLevel.project_name}" /></div>
									<div class="country-list-text">
										<h2><a href="#">${secondLevel.project_name}</a></h2>
										<p>${secondLevel.introduce}</p>
									</div>
								</li>
							</c:forEach>	
							</ul>
						</div>
						</c:forEach>
						<!--投资移民end-->
					</div>
				</div>
			</div>
			<!--移民项目end-->
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
	</body>
</html>