<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>『太平洋出国』移民中介_移民机构-移民、护照、投资移民、创业移民、技术移民、购房移民、移民资金安全、移民成功率高、国际顶级律师驻场，太平洋出国移民官网</title>
<meta name="Keywords" content="移民,投资移民,创业移民,技术移民,护照,澳大利亚移民,新西兰移民,加拿大移民,美国移民,移民资金安全,移民成功率高,国际顶级律师驻场,太平洋加达移民,太平洋出国移民"/>
<meta name="Description" content="太平洋出国集团是投资移民领导品牌,致力打造最好的投资移民公司,20年专业办理美国移民、加拿大移民、欧洲移民等投资移民、澳大利亚移民、新西兰移民，移民成功率高达99%，市场占有率超过80%" />
<meta name="author" content="太平洋出国" />
<meta name="copyright" content="太平洋出国" />

<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resource/css/jquery.fullPage.css">
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
<link rel="stylesheet" href="/resource/css/jquery.bxslider.css">
<link rel="stylesheet" href="/resource/css/index.css">
<link rel="stylesheet" href="/resource/css/index2.css">
<style>
#section-footer {
	width: 100%;
	height: auto;
}
</style>
</head>

<body style="display: relative;">
	<ul id="menu">
		<li data-menuanchor="page1" class="active pot"><a href="#page1"></a>
		</li>
		<li data-menuanchor="page2" class="active pot"><a href="#page2"></a>
		</li>
		<li data-menuanchor="page3" class="active pot"><a href="#page3"></a>
		</li>
		<li data-menuanchor="page4" class="active pot"><a href="#page4"></a>
		</li>
		<li data-menuanchor="page5" class="active pot"><a href="#page5"></a>
		</li>
		<li data-menuanchor="page6" class="active pot"><a href="#page6"></a>
		</li>
	</ul>

	<!--导航条登陆状态开始-->
	<div class="tpy-header-bottom tpy-header-bar">
		<div class="container tpy-bottom-container person-business">
			<div class="row">
				<div class="col-xs-8 index-header-nav">
					<%@ include  file="/views/nav/menu.jsp"%>
				</div>
				<%-- <div class="col-xs-3  tpy-header-status">
                     <c:if test="${not empty custinfo}">
					<div class="tpy-login alignItem" style="overflow:hidden;">
						<div class="tpy-login-img"><img src="<c:if test="${empty custinfo.headImg}">../resource/images/person-head.jpg</c:if><c:if test="${not empty custinfo.headImg}">${custinfo.headImg}</c:if>" alt="" /></div>
						<div class="tpy-login-name" style="-webkit-flex:1">${custinfo.phoneNum}</div>
					</div>
					<div class="tpy-login-box hide">
						<p><a href="/logout">退出登录</a></p>
					</div>
				</c:if>	
				<c:if test="${empty custinfo}">
					<ul class="js-log-btn">
								<li><a class="js-login" href="#">登录</a></li>
								<li class="tpy-register"><a class="js-register" href="#">注册</a></li>
					</ul>
				</c:if>
                </div> --%>
                <div class="col-xs-4 tpy-index-header-status tpy-login-success-index">
                 <c:if test="${not empty custinfo}">
                    <div class="tpy-index-login alignItem">
                        <div class="tpy-index-login-img"><img src="<c:if test="${empty custinfo.headImg}">/resource/images/person-head.jpg</c:if><c:if test="${not empty custinfo.headImg}">${custinfo.headImg}</c:if>" alt="" /></div>
                        <div class="tpy-index-login-name">${custinfo.phoneNum}</div>
                    </div>
                    <div class="tpy-index-login-box hide">
                        <!--<p><a href="#">我的业务</a></p>
								<p><a href="#">我的活动</a></p>-->
                        <p><a href="#">退出登录</a></p>

                    </div>
                    </c:if>
                    <c:if test="${empty custinfo}">
					<ul class="js-log-btn">
								<li><a class="js-login" href="#">登录</a></li>
								<li class="tpy-register"><a class="js-register" href="#">注册</a></li>
					</ul>
				</c:if>
                </div>
			</div>
		</div>
	</div>
	<!--导航条登陆状态结束-->
	<!--导航条未登陆状态开始-->
