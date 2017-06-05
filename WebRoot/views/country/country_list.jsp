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
	<link rel="stylesheet" type="text/css" href="../resource/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="../resource/css/layer.css" type="text/css" />
	<link rel="stylesheet" href="../resource/css/common.css" type="text/css" />
	<!--以下页面css-->
	<link rel="stylesheet" href="../resource/css/country.css" type="text/css" />
	</head>
	<body class="bg-grey">
		<%@ include  file="/views/nav/header.jsp"%>
		<!--other banner开始-->
	 	<%@ include  file="/views/nav/banner.jsp"%>
		<!--other banner结束-->

		<form id="couSear">
		<div class="container tpy-container"><!--中间container开始-->
			<div class="country-classify row"><!--国家地区，移民目的begin-->
				<div class="col-xs-12">
					<div class="fl"><span class="text-dark font-bold">国家地区</span><i class="country-line">|</i></div>
					<ul class="country-classify-ul">
						<li><a onclick="continent('')" <c:if test="${empty continent_name}">class="on"</c:if> href="#">不限</a></li>
						<c:forEach items="${continents}" var="contin">
							<li><a href="#" onclick="continent('${contin.continentName}')" <c:if test="${contin.continentName == continent_name}"> class="on"</c:if>>${contin.continentName}</a></li>
						</c:forEach>
					</ul>
					<input type="hidden" name="continent_name" value="${continent_name}"/>
				</div>
				<div class="col-xs-12">
					<div class="fl"><span class="text-dark font-bold">移民目的</span><i class="country-line">|</i></div>
					<ul class="country-classify-ul">
						<li><a onclick="hotspot('')" <c:if test="${empty hotspot}">class="on"</c:if> href="#">不限</a></li>
						<c:forEach items="${hotspots}" var="hotsp">
							<li><a href="#" onclick="hotspot('${hotsp.dictCode}')" <c:if test="${hotsp.dictCode == hotspot}"> class="on"</c:if>>${hotsp.name}</a></li>
						</c:forEach>
					</ul>
					<input type="hidden" name="hotspot" value="${hotspot}"/>
				</div>
			</div><!--国家地区，移民目的end-->
			</form>
			<ul class="row country-list"><!--国家列表begin-->
			<c:forEach items="${countryList}" var="country">
				<li class="col-xs-12">
					<a href="/country/survey/${country.countryId}">
						<div class="country-list-img"><img src="${country.standPicOfficial }" alt="${country.countryName}" /></div>
						<div class="country-list-text">
							<h2>${country.countryName}</h2>
							<p>${country.countryBrief}</p>
						</div>
					</a>
				</li>
			</c:forEach>
			</ul><!--国家列表end-->
		</div><!--中间container结束-->
				<div class="section fp-auto-height footer">
			<div class="foot">
					<!--footer尾部开始-->
					<%@ include  file="/views/nav/footer.jsp"%>
					<!--footer尾部结束-->
				 <%@include file="/views/nav/loginbox.jsp" %>
			</div>
			<!---->
	</div>
		

	<script data-main="../resource/js/project/main" src="../resource/js/system/require.js"></script>
	<script type="text/javascript">
		function hotspot(hotsp){
			$("input[name='hotspot']").val(hotsp);
			$("#couSear").submit();
		}
		function continent(counti){
			$("input[name='continent_name']").val(counti);
			$("#couSear").submit();
		}
	</script>
</body>
</html>