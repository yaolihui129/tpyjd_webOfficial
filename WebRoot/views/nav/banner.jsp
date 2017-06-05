<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="tpy-other-banner">
	<c:choose>
		<c:when test="${bannerImgs != null and !empty bannerImgs}">
			<script>var officialFrequency = ${topicSetting.officialFrequency}</script>
			<ul class="bxslider js-bxslider">
				<c:choose>
					<c:when test="${topicSetting != null}">
						<c:set var="tpiend" value="${fn:length(bannerImgs) > topicSetting.officialNumber?topicSetting.officialNumber-1:fn:length(bannerImgs)}" />
						<c:forEach var="img" items="${bannerImgs}" begin="0"  end="${tpiend}"  >
				  			 <li style="background: url('${img.topicImage}') no-repeat scroll center center;"><c:if test="${img.topicUrl != null}"><a href="${img.topicUrl}"></a></c:if></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="img" items="${bannerImgs}">
				  			<li style="background: url('${img.topicImage}') no-repeat scroll center center;"><c:if test="${img.topicUrl != null}"><a href="${img.topicUrl}"></a></c:if></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</c:when>
		<c:otherwise>
			<div class="other-banner-only" style="background: url('/resource/images/obanner.jpg') no-repeat scroll center center;"></div>
		</c:otherwise>
	</c:choose>
	
	<div class="other-breadline">
		<div class="container">
			<div class="row">
				<c:if test="${banner != null  and !empty banner}">
				<div class="col-xs-12">当前位置：
					<c:forEach var="item" items="${banner}" varStatus="status">
						<c:choose>
							<c:when test="${status.count==1}">
								<a href="${item.menuUrl}">${item.menuName}</a>														
							</c:when>
							<c:otherwise>
								<span>></span><a href="${item.menuUrl}">${item.menuName}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