<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--导航条未登陆状态结束-->
	<div id="fullpage">
		<div class="section fp-auto-height header">

			<!--header头部未登陆状态-->
				<!--header头部开始-->
					<%@ include  file="/views/nav/header.jsp"%>
				<!--header头部结束-->
			<!--header头部未登陆状态结束-->
		</div>
		<div class="section">
			<div class="index index-1">
				<div class="banner">
					<div class="bannerContent">
						<c:choose>
							<c:when test="${bannerImgs != null and !empty bannerImgs}">
								<script>var officialFrequency = ${topicSetting.officialFrequency}</script>	
								<ul class="banner-slider">
									<c:choose>
										<c:when test="${topicSetting != null}">
											
											<c:set var="tpiend" value="${fn:length(bannerImgs) > topicSetting.officialNumber?topicSetting.officialNumber-1:fn:length(bannerImgs)}" />
											<c:forEach var="img" items="${bannerImgs}" begin="0"  end="${tpiend}"  >
									  			<li class="imgContainer" style="background:url(${img.topicImage})  no-repeat center center"><a href="${img.topicUrl}" ></a></li>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="img" items="${bannerImgs}">
									  			<li class="imgContainer" style="background:url(${img.topicImage})  no-repeat center center"><a href="${img.topicUrl}" ></a></li>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</ul>
							</c:when>
							<c:otherwise>
								<ul class="banner-slider">
									<li class="imgContainer" style="background:url(/resource/images/index1-banner1_01.jpg})  no-repeat center center"></li>
									<li class="imgContainer" style="background:url(/resource/images/index1-banner1_02.jpg})  no-repeat center center"></li>
								</ul>
							</c:otherwise>
						</c:choose>
						
					</div>
					<div class="index-search">
						<div class="search">
							<select class="search-select purpose" id="intentCountry"
								name="intentCountry">
								<option value="">意向国家</option>
								<c:forEach items="${intentCountry}" var="item"
									varStatus="status">
									<option value="${item.estimate_select_id }">${item.content }</option>
								</c:forEach>
							</select> <select class="search-select houseArea" id="immigPurpose"
								name="immigPurpose">
								<option value="">移民目的</option>
								<c:forEach items="${immigPurpose}" var="item" varStatus="status">
									<option value="${item.estimate_select_id }">${item.content }</option>
								</c:forEach>
							</select>
							<button class="btn" id="btnEvaluate">
								<span>移民评估</span> <span class="select-small">已有${estimateAccount }人评估</span>
							</button>

						</div>
					</div>
				</div>
				<div class="index1-main">
					<div class="top-line">
						<div class="hot-slider">
							<div class="hot-content">
								<c:forEach items="${successCases}" var="item" varStatus="status">
									<a href="/about-us/success-cases/detail/${item.info_id }" target="_blank"><span>祝贺：${item.title }</span></a>
								</c:forEach>
							</div>

						</div>
					</div>
					<div class="index-icon">
						<div class="icones">
							<dl>
								<dt></dt>
								<dd>首批获公安部资格认证</dd>
							</dl>
							<dl>
								<dt class="pinpai"></dt>
								<dd>商业投资移民领导品牌</dd>
							</dl>
							<dl>
								<dt class="jigou"></dt>
								<dd>最受公众信赖移民机构</dd>
							</dl>
							<dl>
								<dt class="fuwu"></dt>
								<dd>20年专注移民服务，更权威！</dd>
							</dl>
							<dl>
								<dt class="anli"></dt>
								<dd>10000+成功案例，更放心！</dd>
							</dl>
							<dl>
								<dt class="baozhang"></dt>
								<dd>99.3%成功率，更有保障！</dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="index index-2">
				<div class="index2-left">
					<div class="index2-title">
						<p>政策动态</p>
						<span class="index2-title1"></span> <span class="index2-title2"></span>
					</div>
				</div>
				<div class="index2-right">
					<img data-src="/resource/images/index2-bg.jpg">
				</div>
				<div class="index2-main">
					<div class="index2-main-left">
						<div class="Recent-top">
							<h2 class="Recent-title">
								<span class="index2-title-icon"></span>
								<p>${titles[0]}</p>
								<span class="english">Recent activity</span> <a href="/activity">了解更多
									>></a>
							</h2>
							<c:forEach items="${latestActivities}" var="item"
								varStatus="status">
								<c:if test="${status.index == 0 }">
									<div class="Recent-content">
										<dl>
											<dt>
												<img data-src="${item.activity_image1 }">
											</dt>
											<dd>
												<h4><a href="${item.url}" target="_blank">${item.activity_name}</a></h4>
												<p class="Recent-time">
													<fmt:formatDate value="${item.start_time }"
														pattern="yyyy年MM月dd日 HH时mm分" />
												</p>
												<p class="Recent-adress">${item.activity_address}</p>
												<c:if test="${item.join_enable == '1' }">
													<button class="js-messageBtn" data-id="${item.activity_id }">+我要加入</button>
												</c:if>
												<c:if test="${item.join_enable == '0' }">
													<button class="Recent-over">活动已结束</button>
												</c:if>
											</dd>
										</dl>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="Recent-bottom">
							<c:forEach items="${latestActivities}" var="item"
								varStatus="status">
								<c:if test="${status.index != 0 }">
									<div class="Recent-list">
										<h4><a href="${item.url}" target="_blank">${item.activity_name}</a></h4>
										<p class="Recent-time">
											<fmt:formatDate value="${item.start_time }"
												pattern="yyyy年MM月dd日 HH时mm分" />
										</p>
										<p class="Recent-adress">${item.activity_address}</p>
										<c:if test="${item.join_enable == '1' }">
											<button class="js-messageBtn" data-id="${item.activity_id }">+我要加入</button>
										</c:if>
										<c:if test="${item.join_enable == '0' }">
											<button class="Recent-over">活动已结束</button>
										</c:if>
									</div>
								</c:if>

							</c:forEach>
						</div>
					</div>
					<div class="index2-main-right">
						<div class="Policies-top">
							<h2 class="Recent-title">
								<span class="index2-title-icon index2-title-icon2"></span>
								<p>${titles[1]}</p>
								<span class="english">New Policies</span> <a href="/policy">了解更多
									>></a>
							</h2>
							<div class="Policies-content">
								<c:forEach items="${latestPolicy}" var="item" varStatus="status">
									<c:if test="${status.index == 0 }">
										<a href="/policy/detail/${item.info_id }">
											<div class="Policies-list Policies-list1">
												<dl>
													<dt class="country-timestamp">
														<p class="country-timestamp-day">
															<fmt:formatDate value="${item.publish_time }"
																pattern="dd " />
														</p>
														<p class="country-timestamp-date">
															<fmt:formatDate value="${item.publish_time }"
																pattern="yyyy.MM " />
														</p>
													</dt>
													<dd>${item.title }</dd>
												</dl>
												<p class="Policies-list-content">${item.summary }</p>
											</div>
										</a>
									</c:if>
									<c:if test="${status.index != 0 }">
										<a href="/policy/detail/${item.info_id }">
											<div class="Policies-list">
												<dl>
													<dt class="country-timestamp">
														<p class="country-timestamp-day">
															<fmt:formatDate value="${item.publish_time }"
																pattern="dd " />
														</p>
														<p class="country-timestamp-date">
															<fmt:formatDate value="${item.publish_time }"
																pattern="yyyy.MM " />
														</p>
													</dt>
													<dd>
														<p class="Policies-list-title">${item.title }</p>
														<p class="Policies-list-main">${item.summary }</p>
													</dd>
												</dl>
											</div>
										</a>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<%-- <div class="Policies-bottom">
							<h2 class="Recent-title">
								<span class="index2-title-icon index2-title-icon3"></span>
								<p>${titles[2]}</p>
								<span class="english">Latest live</span> <a href="#">了解更多 >></a>
							</h2>
							<div class="live-content">
								<c:forEach items="${latestLive}" var="item" varStatus="status">
									<div
										<c:if test="${status.index == 0 }">class="live-left"</c:if>
										<c:if test="${status.index != 0 }"> class="live-right"</c:if>>
										<dl>
											<dt>
												<img data-src="${item.livePoster}">
												<c:if test="${item.liveStatus == '直播中' }">
													<span class="live-zhibo"><img
														data-src="/resource/images/index2-zhibo_08.png"></span>
												</c:if>

											</dt>
											<dd>
												<p class="live-masagge">德国内政部重点推荐</p>
												<p class="live-time">时间：2017年3月3日 9:00</p>
												<p class="live-adress">主讲人：Lily</p>
											</dd>
										</dl>
									</div>
								</c:forEach>
							</div>
						</div> --%>
					</div>
				</div>
				<div class="fuhao1">
					<img src="resource/images/index2-bg2.png">
				</div>
			</div>
		</div>
		<div class="section">
			<div class="index index-3">
				<div class="index3">
					<div class="index3-left">
						<div class="index3-img1">
							<a href="/country/survey/${preferredCountry1.country_id}">
							<div class="index3-pic index3-pic1">
								<img data-src="${preferredCountry1.preferred_pic }"> <span>${preferredCountry1.country_name }</span>
								<div class="index3-pic-mark">
									<h4>${preferredCountry1.country_name }</h4>
									<%-- <p class="biaoqian">
										<c:forEach items="${preferredCountry1.project_type}"
											var="item" varStatus="status">
											<em>${item.name }</em>
										</c:forEach>
									</p> --%>
									<p class="tedian">${preferredCountry1.country_brief }</p>
								</div>
							</div>
							</a>
						</div>
						<div class="index3-img2">
							<a href="/country/survey/${preferredCountry2.country_id}">
							<div class="index3-pic index3-pic2">
								<img data-src="${preferredCountry2.preferred_pic }"> <span>${preferredCountry2.country_name }</span>
								<div class="index3-pic-mark">
									<h4>${preferredCountry2.country_name }</h4>
									<%-- <p class="biaoqian">
										<c:forEach items="${preferredCountry2.project_type}"
											var="item" varStatus="status">
											<em>${item.name }</em>
										</c:forEach>
									</p> --%>
									<p class="tedian">${preferredCountry2.country_brief }</p>
								</div>
							</div>
							</a>
						</div>
					</div>
					<div class="index3-center">
						<div class="index3-title">
							<div class="Preferably-main">
								<p class="Preferably-title">优选国家</p>
								<p class="Preferably-english">
									<span>Preferably</span> Country
								</p>
							</div>
						</div>
						<div class="index3-img3">
							<a href="/country/survey/${preferredCountry3.country_id}">
							<div class="index3-pic index3-pic3">
								<img data-src="${preferredCountry3.preferred_pic }"> <span>${preferredCountry3.country_name }</span>
								<div class="index3-pic-mark">
									<h4>${preferredCountry3.country_name }</h4>
									<%-- <p class="biaoqian">
										<c:forEach items="${preferredCountry3.project_type}"
											var="item" varStatus="status">
											<em>${item.name }</em>
										</c:forEach>
									</p> --%>
									<p class="tedian">${preferredCountry3.country_brief }</p>
								</div>
							</div>
							</a>
						</div>
					</div>
					<div class="index3-right">
						<div class="index3-img4">
							<a href="/country/survey/${preferredCountry4.country_id}">
							<div class="index3-pic index3-pic4">
								<img data-src="${preferredCountry4.preferred_pic }"> <span>${preferredCountry4.country_name }</span>
								<div class="index3-pic-mark">
									<h4>${preferredCountry4.country_name }</h4>
									<%-- <p class="biaoqian">
										<c:forEach items="${preferredCountry4.project_type}"
											var="item" varStatus="status">
											<em>${item.name }</em>
										</c:forEach>
									</p> --%>
									<p class="tedian">${preferredCountry4.country_brief }</p>
								</div>
							</div>
							</a>
						</div>
					</div>
				</div>
				<div class="fuhao2">
					<img data-src="/resource/images/index3-bg.png">
				</div>
			</div>
		</div>
		<div class="section">
			<div class="index index-4">
				<div class="Hot-Projects">
					<div class="index4-title">
						<p>热门项目</p>
						<span class="hot-font1">Hot</span> <span class="hot-font2">Projects</span>
					</div>
				</div>
				<div class="index4-main">
					<c:forEach items="${hotProjects}" var="item" varStatus="status">
						<a href="/project/info/${item.project_id }">
						<div class="index4-img">
							<img data-src="${item.preferred_pic }">
							<div class="index4-img-bottom">${item.project_name }</div>
							<div class="index4-img-mark">
								<h4>项目优势</h4>
								<ul>
									<c:forEach items="${item.projs}" var="p" varStatus="status">
										<li>${p }</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						</a>
					</c:forEach>

				</div>
				<div class="fuhao3">
					<img data-src="resource/images/index4-bg2.png">
				</div>
			</div>
		</div>
		<div class="section">
			<div class="index index-5">
				<div class="index5-title">
					<p class="index5-font1">公司大事件</p>
					<p class="index5-font2">
						<span>Company</span> major events
					</p>
				</div>
				
				<div class="Company-main">
					<div class="Company-major">
						<c:forEach items="${bigEvent}" var="item" varStatus="status">
						<div class="Company-content">
							<div class="Company-left">
								<div class="index5-img index5-img1">
									<img data-src="${item.img1.picture_url }">
									<p class="index5-pic-explain">${item.img1.describe }</p>
								</div>
								<div class="index5-img index5-img2">
									<img data-src="${item.img2.picture_url }">
									<p class="index5-pic-explain">${item.img2.describe }</p>
								</div>
							</div>
							<div class="Company-center">
								<div class="index5-img index5-img3">
									<img data-src="${item.img3.picture_url }">
									<p class="index5-pic-explain">${item.img3.describe }</p>
								</div>
								<div class="index5-img index5-img4">
									<img data-src="${item.img4.picture_url }">
									<p class="index5-pic-explain">${item.img4.describe }</p>
								</div>
							</div>
							<div class="Company-right">
								<div class="index5-img index5-img5">
									<img data-src="${item.img5.picture_url }">
									<p class="index5-pic-explain">${item.img5.describe }</p>
								</div>
								<div class="index5-img index5-img6">
									<img data-src="${item.img6.picture_url }">
									<p class="index5-pic-explain">${item.img6.describe }</p>
								</div>
								<div class="index5-img index5-img7">
									<img data-src="${item.img7.picture_url }">
									<p class="index5-pic-explain">${item.img7.describe }</p>
								</div>
							</div>
						</div>
						</c:forEach>

					</div>

				</div>
				<div class="fuhao4">
					<img data-src="/resource/images/index5-bg2.png">
				</div>
				
			</div>
		</div>
		<div class="section">
			<div class="index index-6">
				<div class="index6-title">
					<p class="index6-title-font">专业团队</p>
					<p class="index6-title-english">
						<span>Professional</span> Team
					</p>
				</div>
				<div class="index6-main">

					<c:forEach items="${professionalTeam}" var="item"
						varStatus="status">
						<div class="index6-content">
							<div class="index6-content-title">
								<p>${item.name }<span> ${item.short_name }</span>
								</p>
							</div>
							<div class="Consultant-content">
								<c:forEach items="${item.teamList}" var="team"
									varStatus="status">
									<a href="/about-us/professional-team/expert/${team.team_id }">
									<div class="Consultant-img">
										<img src="${team.head_img }">
										<div class="Consultant-text">
											<p class="Consultant-text-name">${team.english_name }</p>
											<p class="Consultant-text-font">${team.job_title }</p>
										</div>
										<div class="Consultant-pic-mark">
											<p class="Consultant-mark-name">${team.english_name }</p>
											<p>${team.job_title }</p>
											<p class="Consultant-mark-font">${team.introduce }</p>
										</div>
									</div>
									</a>
								</c:forEach>
							</div>
						</div>
					</c:forEach>

				</div>
				<div class="fuhao5">
					<img src="/resource/images/index6-bg2.png">
				</div>
			</div>
		</div>
		<div class="section fp-auto-height footer">
			<div class="foot">
				<!--footer尾部开始-->
				<%@ include  file="/views/nav/footer.jsp"%>
				<!--footer尾部结束-->
				<%--  <%@include file="/views/nav/loginbox.jsp" %> --%>
			</div>
			<!---->
		</div>
	</div>
	<script src="resource/js/system/jquery-2.2.0.min.js"></script>
	<script src="resource/js/system/layer.js"></script>
	<script src="resource/js/modules/yzm.js"></script>
	<script src="resource/js/modules/common.js"></script>
	<script src="resource/js/modules/formValidate.js"></script>
	<!--<script type="text/javascript" src="/resource/js/modules/fullPage.js"></script>-->
	<script src="resource/js/modules/jquery.fullPage.js"></script>
	<script src="resource/js/modules/jquery.bxslider.js"></script>
	<script src="resource/js/modules/index.js"></script>
	<script src="resource/js/modules/lyxAlert.js"></script>
	<script type="text/javascript">
        /*全屏插件使用*/
        $('#fullpage').fullpage({
            anchors: ['header', 'page1', 'page2', 'page3', 'page4', 'page5', 'page6', 'footer'],
            menu: '#menu',
            css3: true,
            /* afterLoad: function (index, anchorLink) {
                 if (index == 6) {
                     $("#menu").css("display", "none");
                 }
             },*/
            afterRender: function () {
                /*$('.banner-slider').bxSlider({
                    auto: true,
                    minSlides: 1,
                    maxSlides: 1,
                    slideMargin: 10
                });*/

            },
            onLeave: function (index, direction) {
                if (index == '1') {
                    $(".tpy-header-bar").fadeIn(2000);
                    $("#menu").fadeIn(700);
                    (function () { //账号登录后，下拉列表的宽度=登录后账号的宽度
                        var login2 = $(".tpy-index-login"),
                            login_box2 = login2.next(".tpy-index-login-box"),
                            login_width2 = login2.outerWidth();
                        console.log(login_width2);
                        login_box2.outerWidth(login_width2 + 2);
                    }());
                };
                if (index == '2' && direction == '1') {
                    $(".tpy-header-bar").css("display", "none");
                    $("#menu").fadeOut(700);


                };
                if (index == '3' && direction == '2') {
                    flag = true;
                    $(".leave-message-box").remove();
                    //location.reload(); //当前页面 
                }
                if (index == '3' && direction == '4') {
                    flag = true;
                    $(".leave-message-box").remove();
                }
                if (index == '8') {
                    $("#menu").fadeIn(700);

                };
                if (index == '7' && direction == '8') {
                    $("#menu").fadeOut(700);
                }
            }

        });
        /*banner图轮播*/
        $(function () {
            /*$('#marquee').bxSlider({
                mode: 'vertical', //默认的是水平 
                displaySlideQty: 1, //显示li的个数 
                moveSlideQty: 1, //移动li的个数  
                captions: true, //自动控制 
                auto: true,
                controls: false, //隐藏左右按钮 
                pause: 4000, //停留时间
                speed: 500, //切换时间
                slideMargin: 10, //每个项目之间的间距
                slideWidth: 设置多图滚动时的单个项目宽度；
                minSlides： 多图滚动时一次滚动的最少个数；
            });*/
            $('.banner-slider').bxSlider({
                auto: true,
                minSlides: 1,
                maxSlides: 1,
                slideMargin: 10,
                infiniteLoop: true
            });
            $('.hot-content').bxSlider({
                mode: 'vertical',
                auto: true,
                //autoControls: true,
                speed: 1000,
                minSlides: 1,
                maxSlides: 1,
                slideMargin: 10
            });
            $('.Company-major').bxSlider({
                //autoControls: true,
                speed: 1000,
                pause: 4000,
                minSlides: 1,
                maxSlides: 1,
                slideMargin: 5,
                pager: false,
                controls: true,
                infiniteLoop: false
            });
        });
    </script>
    <script type="text/javascript">
        window.onload = function () {
            setTimeout(function () {
                window.flag = true;
                $(".js-messageBtn").on("click", function () {
                    var $this = $(this),
                    	aid = $this.data("id"),//点击我要加入获取活动id
                        btn_top = $this.offset().top,
                        btn_left = $this.offset().left,
                        btn_width = $this.outerWidth();
                    if (flag) {
                        flag = false;
                        lyxAlert({
                            back: function () { //点击确认提交按钮后的函数
                                var biao = true,
                                    alert_box = $(".leave-message-box"),
                                    name = alert_box.find(".js-name"),
                                    name_val = name.val(),
                                    tel = alert_box.find(".js-tel"),
                                    tel_val = tel.val();
                                if (name.hasClass("forbid")) {
                                    biao = false;
                                    name.next().removeClass("hide");
                                    return;
                                }
                                if (tel.hasClass("forbid")) {
                                    biao = false;
                                    tel.next().removeClass("hide");
                                    return;
                                }
                                if (biao === true) {
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
                        $(".leave-message-box").css({ //弹出box位置定位
                            "top": btn_top,
                            "left": btn_left,
                            "transform": "translate(-50%,-110%)",
                            "margin-left": btn_width / 2 + "px"
                        });
                    }

                });
            }, 200);
        };
    </script>
	<script type="text/javascript">
	//跳转移民评估
	$("#btnEvaluate").click(
			function(e) {
				var rootUrl = "http://" + window.location.host;
				window.open(rootUrl + "/estimate/" + $("#intentCountry").val() + "-" + $("#immigPurpose").val());
			})
	</script>
</body>

</html>