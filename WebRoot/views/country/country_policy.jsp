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
			<li><a href="/country/survey/${country.countryId}">国家概况</a></li>
			<li><a href="/country/survey/${country.countryId}">移民项目</a></li>
			<li class="on">最新政策</li>
		</ul>
		<form id="searchForm" action="/country/policy/${country.countryId}" method="post">
		<div class="lyx-tab-container country-item-box">
			<!--最新政策begin-->
			<div class="lyx-tab-box show">
				<div class="country-one">
				 <c:forEach items="${list}" var="item" varStatus="status">
				 <c:if test="${status.count == 1}">
					<div class="country-new-policy container">
						<div class="country-list-img"><img src="${item.list_img}" alt="${item.section_name}" /></div>
						<div class="country-list-text">
							<h2 class="alignItem">
								<div class="country-timestamp">
									<p class="country-timestamp-day"><fmt:formatDate value="${item.publish_time}" pattern="dd"/></p>
									<p class="country-timestamp-date"><fmt:formatDate value="${item.publish_time}" pattern="yyyy.MM"/></p>
								</div>
								<a href="#" class="first-title-size">${item.title}</a>
							</h2>
							<div class="text-justify">${item.contents}</div>
							<p class="text-right"><a href="/information/preview/${item.info_id}"><span class="country-seemore">查看详细</span></a></p>
						</div>
					</div>
				</c:if>	
					<!--推荐政策end-->
					<!--政策列表begin-->
					 <c:if test="${status.count != 1}">
					<ul class="country-new-list container">
						<li>
							<div class="country-timestamp">
								<p class="country-timestamp-day"><fmt:formatDate value="${item.publish_time}" pattern="dd"/></p>
								<p class="country-timestamp-date"><fmt:formatDate value="${item.publish_time}" pattern="yyyy.MM"/></p>
							</div>
							<div class="country-list-text">
								<h2><a href="/information/preview/${item.info_id}" class="second-title-size">${item.title}</a></h2>
								<div class="second-text-justify">${item.contents}</div>
							</div>
						</li>
					</ul>
					</c:if>
					</c:forEach>
					<!--政策列表end-->
					
					<div id="pageBar">
                    	<input type="hidden" name="page" id="page" value="1" />
                        ${pageBar}
                    </div>
				</div>
				
			</div>
			<!--最新政策end-->
		</div>
		</form>
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
	$(document).ready(function(){
		//限制字符个数
		$('.text-justify').each(function(){
		var maxwidth=120;
		if($(this).text().length>maxwidth){
		$(this).text($(this).text().substring(0,maxwidth));
		$(this).html($(this).html()+'…');
		}
		});
		//限制字符个数
		$('.first-title-size').each(function(){
		var maxwidth=50;
		if($(this).text().length>maxwidth){
		$(this).text($(this).text().substring(0,maxwidth));
		$(this).html($(this).html()+'…');
		}
		});
		//限制字符个数
		$('.second-text-justify').each(function(){
		var maxwidth=120;
		if($(this).text().length>maxwidth){
		$(this).text($(this).text().substring(0,maxwidth));
		$(this).html($(this).html()+'…');
		}
		});
		//限制字符个数
		$('.second-title-size').each(function(){
		var maxwidth=50;
		if($(this).text().length>maxwidth){
		$(this).text($(this).text().substring(0,maxwidth));
		$(this).html($(this).html()+'…');
		}
		});
		});
	</script>
	</body>
</html>