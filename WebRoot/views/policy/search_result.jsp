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
<title>太平洋出国|移民新政</title>



<!--以下系统css-->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<!--以下页面css-->
<link rel="stylesheet" href="/resource/css/news.css" type="text/css" />
</head>
<body>
	<div class="tpy-header">
		<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
		<!--header头部结束-->
	</div>
<%@ include  file="/views/nav/loginbox.jsp"%>
	<div class="news-breadline">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					当前位置：<a href="#">首页</a><span>></span><a href="/policy">移民新政</a><span>></span>搜索结果<i
						class="text-orange">${fn:length(list)} </i>条
				</div>
			</div>
		</div>
	</div>
	<!--中间container开始-->
	<div class="container tpy-container">
		<form id="searchForm" method="post">
			<!--搜索begin-->
			<div class="news-search">
				<p>
					<input class="news-search-input" type="text" id="search_key"
						name="search_key" value="${keys }" placeholder="搜索你感兴趣的新政、问题、标签" />
					<input class="news-search-button" id="btnSearch" type="button"
						value="搜索" />
				</p>
				<p class="clearfix news-hot-sign" style="display: none;">
					<span>热门标签</span> <a href="#">美国</a> <a href="#">加拿大</a> <a
						href="#">新西兰</a> <a href="#">澳大利亚</a> <a href="#">投资移民</a> <a
						href="#">留学移民</a> <a href="#">爱尔兰</a> <a href="#">EB-5</a>
				</p>
			</div>
			<!--搜索end-->
			<c:if test="${empty  list}">
				很抱歉，您搜索的关键字暂无搜索结果
			</c:if>
			<c:if test="${not empty  list}">
				<!--政策列表begin-->
				<ul class="country-new-list container" id="search_result">
					<c:forEach items="${list}" var="item" varStatus="status">
						<li>
							<div class="country-timestamp">
								<p class="country-timestamp-day">
									<fmt:formatDate value="${item.publish_time }" pattern="dd " />
								</p>
								<p class="country-timestamp-date">
									<fmt:formatDate value="${item.publish_time }"
										pattern="yyyy.MM " />
								</p>
							</div>
							<div class="country-list-text">
								<h2>
									<a href="/policy/detail/${item.info_id }"> ${item.title} </a>
								</h2>
								<p>${item.summary }</p>
							</div>
						</li>
					</c:forEach>
				</ul>

				<!-- 分页 pageBar start -->
				<input type="hidden" id="page" name="page" value="1" /> ${pageBar}
				<!-- 分页 pageBar end -->
			</c:if>
		</form>
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

	<script src="/resource/js/system/jquery-2.2.0.min.js"
		type="text/javascript"></script>
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
			/* if (document.getElementById("register-checkbox").checked == false) {
				flag = false;
				layer.alert("请同意用户注册协议");
				return;
			} */
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

		//绑定翻页
		$('.country-new-page a').each(function(index, element) {
			if ($(element).attr("data-page") != undefined) {
				$(this).click(function(e) {
					$("#page").val($(element).attr("data-page"));
					doRequest();
				});
			}
		});

		//提交查询请求
		function doRequest() {
			$("#searchForm").submit();
		}

		window.onload = function() {
			setKeysColor();
		}
		//给搜索关键词设置颜色
		function setKeysColor() {
			var regx = /${keys}/g;
			document.getElementById("search_result").innerHTML = document
					.getElementById("search_result").innerHTML.replace(regx,
					'<i class="text-orange">' + "${keys}" + '</i>');
		}
		
		//触发搜索
		$("#btnSearch").click(
				function(e) {
					doRequest();
				});
	</script>

	<script data-main="/resource/js/project/main"
		src="/resource/js/system/require.js"></script>
</body>
</html>