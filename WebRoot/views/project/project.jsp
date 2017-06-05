<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport">
    <meta name="format-detection"content="telephone=no" />   	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--<meta name="renderer" content="webkit">-->
    <title>太平洋出国|移民项目</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/country.css" type="text/css" />
</head>

<body class="bg-grey">
	<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
	<!--header头部结束-->


 	<!--other banner开始-->
 		<%@ include  file="/views/nav/banner.jsp"%>
	<!--other banner结束-->


	<!--项目介绍开始-->
	<div class="container tpy-container">
		<div class="row margin-auto  bg-white">
			<h2 class="project-h2">
				<span class="border-radius-title">${project.project_name}</span>
				<!-- <a class="blue-btn fr" href="#">预约服务</a> -->
			</h2>
			<div class="col-xs-12 project-intro">
				<div class="country-list-img"><img src="${project.web_project_img}" alt="" /></div>
				<div class="country-list-text">
					<p>${project.introduce}</p>
				</div>
			</div>
			<hr />
			<div class="col-xs-6 project-classfy">
				<ul>
					<li>投资类型：${project.project_type}</li>
					<li>签证类型：${project.visa_type}</li>
					<li>居住要求：${project.live_requirement}</li>
					<li>语言要求：${project.language}</li>
					<li>学历要求：${project.qualifications}</li>
					<li>管理经验：${project.manage}</li>
					<li>推荐指数：<c:choose>
												<c:when test="${project.recommended==1}"><i></i></c:when>
												<c:when test="${project.recommended==2}"><i></i><i></i></c:when>
												<c:when test="${project.recommended==3}"><i></i><i></i><i></i></c:when>
												<c:when test="${project.recommended==4}"><i></i><i></i><i></i><i></i></c:when>
												<c:when test="${project.recommended==5}"><i></i><i></i><i></i><i></i><i></i></c:when>
											</c:choose>
					</li>
				</ul>
			</div>
			<div class="col-xs-6 project-classfy">
				<p class="pb-10">项目优势：</p>
				<ul>
					<c:if test="${project.advantage != null}">
						<c:forEach var="item" items="${project.advantage}" varStatus="status">
							<li>${status.count}、${item}</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<c:if test="${project.project_status==projectStatus}">
				<div class="project-sign"><img src="/resource/images/project-end.png" alt="" /></div>
			</c:if>
		</div>
	</div>
	<!--项目介绍结束-->
	<!--项目分类介绍begin--> 
	<div class="lyx-tab">
		<ul class="lyx-tab-hand">
			<li class="on" onclick="window.location.href='#xmbj'"><a href=""></a>项目介绍</li>
			<li onclick="window.location.href='#sqtj'">申请条件</li>
			<c:if test="${project.subProjectList != null and !empty project.subProjectList}">
				<li onclick="window.location.href='#tzxm'">投资项目</li>
			</c:if>
			<c:if test="${project.projectApList != null and !empty project.projectApList}">
				<li onclick="window.location.href='#bllc'">办理流程</li>
			</c:if>
		</ul>
		<div class="country-item-box">
			
			<div class="lyx-tab-box  show">
				<!--项目背景begin-->
				<div class="country-one" id="xmbj">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">项目介绍</h2>
							<div class="country-article-body">${project.web_introduce}</div>
						</div>
					</div>
				</div>
				<!--项目背景end-->
				<!--申请条件begin-->
				<div class="country-one" id="sqtj">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">申请条件</h2>
							<div class="country-article-body">${project.apply_condition}</div>
						</div>
					</div>
				</div>
				<!--申请条件end-->				
				<!--投资项目begin-->
				<c:if test="${project.subProjectList != null and !empty project.subProjectList}">
				<div class="country-one" id="tzxm">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">投资项目</h2>
							<ul class="project-ul">
								
								<c:forEach var="item" items="${project.subProjectList}">
									<li>
										<p class="project-img"><a href="/project/info/${item.projectId}"><img src="${item.webProjectImg}" alt="" /></a></p>
										<h2><a href="/project/info/${item.projectId}">${item.projectName}</a></h2>
										<p class="project-text">${item.introduce}</p>
									</li>
								</c:forEach>
								
							</ul>
						</div>
					</div>
				</div>
				</c:if>
				<!--投资项目end-->
				<!--办理流程begin-->
				<c:if test="${project.projectApList != null and !empty project.projectApList}">
				<div class="country-one" id="bllc">
					<div class="container">
						<div class="row margin-auto">
							<h2 class="country-article-title">办理流程</h2>
							<ul class="project-flow">
								<c:forEach var="item" items="${project.projectApList}">
									<li><i></i><span>${item.name}</span></li>
								</c:forEach>
								
							</ul>
						</div>
					</div>
				</div>
				</c:if>
				<!--办理流程end-->
			</div>
			
		</div>
	</div>
	<!--项目分类介绍end-->
		<!--footer尾部开始-->
				<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->
		

	<script data-main="/resource/js/project/main-country" src="/resource/js/system/require.js"></script>
	</body>
</html>


