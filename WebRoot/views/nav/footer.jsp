<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="tpy-footer">
	<div class="container tpy-footer-container">
		<div class="row alignItem pb-20">
			<div class="col-xs-5">
			<div class="mb-20 tpy-logo-set"><a href="/"><img src="${footers.logoImg}" alt="" /></a></div>
				<p>${footers.companyName} </p>
				<p>地址：${footers.companyAdd}</p>
				<p>客服邮箱：${footers.email} </p>
				<p>全国咨询热线：${footers.customerHotline} </p>
				<p>资讯服务热线：${footers.serviceHotline}</p>
			</div>
			<div class="col-xs-3 tpy-foot-code">
				<div class="col-xs-6"><img src="${footers.wxImgOne}"/></div>
				<div class="col-xs-6"><img src="${footers.wxImgTwo}"/></div>
			</div>
			<div class="col-xs-3 col-xs-offset-1">
				<p class="pTB-15 pl-0">友情链接</p>
				<ul class="foot-flink">
					<c:if test="${friendly != null}">
						<c:forEach var="item" items="${friendly}">
							<li><a href="${item.url}" target="_blank">${item.title}</a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="tpy-footer-bottom">
		<span>${copy.companyName}</span>
		<span>${copy.copyright} 版权所有</span>
		<span>${copy.recordation}</span>
	</div>
</div>
<%-- <div class="tpy-footer">
	<div class="container tpy-footer-container">
		<div class="row alignItem pb-20">
			<div class="col-xs-5">
			<div class="mb-20 tpy-logo-set"><img src="/resource/images/tpy-footer-logo.png" alt="" /></div>
				<p>${footers.companyName} </p>
				<p>地址：${footers.companyAdd}</p>
				<p>客服邮箱：${footers.email} </p>
				<p>全国咨询热线：${footers.customerHotline} </p>
				<p>资讯服务热线：${footers.serviceHotline}</p>
			</div>
			<div class="col-xs-3 tpy-foot-code">
				<div class="col-xs-6"><img src="${footers.wxImgOne}"/></div>
				<div class="col-xs-6"><img src="${footers.wxImgTwo}"/></div>
			</div>
			<div class="col-xs-3 col-xs-offset-1">
				<p class="pTB-15 pl-0">友情链接</p>
				<ul class="foot-flink">
					<c:if test="${friendly != null}">
						<c:forEach var="item" items="${friendly}">
							<li><a href="${item.url}">${item.title}</a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="tpy-footer-bottom">
		<span>${copy.companyName}</span>
		<span>${copy.copyright} 版权所有</span>
		<span>${copy.recordation}</span>
	</div>
</div> --%>