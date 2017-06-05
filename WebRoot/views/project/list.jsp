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
	<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css"/>
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


<form id="searchForm"  action="/project" method="post">
		
		<!--中间container开始-->
		<div class="container tpy-container">
			<!--移民项目区分begin-->
			<div class="country-classify row">
				<div class="col-xs-12">
					<div class="fl"><span class="text-dark font-bold">选择地区</span><i class="country-line">|</i></div>
					<ul class="country-classify-ul">
						<li><a href="javascript:;" onclick="continentFn('');" <c:if test="${continent==null}">class="on"</c:if>>不限</a></li>
						<c:if test="${continentList != null}">
							<c:forEach var="item" items="${continentList}">
								<li><a href="javascript:;" onclick="continentFn('${item.dictCode}');"  <c:if test="${item.dictCode==continent}">class="on"</c:if>>${item.name}</a></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div class="col-xs-12">
					<div class="fl"><span class="text-dark font-bold">选择国家</span><i class="country-line">|</i></div>
					<ul class="country-classify-ul">
						<li><a href="javascript:;" onclick="countryFn('');" <c:if test="${country==null}">class="on"</c:if>>不限</a></li>
						<c:if test="${countryList != null}">
							<c:forEach var="item" items="${countryList}">
								<li><a href="javascript:;" onclick="countryFn('${item.dictCode}');" <c:if test="${item.dictCode==country}">class="on"</c:if>>${item.name}</a></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div class="col-xs-12">
					<div class="fl"><span class="text-dark font-bold">项目类型</span><i class="country-line">|</i></div>
					<ul class="country-classify-ul">
						<li><a href="javascript:;" onclick="typeFn('');"  <c:if test="${type==null}">class="on"</c:if>>不限</a></li>
						<c:if test="${typeList != null}">
							<c:forEach var="item" items="${typeList}">
								<li><a href="javascript:;" onclick="typeFn('${item.dictCode}');" <c:if test="${item.dictCode==type}">class="on"</c:if>>${item.name}</a></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
			<!--移民项目区分begin-->
			<!--项目列表begin-->
			<ul class="country-hot-ul">
				
				<c:if test="${list != null}">
					<c:forEach var="item" items="${list}">
						<li onclick='window.location.href="/project/info/${item.project_id }"'>
							<div class="country-list-img"><img src="${item.web_project_img}" alt="${item.project_name}" /></div>
							<div class="country-list-text">
								<h2><a href="/project/info/${item.project_id }">${item.project_name}</a></h2>
								<ol>
									<li>签证类型：${item.visa_type}</li>
									<li>语言要求：${item.language}</li>
									<li>投资金额：${item.investments}</li>
									<li>居住条件：${item.live_requirement}</li>
									<%-- <li>总费用预估：<c:if test="${item.immigration_budget>0}">约${item.immigration_budget}起</c:if></li> --%>
								</ol>
							</div>
						</li>
					</c:forEach>
				</c:if>
				
			</ul>
				<input type="hidden" id="continent" name="continent" value="${continent}"/>
				<input type="hidden" id="type" name="type" value="${type}"/>
				<input type="hidden" id="country" name="country" value="${country}"/>
			<!--项目列表end-->
			<div id="pageBar">
				<input type="hidden" id="page" name="page" value="1" />
				${pageBar}
			</div>
		</div>
		
</form>
		<!--中间container结束-->
		
		<!--footer尾部开始-->
				<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->
		<script type="text/javascript" src="/resource/js/system/jquery-2.2.0.min.js"></script>
		<script data-main="/resource/js/project/main" src="/resource/js/system/require.js"></script>
		<script>
			$(function(){
				//绑定翻页
				$('.country-new-page a').each(function(index, element) {
					if ($(element).attr("data-page") != undefined) {
						$(this).click(function(e) {
							$("#page").val($(element).attr("data-page"));
							doRequest();
						});
					}
				});
			})
			
			//提交查询请求
			function doRequest() {
				$("#searchForm").submit();
			}
			
			//查询洲条件
			function continentFn(code) {
				if(code) {
					$("#continent").val(code);
				} else {
					$("#continent").val("");
				}
				doRequest();
			}
			
			//查询类型条件
			function typeFn(code) {
				if(code) {
					$("#type").val(code);
				} else {
					$("#type").val("");
				}
				doRequest();
			}
			
			//查询国家条件
			function countryFn(code) {
				if(code) {
					$("#country").val(code);
				} else {
					$("#country").val("");
				}
				doRequest();
			}
		
		</script>
		
</body>
</html>

