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
    <title>太平洋出国|移民评估</title>

	<!--以下系统css-->
	<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
	<!--以下页面css-->
	<link rel="stylesheet" href="/resource/css/assess.css" type="text/css" />
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

		
		<div class="container tpy-container"><!--中间container开始-->
			<div class="row margin-auto">
				<div class="col-xs-12 assess-self-result">
					<h2>
						<span class="border-radius-title">您的评估条件</span>
						<a href="/estimate">重新评估</a>
					</h2>
					<ul class="assess-self-ul">
						<li>意向国家：<span>${estimate.country_name}</span></li>
						<li>移民目的：<span>${estimate.hotspots_name}</span></li>
						<li>您的家庭净资产：<span>${estimate.asset_name}</span></li>
						<li>将用于移民预算资金：<span><c:if test="${estimate.immigration_budget_name > 0}">${estimate.immigration_budget_name}万元</c:if></span></li>
						<li>您的最高学历：<span>${estimate.qualifications_name}</span></li>
						<li>主申请人外语能力：<span>${estimate.language_name}</span></li>
						<li>您的管理经验：<span>${estimate.manage_name}</span></li>
						<li>可接受海外居住时间：<span>${estimate.live_requirement_name}</span></li>
					</ul>
				</div>
			</div>
			<div class="row margin-auto">
				<div class="col-xs-12 assess-self-result">
					<h2>
						<span class="border-radius-title">您的评估结果</span>
					</h2>
					<ul class="assess-result-ul">
						<c:if test="${msg != null}">${msg}</c:if>
						
						<c:if test="${resultList != null}">
							<c:forEach var="item" items="${resultList}">
								<li>
									<h2>${item.projectName}</h2>
									<ol class="col-xs-10 assess-result-ol">
										<li <c:choose><c:when test="${item.country==null || item.country==1}"> class="assess-no-match2"</c:when><c:when test="${item.country==2}"> class="assess-no-match"</c:when></c:choose>>
												意向国家：<span>${item.country_name}</span></li>
										<li <c:choose><c:when test="${item.hotspots==null || item.hotspots==1}"> class="assess-no-match2"</c:when><c:when test="${item.hotspots==2}"> class="assess-no-match"</c:when></c:choose>>
												移民目的：<span>${item.hotspots_name}</span></li>
										<li<c:choose><c:when test="${item.asset==null || item.asset==1}"> class="assess-no-match2"</c:when><c:when test="${item.asset==2}"> class="assess-no-match"</c:when></c:choose>>
												资产要求：<span>${item.asset_name}</span></li>
										<li<c:choose><c:when test="${item.immigration_budget==null || item.immigration_budget==1}"> class="assess-no-match2"</c:when><c:when test="${item.immigration_budget==2}"> class="assess-no-match"</c:when></c:choose>>
												移民预算：<span>${item.immigration_budget}万元</span></li>
										<li<c:choose><c:when test="${item.qualifications==null || item.qualifications==1}"> class="assess-no-match2"</c:when><c:when test="${item.qualifications==2}"> class="assess-no-match"</c:when></c:choose>>
												学历要求：<span>${item.qualifications_name}</span></li>
										<li<c:choose><c:when test="${item.age==null || item.age==1}"> class="assess-no-match2"</c:when><c:when test="${item.age==2}"> class="assess-no-match"</c:when></c:choose>>
												年龄需求：<span>${item.age_name}</span></li>
										<li<c:choose><c:when test="${item.language==null || item.language==1}"> class="assess-no-match2"</c:when><c:when test="${item.language==2}"> class="assess-no-match"</c:when></c:choose>>
												语言要求：<span>${item.language_name}</span></li>
										<li<c:choose><c:when test="${item.manage==null || item.manage==1}"> class="assess-no-match2"</c:when><c:when test="${item.manage==2}"> class="assess-no-match"</c:when></c:choose>>
												管理经验：<span>${item.manage_name}</span></li>
										<li<c:choose><c:when test="${item.live_requirement==null || item.live_requirement==1}"> class="assess-no-match2"</c:when><c:when test="${item.live_requirement==2}"> class="assess-no-match"</c:when></c:choose>>
												居住要求：<span>${item.live_requirement_name}</span></li>
									</ol>
									<div class="col-xs-2 assess-result-result">
										<p class="assess-percent">${item.matching}%</p>
										<p>匹配度</p>
										<p><a href="/project/info/${item.projectId}" class="assess-apply-btn">申请项目</a></p>
									</div>
									<h3>项目特征</h3>
									<div class="row assess-feature">
										<div class="col-xs-6">签证类型：<span>${item.visa_type_name}</span></div>
										<div class="col-xs-6">推荐指数：
											<c:choose>
												<c:when test="${item.recommended==1}"><i></i></c:when>
												<c:when test="${item.recommended==2}"><i></i><i></i></c:when>
												<c:when test="${item.recommended==3}"><i></i><i></i><i></i></c:when>
												<c:when test="${item.recommended==4}"><i></i><i></i><i></i><i></i></c:when>
												<c:when test="${item.recommended==5}"><i></i><i></i><i></i><i></i><i></i></c:when>
											</c:choose>
										</div>
									</div>
									<div class="row assess-feature">
										<div class="col-xs-12">
											<div class="fl">项目优势：</div>
											<ol class="assess-result-advantage">
                        
												<c:if test="${!empty item.advantage}">
													<c:forEach var="ary" items="${item.advantage}" varStatus="status">
														<li>${status.count}、${ary}</li>
													</c:forEach>
												</c:if>
												
											</ol>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
						
						
					</ul>
				</div>
			</div>
		</div><!--中间container结束-->
				
		<!--footer尾部开始-->
				<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->


	<script data-main="/resource/js/project/main" src="/resource/js/system/require.js"></script>
</body>
</html>




