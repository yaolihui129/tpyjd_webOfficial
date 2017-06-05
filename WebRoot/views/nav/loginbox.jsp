<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<script src="resource/js/system/jquery-2.2.0.min.js"></script>
<div class="tpy-login-alert js-login-alert hide"><!--注册登录弹框-->
			<div class="container tpy-alert-box js-alert-box hide">
				<div class="row">
					<ul class="col-xs-12 tpy-login-title js-login-title clearfix">
						<li class="col-xs-6 on">登录</li>
						<li class="col-xs-6">注册</li>
					</ul>
				</div>
				<div class="clearfix">
					<div class="tpy-login-context clearfix on">
						<div class="row">
							<div class="col-xs-12">
								<input class="login-tel js-tel forbid" id="phone" type="text" placeholder="输入11位手机号" autocomplete="off"/>
								<p class="col-xs-11 person-tips hide">请输入正确的手机号</p>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<input class="login-password js-password forbid" id="password" type="password" placeholder="输入登录密码（6-20位）" />
								<p class="col-xs-11 person-tips hide">请输入正确的密码（6-20）</p>
							</div>
							
						</div>
						<div class="row">
							<div class="col-xs-6">
								<a class="login-forget js-login-forget" href="#">忘记密码？</a>
							</div>
							<div class="col-xs-4 pl-0">
								<input class="login-btn js-login-btn" type="button" value="登录" onClick="login()" />
							</div>
						</div>
					</div>
					<div class="tpy-login-context clearfix hide">
						<div class="row">
							<div class="col-xs-12">
								<input class="login-tel js-tel js-yzm-tel forbid" type="text" placeholder="输入11位手机号" autocomplete="off"/>
								<p class="col-xs-11 person-tips hide">请输入正确的手机号</p>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-12" style="position: relative;">
								<input class="register-yzm" type="text" placeholder="输入收到的确认码" />
								<a class="register-get js-getyzm" data-on="n" href="#">获取验证码</a>
								<p class="person-yzm-code hide js-person-code">验证码短信已经发送到<span>135****6666</span></p>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<input class="login-password js-password forbid" type="password" placeholder="设置登录密码（6-20位）" />
								<p class="col-xs-11 person-tips hide">请输入正确的密码（6-20）</p>
							</div>

						</div>
						<div class="row text-center">
							<!-- <div class="col-xs-6 alignItem register-agree">
								<input id="register-checkbox" class="register-checkbox js-register-checkbox" type="checkbox" checked="checked" /><label for="register-checkbox"></label>我同意<a href="#">《用户注册协议》</a>
							</div> -->
							<!-- <div class="col-xs-4 pl-0"> -->
								<input class="login-btn js-register-btn" type="button" value="注册" onclick="register()" />
							<!-- </div> -->
						</div>
					</div>
				</div>
				
			</div>
			<div class="container tpy-alert-box js-alert-mmbox hide">
				<div class="row">
					<ul class="col-xs-12 tpy-login-title js-login-title clearfix" >
						<li class="col-xs-12 on" style="border-bottom: 1px solid #EAEAEA;">忘记密码</li>
					</ul>
				</div>
				<div class="clearfix">
					<div class="tpy-login-context clearfix">
						<div class="row">
							<div class="col-xs-12">
								<input class="login-tel js-tel forbid" type="text" placeholder="输入11位手机号码" autocomplete="off"/>
								<p class="col-xs-11 person-tips hide">请输入正确的手机号</p>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-12" style="position: relative;">
								<input class="register-yzm" type="text" placeholder="输入收到的确认码" />
								<a class="register-get js-getyzm" data-on="n" href="#">获取验证码</a>
								<p class="person-yzm-code hide js-person-code">验证码短信已经发送到<span>135****6666</span></p>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<input class="login-password js-password forbid" type="password" placeholder="设置登录密码（6-20位）" />
								<p class="col-xs-11 person-tips hide">请输入正确的密码（6-20）</p>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-12">
								<input style="width: 455px;" class="login-btn js-reset-btn" type="button" value="重置密码" onclick="reset()" />
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<script>
		var reclick = true;
		
			function login(){
				//debugger
				var flag = true;
				var box = $(".js-alert-box").find(".tpy-login-context.on"),
					tel = box.find(".js-tel"),
					secret = box.find(".js-password");
				if(tel.hasClass("forbid")){
					flag = false;
					tel.next().removeClass("hide");
					return;
				}
				if(secret.hasClass("forbid")){
					flag = false;
					secret.next().removeClass("hide");
					return;
				}
				tel_val = tel.val(),
				secret_val = secret.val();
				if(flag === true){
					//放入提交代码
					if(reclick){//防止重复提交
						reclick = false;
						$.post(
								"/register/login",
								{
									"phone":tel_val,
									"password":secret_val	
								},
								function(data){
									if(data.status == 0){
										reclick = true;
										if(data.custinfo.head_img != null && data.custinfo.head_img != undefined && data.custinfo.head_img != ''){
											$(".tpy-login-success").html("<div class='tpy-login alignItem'><div class='tpy-login-img'><img src='"+data.custinfo.head_img+"' alt='' /></div><div class='tpy-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
											$(".tpy-login-success-index").html("<div class='tpy-index-login alignItem'><div class='tpy-index-login-img'><img src='"+data.custinfo.head_img+"' alt='' /></div><div class='tpy-index-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-index-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
										}else{
											$(".tpy-login-success").html("<div class='tpy-login alignItem'><div class='tpy-login-img'><img src='/resource/images/person-head.jpg' alt='' /></div><div class='tpy-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
											$(".tpy-login-success-index").html("<div class='tpy-index-login alignItem'><div class='tpy-index-login-img'><img src='/resource/images/person-head.jpg' alt='' /></div><div class='tpy-index-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-index-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
										}
										 var login = $(".tpy-index-header-status .tpy-index-login"),
							                login_box = login.next(".tpy-index-login-box"),
							                login_width = login.outerWidth();
							            login_box.outerWidth(login_width + 2);
							            lyxFn.init();
							            $(".js-login-alert,.js-alert-box").addClass("hide");
							            $(".js-alert-mmbox").removeClass("hide");
										layer.alert("恭喜您登录成功");
										//window.location.reload();
									}else{
										reclick = true;
										layer.alert(data.msg);
									}
								}	
						)
					}
				}
			}
			
			function register(){
				
				var flag = true;
				var box = $(".js-alert-box").find(".tpy-login-context.on"),
					tel = box.find(".js-tel"),
					secret = box.find(".js-password"),
					yzm = box.find(".register-yzm");
				if(tel.hasClass("forbid")){
					flag = false;
					tel.next().removeClass("hide");
					return;
				}
				if(secret.hasClass("forbid")){
					flag = false;
					secret.next().removeClass("hide");
					return;
				}
				yzm_val = yzm.val();
				if(!yzm_val){
					flag = false;
					layer.alert("验证码不能为空");
					return;
				}
				//if (document.getElementById("register-checkbox").checked == false) {
                //	flag = false;
                //	layer.alert("请同意用户注册协议");
                //	return;
            	//} 
				tel_val = tel.val(),
				secret_val = secret.val();
				
				if(flag === true){
					//放入提交代码
					if(reclick){//防止重复提交
						reclick = false;
						$.post(
								"/register",
								{
									"phone":tel_val,
									"password":secret_val,
									"security":yzm_val
								},
								function(data){
									if(data.status == 0){
										reclick = true;
										if(data.custinfo.head_img != null && data.custinfo.head_img != undefined && data.custinfo.head_img != ''){
											$(".tpy-login-success").html("<div class='tpy-login alignItem'><div class='tpy-login-img'><img src='"+data.custinfo.head_img+"' alt='' /></div><div class='tpy-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
											$(".tpy-login-success-index").html("<div class='tpy-index-login alignItem'><div class='tpy-index-login-img'><img src='"+data.custinfo.head_img+"' alt='' /></div><div class='tpy-index-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-index-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
										}else{
											$(".tpy-login-success").html("<div class='tpy-login alignItem'><div class='tpy-login-img'><img src='/resource/images/person-head.jpg' alt='' /></div><div class='tpy-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
											$(".tpy-login-success-index").html("<div class='tpy-index-login alignItem'><div class='tpy-index-login-img'><img src='/resource/images/person-head.jpg' alt='' /></div><div class='tpy-index-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-index-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
										}
										 var login = $(".tpy-index-header-status .tpy-index-login"),
							                login_box = login.next(".tpy-index-login-box"),
							                login_width = login.outerWidth();
							            login_box.outerWidth(login_width + 2);
							            lyxFn.init();
							            $(".js-login-alert,.js-alert-box").addClass("hide");
							            $(".js-alert-mmbox").removeClass("hide");
										layer.alert("恭喜您注册成功");
										
									}else{
										reclick = true;
										layer.alert(data.msg);
									}
								}	
						)
					}
				}
			}
			function reset(){
//				debugger
				var flag = true;
				var box = $(".js-alert-mmbox"),
					tel = box.find(".js-tel"),
					secret = box.find(".js-password"),
					yzm = box.find(".register-yzm");
				if(tel.hasClass("forbid")){
					flag = false;
					tel.next().removeClass("hide");
					return;
				}
				if(secret.hasClass("forbid")){
					flag = false;
					secret.next().removeClass("hide");
					return;
				}
				yzm_val = yzm.val();
				if(!yzm_val){
					flag = false;
					layer.alert("验证码不能为空");
					return;
				}
				tel_val = tel.val(),
				secret_val = secret.val();
				if(flag === true){
					//放入提交代码
					if(reclick){//防止重复提交
						reclick = false;
						$.post(
								"/register/reset",
								{
									"phone":tel_val,
									"password":secret_val,
									"security":yzm_val
								},
								function(data){
									if(data.status == 0){
										reclick = true;
										if(data.custinfo.head_img != null && data.custinfo.head_img != undefined && data.custinfo.head_img != ''){
											$(".tpy-login-success").html("<div class='tpy-login alignItem'><div class='tpy-login-img'><img src='"+data.custinfo.head_img+"' alt='' /></div><div class='tpy-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
											$(".tpy-login-success-index").html("<div class='tpy-index-login alignItem'><div class='tpy-index-login-img'><img src='"+data.custinfo.head_img+"' alt='' /></div><div class='tpy-index-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-index-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
										}else{
											$(".tpy-login-success").html("<div class='tpy-login alignItem'><div class='tpy-login-img'><img src='/resource/images/person-head.jpg' alt='' /></div><div class='tpy-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
											$(".tpy-login-success-index").html("<div class='tpy-index-login alignItem'><div class='tpy-index-login-img'><img src='/resource/images/person-head.jpg' alt='' /></div><div class='tpy-index-login-name'>"+data.custinfo.phone_num+"</div></div><div class='tpy-index-login-box hide'><p><a href='/logout'>退出登录</a></p></div>");
										}
										 var login = $(".tpy-index-header-status .tpy-index-login"),
							                login_box = login.next(".tpy-index-login-box"),
							                login_width = login.outerWidth();
							            login_box.outerWidth(login_width + 2);
							            lyxFn.init();
							            $(".js-login-alert,.js-alert-box").addClass("hide");
							            $(".js-alert-mmbox").removeClass("hide");
							            layer.alert("恭喜您重置密码成功");
									}else{
										reclick = true;
										layer.alert(data.msg);
									}
								}	
						)
					}
				}
			}
		</script>