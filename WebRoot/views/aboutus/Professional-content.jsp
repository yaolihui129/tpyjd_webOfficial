<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport">
    <meta name="format-detection"content="telephone=no" />   	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--<meta name="renderer" content="webkit">-->
    <title>太平洋出国|关于我们</title>

	<!--以下系统css-->
	<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
	<!--以下页面css-->
	<link rel="stylesheet" href="/resource/css/assess.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/about.css">
	
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
        <div class="row margin-auto about-all">
            <div class="col-xs-2 about-nav">
                <div class="company-logo"><img src="/resource/images/about-logo.png"></div>
                <ul>
                    <script src="/common/about/4"></script>
                </ul>
                <div class="about-bg"><img src="/resource/images/about-bg.png"></div>
            </div>
            <div class="col-xs-10 about-main">
                <h2 class="profile-title">专家简介<span> Expert profile</span></h2>
                <div class="team-content">
                    <div class="Self-introduction">
                        <dl>
                            <dt><img src="${user.headImg}"></dt>
                            <dd>
                                <h4>${user.englishName}</h4>
                                <p class="person-title">${user.jobTitle}</p>
                                <p class="person-icon">
                                <c:forEach items="${expert_countries}" var="country"  varStatus="status">
                                	<c:choose>
                                		<c:when test="${status.index==0}">
                                			<span class="person-blue">${country}</span>
                                		</c:when>
                                		<c:when test="${status.index==1}">
                                			<span class="person-orange">${country}</span>
                                		</c:when>
                                		<c:otherwise>
                                			<span class="person-green">${country}</span>
                                		</c:otherwise>
                                	</c:choose>
                                </c:forEach>
                                </p>
                                
                                <p class="person-star">
                                <c:if test="${!empty stars}">
                                <c:forEach var="i" begin="0" end="4" varStatus="status">
                                <c:choose>
                                		<c:when test="${status.index<stars}">
                                		<span class="on-star"></span>
                                		</c:when>
                                		<c:otherwise>
                                		<span></span>
                                		</c:otherwise>
                                </c:choose>
                                </c:forEach>
                                </c:if>
                                </p>
                                <p class="person-evaluate">
                                		<c:if test="${!empty city}">
	                                <span class="person-adress">${city} </span>
	                               </c:if>
	                               <c:if test="${!empty liked && liked>0}">
	                                <span class="person-dianzan">${liked}</span>
	                                </c:if>
                                </p>
                                <c:if test="${!empty user.wechat}">
                                <div class="person-contact">
                                    <a href="javascript:;" class="weixin">微信咨询</a>
                                    <div class="erwei-bg hide">
                                        <img src="${user.wechat}">
                                    </div>
                                </div>
                                </c:if>
                            </dd>

                        </dl>
                        <div class="clear"></div>
                    </div>
                    <div class="individual-resume">
                        <h4>个人简介</h4>
                        <p>${user.introduce}</p>
                        <c:if test="${!empty video}">
                        <div class="js-vedio">
                            <embed src='${video}' allowFullScreen='true' quality='high' width='726' height='480' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash' />
                        </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
   	<!--footer尾部开始-->
	<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->

    <script data-main="/resource/js/project/main-team" src="/resource/js/system/require.js"></script>

    </body>
</html>
