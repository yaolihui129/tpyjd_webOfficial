<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
	name="viewport" id="viewport">
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>太平洋出国|关于我们|联系我们</title>
<!--以下系统css-->
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/assess.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/about.css">
<link rel="stylesheet" href="/resource/css/country.css" type="text/css" />
</head>

<body class="bg-grey">
<!--header头部开始-->
			<%@ include  file="/views/nav/header.jsp"%>
			<%@ include  file="/views/nav/loginbox.jsp"%>
		<!--header头部结束-->
		<!--other banner开始-->
	<%@ include  file="/views/nav/banner.jsp"%>
	<!--other banner结束-->
    <div class="container tpy-container">
        <!--中间container开始-->
        <div class="row margin-auto">
            <div class="col-xs-2 about-nav">
                <div class="company-logo"><img src="/resource/images/about-logo.png"></div>
                <ul>
                    <script src="/common/about/8"></script>
                </ul>
                <div class="about-bg"><img src="/resource/images/about-bg.png"></div>
            </div>
            <div class="col-xs-10 about-main">
                <h2 class="profile-title">联系我们<span>Contact us</span></h2>
                <div class="profile-content">
                    <div class="lyx-tab">
                        <ul class="lyx-tab-hand country-life-hand">
                        <c:choose>
                        		<c:when test="${!empty area && area=='beijing'}">
                        		<li class="on">北京</li>
                        		</c:when>
                        		<c:otherwise>
                        		<li>北京</li>
                        		</c:otherwise>
                        	</c:choose>
                        	<c:choose>
                        		<c:when test="${!empty area && area=='filiale'}">
                        			<li id="filiale" class="on">国内分公司</li>
                        		</c:when>
                        		<c:otherwise>
                        			<li id="filiale">国内分公司</li>
                        		</c:otherwise>
                        	</c:choose>
                            <c:if test="${!empty foreign}"><li>国外分公司</li></c:if>
                        </ul>
                        <div class="lyx-tab-container">
                            
                            <c:choose>
	                        		<c:when test="${!empty area && area=='beijing'}">
	                        		<div class="lyx-tab-box show">
	                                ${beijing}
	                            </div>
	                        		</c:when>
	                        		<c:otherwise>
	                        		<div class="lyx-tab-box country-article-body">
	                                ${beijing}
	                            </div>
	                        		</c:otherwise>
	                        	</c:choose>
                            <c:choose>
	                        		<c:when test="${!empty area && area=='filiale'}">
	                        			<div class="lyx-tab-box show">
		                                ${domestic}
		                            </div>
	                        		</c:when>
	                        		<c:otherwise>
	                        			<div class="lyx-tab-box country-article-body">
		                                ${domestic}
		                            </div>
	                        		</c:otherwise>
                        		</c:choose>
                            <div class="lyx-tab-box country-article-body">
                                ${foreign}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--footer尾部开始-->
	<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->
    <script data-main="/resource/js/project/main-join" src="/resource/js/system/require.js"></script>
 </body>
 </html>