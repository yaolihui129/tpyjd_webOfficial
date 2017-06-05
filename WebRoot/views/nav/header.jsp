<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
<div class="tpy-header">
		<div class="tpy-header-top">
			<div class="container tpy-top-container">
				<div class="row">
					<div class="col-xs-2 tpy-header-company">
						<span><a href="/about-us/contact-us/bj">北京</a></span><span class="tpy-company-branch">分公司</span>
						<ul class="tpy-company-ul hide">
							
							<c:if test="${branch != null}">
								<c:forEach var="item" items="${branch}">
									<li><a href="${item.url}">${item.title}</a></li>
								</c:forEach>
							</c:if>
							
						</ul>
					</div>
					<div class="col-xs-7 tpy-header-contact">
						<span class="tpy-contact-chat">微信关注</span><!-- <span class="tpy-contact-tel">直接联系</span> -->
						<div class="tpy-chat-box hide">
							<c:if test="${headers.wxImgOne != null and !empty headers.wxImgOne}">
								<div class="col-xs-6">
									<p><img src="${headers.wxImgOne}" alt="" /></p>
									<p>${headers.wxImgOneDescription}</p>
								</div>
							</c:if>
							<c:if test="${headers.wxImgTwo != null and !empty headers.wxImgTwo}">
								<div class="col-xs-6">
									<p><img src="${headers.wxImgTwo}" alt="" /></p>
									<p>${headers.wxImgTwoDescription}</p>
								</div>
							</c:if>
						</div>
						<%-- <div class="tpy-tel-box hide">
							<p><img src="${headers.contactImg}" alt="" /></p>
							<p>扫码直接拨打电话</p>
						</div> --%>
					</div>
					<div class="col-xs-2 tpy-header-phone pull-right">${headers.phone}</div>
				</div>
			</div>
		</div>
		
		<div class="tpy-header-bottom">
			<div class="container tpy-bottom-container">
				<div class="row">
					<div class="col-xs-5 tpy-header-logo"><a href="/"><img src="${headers.logoImg}" alt="" /></a></div>
					<div class="col-xs-3 col-xs-offset-4 tpy-header-status tpy-login-success">
                     <c:if test="${not empty custinfo}">
					<div class="tpy-login alignItem">
						<div class="tpy-login-img"><img src="<c:if test="${empty custinfo.headImg}">/resource/images/person-head.jpg</c:if><c:if test="${not empty custinfo.headImg}">${custinfo.headImg}</c:if>" alt="" /></div>
						<div class="tpy-login-name">${custinfo.phoneNum}</div>
					</div>
					<div class="tpy-login-box hide">
						<p><a href="/logout">退出登录</a></p>
						
					</div>
					</c:if>	
					<c:if test="${empty custinfo}">
					<ul class="js-log-btn">
								<li><a class="js-login" href="javascript:;">登录</a></li>
								<li class="tpy-register"><a class="js-register" href="javascript:;">注册</a></li>
					</ul>
					</c:if>
                </div>						
				</div>
				<div class="row">
					<div class="col-xs-8 tpy-header-nav">
					
					<ul>
						<c:if test="${menu != null and !empty menu}">
							<c:forEach var="item" items="${menu}">
								<c:choose>
									<c:when test="${item.menu_type==menu_type_cdl}">
										<li <c:if test="${item.subMenuList != null and !empty item.subMenuList}"> class="js-nav-company"</c:if>>
											<a href="${item.menu_url}" <c:if test="${item.open_mode==open_mode_blank}">target="_blank"</c:if>>${item.menu_name}</a><i></i>
											<c:if test="${item.subMenuList != null and !empty item.subMenuList}">
												<div class="nav-company-box hide">
													<c:forEach var="sub" items="${item.subMenuList}">
														<p><a href="${sub.menu_url}" <c:if test="${sub.open_mode==open_mode_blank}">target="_blank"</c:if>>${sub.menu_name}</a></p>
													</c:forEach>
												</div>
											</c:if>
									</c:when>
									<c:when test="${item.menu_type==menu_type_gj}">
										<li class="js-nav-country">
											<a href="${item.menu_url}" <c:if test="${item.open_mode==open_mode_blank}">target="_blank"</c:if>>${item.menu_name}</a><i></i>
											<c:if test="${item.subMenuList != null and !empty item.subMenuList}">
												<div class="nav-country-box js-country-box hide">
													<div class="nav-country-left">
														<c:forEach var="sub" items="${item.subMenuList}">
															<p><a href="/country?continent_name=${sub.menu_name}">${sub.menu_name}</a></p>
														</c:forEach>
													</div>
													<div class="nav-country-right">
														<c:forEach var="sub" items="${item.subMenuList}">
															<c:if test="${sub.subMenuList != null and !empty sub.subMenuList}">
																<p>
																	<c:forEach var="three" items="${sub.subMenuList}">
																		<a href="/country/survey/${three.dict_code}" <c:if test="${three.open_mode==open_mode_blank}">target="_blank"</c:if>>${three.menu_name}</a>
																	</c:forEach>
																</p>
															</c:if>
														</c:forEach>
													</div>
												</div>
											</c:if>
										</li>
									</c:when>
									<c:when test="${item.menu_type==menu_type_xm}">
										<li class="js-nav-country">
											<a href="${item.menu_url}" <c:if test="${item.open_mode==open_mode_blank}">target="_blank"</c:if>>${item.menu_name}</a><i></i>
											<c:if test="${item.subMenuList != null and !empty item.subMenuList}">
												<div class="nav-country-box js-country-box hide">
													<div class="nav-country-left">
														<c:forEach var="sub" items="${item.subMenuList}">
															<p><a href="/project?type=${sub.dict_code}">${sub.menu_name}</a></p>
														</c:forEach>
													</div>
													<div class="nav-country-right">
														<c:forEach var="sub" items="${item.subMenuList}">
															<c:if test="${sub.subMenuList != null and !empty sub.subMenuList}">
																<p>
																	<c:forEach var="three" items="${sub.subMenuList}">
																		<a href="/project?country=${three.dict_code}&type=${sub.dict_code}" <c:if test="${three.open_mode==open_mode_blank}">target="_blank"</c:if>>${three.menu_name}</a>
																	</c:forEach>
																</p>
															</c:if>
														</c:forEach>
													</div>
												</div>
											</c:if>
										</li>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</ul>
					</div>
					<div class="col-xs-4 tpy-header-coopration">
						<span>战略合作伙伴</span>
						<a class="coop-zhtx" href="http://www.pacificedu.cn/">智慧天下</a>
						<a class="coop-ztx" href="http://www.globalfang.com/">宅天下</a>
					</div>
				</div>
			</div>
		</div>
	</div>