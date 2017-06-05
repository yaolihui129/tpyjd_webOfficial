<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
<c:if test="${menu != null and !empty menu}">
	<c:forEach var="item" items="${menu}">
		<c:choose>
			<c:when test="${item.menu_type==menu_type_cdl}">
				<li <c:if test="${item.subMenuList != null and !empty item.subMenuList}"> class="js-index-company"</c:if>>
					<a href="${item.menu_url}" <c:if test="${item.open_mode==open_mode_blank}">target="_blank"</c:if>>${item.menu_name}</a><i></i>
					<c:if test="${item.subMenuList != null and !empty item.subMenuList}">
						<div class="index-company-box hide">
							<c:forEach var="sub" items="${item.subMenuList}">
								<p><a href="${sub.menu_url}" <c:if test="${sub.open_mode==open_mode_blank}">target="_blank"</c:if>>${sub.menu_name}</a></p>
							</c:forEach>
						</div>
					</c:if>
				</li>
			</c:when>
			<c:when test="${item.menu_type==menu_type_gj}">
				<li class="index-nav-country">
					<a href="${item.menu_url}" <c:if test="${item.open_mode==open_mode_blank}">target="_blank"</c:if>>${item.menu_name}</a><i></i>
					<c:if test="${item.subMenuList != null and !empty item.subMenuList}">
						<div class="index-country-box js-index-country hide">
							<div class="index-country-left">
								<c:forEach var="sub" items="${item.subMenuList}">
									<p><a href="/country?continent_name=${sub.menu_name}">${sub.menu_name}</a></p>
								</c:forEach>
							</div>
							<div class="index-country-right">
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
				<li class="index-nav-assess">
					<a href="${item.menu_url}" <c:if test="${item.open_mode==open_mode_blank}">target="_blank"</c:if>>${item.menu_name}</a><i></i>
					<c:if test="${item.subMenuList != null and !empty item.subMenuList}">
						<div class="index-country-box js-index-country hide">
							<div class="index-country-left">
								<c:forEach var="sub" items="${item.subMenuList}">
									<p><a href="/project?type=${sub.dict_code}">${sub.menu_name}</a></p>
								</c:forEach>
							</div>
							<div class="index-country-right">
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