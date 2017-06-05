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
<title>太平洋出国|最新活动</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/activity.css" type="text/css" />
</head>
<body>
	<div class="tpy-header">
		<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
		<!--header头部结束-->
	</div>
<%@ include  file="/views/nav/loginbox.jsp"%>
	<div class="tpy-other-banner">
		<!--other banner开始-->
		<img src="/resource/images/other-banner.jpg" alt="" />
		<div class="other-breadline">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						当前位置：<a href="/">首页</a><span>></span><a href="#">最新活动</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--other banner结束-->


	<!--中间container开始-->
	<div class="container tpy-container">
		<p class="text-center">
			<span class="activity-btn">即将开始</span>
		</p>
		<!--活动即将开始begin-->
		<c:if test="${not empty comingActivity}">
			<ul class="activity-flow">
				<c:forEach items="${comingActivity}" var="item" varStatus="status">
					<li><i></i>
						<div class="activity-every">
							<div class="activity-list-img">
								<img src="${item.activityImage1 }" alt="" />
							</div>
							<div class="activity-list-text">
								<h2><a href="${item.url}"  target="_blank">${item.activityName }</a></h2>
								<p>
									<fmt:formatDate value="${item.startTime }"
										pattern="yyyy年MM月dd日 hh:mm " />
								</p>
								<p class="activity-address">${item.activityAddress }</p>
								<div class="blue-btn js-messageBtn" data-id="${item.activityId }">我要参加</div>
							</div>
						</div></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${empty comingActivity}">
				很抱歉，目前没有任何活动举办
			</c:if>

		<!--活动即将开始end-->
		<!--年份循环begin-->
		<c:forEach items="${endList}" var="item" varStatus="status">
			<div class="activity-time-box js-time-box">
				<p class="text-center js-activity-times">
					<span class="activity-time-btn ">${item.year } <i></i></span>
				</p>
				<ul class="activity-time-ul">
					<!-- 月份循环 begin -->
					<c:forEach items="${item.months}" var="m" varStatus="ms">
						<li>
							<p class="activity-month js-activity-times">${m.month}月</p>
							<ul class="activity-flow">
								<!-- 循环月份活动 begin -->
								<c:forEach items="${m.activities}" var="activity"
									varStatus="acts">
									<li><i></i>
										<div class="activity-every">
											<div class="activity-list-img">
												<img src="${activity.activity_image1 }" alt="" />
											</div>
											<div class="activity-list-text">
												<h2><a href="${activity.url}" target="_blank">${activity.activity_name }</a></h2>
												<p>
													<fmt:formatDate value="${activity.start_time }"
														pattern="yyyy年MM月dd日 hh:mm " />
												</p>
												<p class="activity-address">${activity.activity_address }</p>
												<div class="end-btn">活动结束</div>
											</div>
										</div></li>
								</c:forEach>
								<!-- 循环月份活动 begin -->

							</ul>
						</li>
					</c:forEach>
					<!-- 月份循环 end -->

				</ul>
			</div>
		</c:forEach>

		<!--年份循环 end-->

	</div>
	<!--中间container结束-->
		<!--footer尾部开始-->
		<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->
	
	<div class="tpy-login-alert js-login-alert hide">
		<!--注册登录弹框-->
		<div class="container tpy-alert-box js-alert-box hide">
			<div class="row">
				<ul class="col-xs-12 tpy-login-title js-login-title clearfix">
					<li class="col-xs-6 on">登录</li>
					<li class="col-xs-6">注册</li>
				</ul>
			</div>
			<div class="clearfix">
				<div class="tpy-login-context clearfix ">
					<div class="row">
						<div class="col-xs-12">
							<input class="login-tel js-tel forbid" type="text"
								placeholder="请输入您的手机号码" />
							<p class="col-xs-11 person-tips hide">请输入正确格式手机号码！</p>
						</div>


					</div>
					<div class="row">
						<div class="col-xs-12">
							<input class="login-password js-password forbid" type="password"
								placeholder="请输入您的密码" />
							<p class="col-xs-11 person-tips hide">请输入6-20位正确密码！</p>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-6">
							<a class="login-forget js-login-forget" href="#">忘记密码？</a>
						</div>
						<div class="col-xs-4 pl-0">
							<input class="login-btn js-login-btn" type="button" value="登录"
								onClick="login()" />
						</div>
					</div>
				</div>
				<div class="tpy-login-context clearfix hide">
					<div class="row">
						<div class="col-xs-12">
							<input class="login-tel js-tel js-yzm-tel forbid" type="text"
								placeholder="请输入您的手机号码" />
							<p class="col-xs-11 person-tips hide">请输入正确格式手机号码！</p>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-12" style="position: relative;">
							<input class="register-yzm" type="text" placeholder="请输入验证码" />
							<a class="register-get js-getyzm" data-on="n" href="#">获取验证码</a>
							<p class="person-yzm-code hide js-person-code">
								验证码短信已经发送到<span>135****6666</span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<input class="login-password js-password forbid" type="password"
								placeholder="请输入您的密码" />
							<p class="col-xs-11 person-tips hide">请输入6-20位数字或者字母！</p>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-6 alignItem register-agree">
							<input id="register-checkbox"
								class="register-checkbox js-register-checkbox" type="checkbox"
								checked="checked" /><label for="register-checkbox"></label>我同意<a
								href="#">《用户注册协议》</a>
						</div>
						<div class="col-xs-4 pl-0">
							<input class="login-btn js-register-btn" type="button" value="注册"
								onclick="register()" />
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="container tpy-alert-box js-alert-mmbox hide">
			<div class="row">
				<ul class="col-xs-12 tpy-login-title js-login-title clearfix">
					<li class="col-xs-12 on" style="border-bottom: 1px solid #EAEAEA;">忘记密码</li>
				</ul>
			</div>
			<div class="clearfix">
				<div class="tpy-login-context clearfix">
					<div class="row">
						<div class="col-xs-12">
							<input class="login-tel js-tel forbid" type="text"
								placeholder="请输入您的手机号码" />
							<p class="col-xs-11 person-tips hide">请输入正确格式手机号码！</p>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-12" style="position: relative;">
							<input class="register-yzm" type="text" placeholder="请输入验证码" />
							<a class="register-get js-getyzm" data-on="n" href="#">获取验证码</a>
							<p class="person-yzm-code hide js-person-code">
								验证码短信已经发送到<span>135****6666</span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<input class="login-password js-password forbid" type="password"
								placeholder="请输入您的密码" />
							<p class="col-xs-11 person-tips hide">请输入6-20位数字或者字母！</p>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-12">
							<input style="width: 455px;" class="login-btn js-reset-btn"
								type="button" value="重置密码" onclick="reset()" />
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script>
		function login() {
			var flag = true;
			var box = $(".js-alert-box").find(".tpy-login-context.on"), tel = box
					.find(".js-tel"), tel_val = tel.val(), secret = box
					.find(".js-password"), secret_val = secret.val();
			if (tel.hasClass("forbid")) {
				flag = false;
				tel.next().removeClass("hide");
				return;
			}
			if (secret.hasClass("forbid")) {
				flag = false;
				secret.next().removeClass("hide");
				return;
			}

			if (flag === true) {
				//放入提交代码
				//					layer.alert("恭喜您登录成功");
				$.ajax({
					type : "post",
					url : "",
					data : {
						"tel" : tel_val,
						"secret" : secret_val
					},
					success : function(data) {
						layer.alert("恭喜您登录成功");

					},
					error : function() {
						layer.alert("登录失败", function() {
							$(".layui-layer-shade,.layui-layer").remove();
						});
					}
				});
			}
		}
		function register() {
			var flag = true;
			var box = $(".js-alert-box").find(".tpy-login-context.on"), tel = box
					.find(".js-tel"), tel_val = tel.val(), secret = box
					.find(".js-password"), secret_val = secret.val();
			if (tel.hasClass("forbid")) {
				flag = false;
				tel.next().removeClass("hide");
				return;
			}
			if (secret.hasClass("forbid")) {
				flag = false;
				secret.next().removeClass("hide");
				return;
			}
			//if (document.getElementById("register-checkbox").checked == false) {
			//	flag = false;
			//	layer.alert("请同意用户注册协议");
			//	return;
			//}
			if (flag === true) {
				//放入提交代码
				//					layer.alert("恭喜您注册成功");
				$.ajax({
					type : "post",
					url : "",
					data : {
						"tel" : tel_val,
						"secret" : secret_val
					},
					success : function(data) {
						layer.alert("恭喜您注册成功");

					},
					error : function() {
						layer.alert("注册失败");
					}
				});
			}
		}
		function reset() {
			var flag = true;
			var box = $(".js-alert-mmbox"), tel = box.find(".js-tel"), tel_val = tel
					.val(), secret = box.find(".js-password"), secret_val = secret
					.val();
			if (tel.hasClass("forbid")) {
				flag = false;
				tel.next().removeClass("hide");
				return;
			}
			if (secret.hasClass("forbid")) {
				flag = false;
				secret.next().removeClass("hide");
				return;
			}

			if (flag === true) {
				//放入提交代码
				//					layer.alert("恭喜您重置密码成功");
				$.ajax({
					type : "post",
					url : "",
					data : {
						"tel" : tel_val,
						"secret" : secret_val
					},
					success : function(data) {
						layer.alert("恭喜您重置密码成功");

					},
					error : function() {
						layer.alert("重置密码失败");
					}
				});
			}
		}
	</script>

	<script data-main="/resource/js/project/main-activity"
		src="/resource/js/system/require.js"></script>
	<script type="text/javascript">
	window.onload = function(){
		setTimeout(function(){
			window.flag = true;
			$(".js-messageBtn").on("click",function(){
				var $this = $(this),
					aid = $this.data("id"),
					btn_top = $this.offset().top,
					btn_left = $this.offset().left,
					btn_width = $this.outerWidth();					
					if(flag){
						flag = false;
						lyxAlert({
							back : function(){//点击确认提交按钮后的函数
								var biao = true,
									alert_box = $(".leave-message-box"),
									name = alert_box.find(".js-name"),
									name_val = name.val(),
									tel = alert_box.find(".js-tel"),
									tel_val = tel.val();
								if(name.hasClass("forbid")){
									biao = false;
									name.next().removeClass("hide");
									return;
								}
								if(tel.hasClass("forbid")){
									biao = false;
									tel.next().removeClass("hide");
									return;
								}
								if(biao === true){
									//姓名电话都正确输入后ajax请求
									$.ajax({
										type :"post",
										url :"/activity/join/"+aid,
										data :{"uname":name_val,"phone":tel_val},
										success :function(data){
												flag = true;
												$(".leave-message-box").remove();
												layer.alert(data.msg);
											
										},
										error : function(){
												flag = true;
												$(".leave-message-box").remove();
												layer.alert("提交失败",function(){
													$(".layui-layer-shade,.layui-layer").remove();
												});
										}
									});
								}
								
							}
						});
						$(".leave-message-box").css({//弹出box位置定位
							"top" : btn_top,
							"left" : btn_left,
							"transform" : "translate(-50%,-110%)",
							"margin-left" : btn_width/2 +"px"
						});	
					}
				
			});
		},200);
	};
		

	</script>
</body>
</html>

