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
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
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
            
                <h2 class="profile-title">专业团队<span>Professional team</span></h2>
                <div class="team-content">
                		<c:forEach items="${result}" var="team">
                    <h2 class="honoraty-title team-title"><span></span>${team.team_name}</h2>
                    <div class="clear"></div>
                    <div class="team-main">
                        <div class="team-slider">
                            <ul class="counselor-bxslider">
                            	   <c:forEach items="${team.experts}" var="expert">
                                <li>
                                		<a href="/about-us/professional-team/expert/${expert.team_id}">
                                    <div class="Consultant-img">
                                        <img src="${expert.head_img}">
                                        <div class="Consultant-text">
                                            <p class="Consultant-text-name">${expert.english_name}</p>
                                            <p class="Consultant-text-font">${expert.job_title}</p>
                                        </div>
                                        <div class="Consultant-pic-mark">
                                            <p class="Consultant-mark-name">${expert.english_name}</p>
                                            <p>${expert.job_title}</p>
                                            <p class="Consultant-mark-font">${expert.introduce}</p>
                                        </div>
                                    </div>
                                    </a>
                                </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
				</c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!--中间container结束-->
    		
	<!--footer尾部开始-->
	<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->

    <script data-main="/resource/js/project/main-team" src="/resource/js/system/require.js"></script>
    </body>
</html>