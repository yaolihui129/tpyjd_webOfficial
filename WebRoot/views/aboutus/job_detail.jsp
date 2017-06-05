<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"
	name="viewport" id="viewport">
<meta name="format-detection" content="telephone=no" />

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--<meta name="renderer" content="webkit">-->
<title>太平洋出国|关于我们</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/assess.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/about.css">
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
</head>

<body class="bg-grey">
	<div class="tpy-header">
		<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
		<!--header头部结束-->
	</div>
	<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--banner 开始-->
	<%@ include  file="/views/nav/banner.jsp"%>
	<!--banner 结束-->
	
	<!-- <div class="tpy-other-banner">
		other banner开始
		<img src="/resource/images/other-banner.jpg" alt="" />
		<div class="other-breadline">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						当前位置：<a href="/">首页</a><span>></span><a href="#">关于我们</a><span>></span><a
							href="/about-us/jobs">诚聘英才</a><span>></span><a href="#">职位详情</a>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!--other banner结束-->
	<div class="container tpy-container">
		<!--中间container开始-->
		<div class="row margin-auto">
			<div class="col-xs-2 about-nav">
				<div class="company-logo">
					<img src="/resource/images/about-logo.png">
				</div>
				<ul>
					<script src="/common/about/6"></script>
				</ul>
				<div class="about-bg">
					<img src="/resource/images/about-bg.png">
				</div>
			</div>
			<div class="col-xs-10 about-main">
				<h2 class="profile-title">
					诚聘英才<span>Join us</span>
				</h2>
				<div class="join-content">
					<div class="job-title">
						<h2>${job.position }</h2>
						<p>工作地点：<%-- ${job.sheng }  --%>${job.shi }</p>
						<p>招聘人数：${job.nums }</p>
						<p>学历要求：${job.edu }</p>
						<p>工作经验：${job.workExp }</p>
						<p>
							发布时间：
							<fmt:formatDate value="${job.edit_time }" pattern="yyyy-MM-dd " />
						</p>
						<p>所属公司：${job.comName }</p>
						<div class="clear"></div>
					</div>
					<div class="job-title">
						<h4>任职要求</h4>
						${job.demand}
					</div>
					<div class="job-title">
						<h4>职位描述</h4>
						${job.description }
					</div>
					<div class="job-title">
                        <h4>其他</h4>
                        ${job.job_set }
                    </div>
				</div>
			</div>
		</div>
	</div>
	<!--中间container结束-->
		<!--footer尾部开始-->
		<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->

	<script data-main="/resource/js/project/main-join"
		src="/resource/js/system/require.js"></script>
</body>
</html>
