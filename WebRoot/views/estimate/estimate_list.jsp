<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport">
    <meta name="format-detection"content="telephone=no" />   	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--<meta name="renderer" content="webkit">-->
    <title>太平洋出国|移民评估</title>


	<!--以下系统css-->
	<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
	<!--以下页面css-->
	<link rel="stylesheet" href="/resource/css/assess.css" type="text/css" />
	</head>
	<body class="bg-grey">
			<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
			<!--header头部开始-->
				<%@ include  file="/views/nav/header.jsp"%>
			<!--header头部结束-->

		<!--other banner开始-->
	 		<%@ include  file="/views/nav/banner.jsp"%>
		<!--other banner结束-->

		
		<div id="qs_box"  class="container tpy-container assess-question"><!--中间container开始-->
			<!-- <div class="text-right "><a href="#" class="assess-btn">我的评估结果</a></div> -->
			
			
			<c:if test="${eqvi != null and !empty eqvi.eqList}">
					<c:forEach var="equestion" items="${eqvi.eqList}" varStatus="status">
						<c:choose>
							<c:when test="${equestion.answer eq danx}">
								<p>${status.count}.${equestion.title}（单选）</p>
								<div class="select-box  js-single <c:if test="${equestion.isrequired==shi}">js-select-box</c:if>">
									<c:if test="${equestion.esList!=null and !empty equestion.esList}">
										<c:forEach var="eselect" items="${equestion.esList}">
												<c:set var="sed_danx" value="-1" />
												<c:set var="a_hotspots" value="-1" />
												<c:if test="${ceqiList != null and !empty ceqiList}">
													<c:forEach var="ceq" items="${ceqiList}">
															<c:if test="${ceq.estimate_question_id==equestion.estimate_question_id and ceq.estimate_select_id==eselect.estimate_select_id}">
																<c:set var="sed_danx" value="${ceq.estimate_select_id}" />
															</c:if>
													</c:forEach>
												</c:if>
												
												<c:if test="${eselect.estimate_select_id==country}">
													<c:set var="sed_danx" value="${eselect.estimate_select_id}" />
												</c:if>
												
												<c:if test="${eselect.estimate_select_id==hotspots}">
													<c:set var="a_hotspots" value="${eselect.estimate_select_id}" />
												</c:if>
												
												<a href="javascript:;" <c:if test="${eselect.estimate_select_id==sed_danx ||eselect.estimate_select_id==a_hotspots}">class="on"</c:if>
													data-q="${equestion.estimate_question_id}" data-s="${eselect.estimate_select_id}">${eselect.content}</a>
												<c:set var="sed_danx" value="-1" /> 
												<c:set var="a_hotspots" value="-1" />
										</c:forEach>
									</c:if>
								</div>
							</c:when>
							<c:when test="${equestion.answer eq duox}">
								<p>${status.count}.${equestion.title}（多选）</p>
								<div class="select-box  js-double <c:if test="${equestion.isrequired==shi}">js-select-box</c:if>">
									<c:if test="${equestion.esList!=null and !empty equestion.esList}">
										<c:forEach var="eselect" items="${equestion.esList}">
												<c:set var="sed_duox" value="-1" /> 
												<c:set var="u_hotspots" value="-1" />
												<c:if test="${ceqiList != null and !empty ceqiList}">
													<c:forEach var="ceq" items="${ceqiList}">
															<c:if test="${ceq.estimate_question_id==equestion.estimate_question_id and ceq.estimate_select_id==eselect.estimate_select_id}">
																<c:set var="sed_duox" value="${ceq.estimate_select_id}" />
															</c:if>
													</c:forEach>
												</c:if>
												
												<c:if test="${eselect.estimate_select_id==hotspots}">
													<c:set var="u_hotspots" value="${eselect.estimate_select_id}" />
												</c:if>
												
												<c:if test="${eselect.estimate_select_id==country}">
													<c:set var="sed_duox" value="${eselect.estimate_select_id}" />
												</c:if>
																								
												<a href="javascript:;" <c:if test="${eselect.estimate_select_id==sed_duox || eselect.estimate_select_id==u_hotspots}">class="on"</c:if>
													data-q="${equestion.estimate_question_id}" data-s="${eselect.estimate_select_id}">${eselect.content}</a>
												<c:set var="sed_duox" value="-1" /> 
												<c:set var="u_hotspots" value="-1" />
										</c:forEach>
									</c:if>
								</div>
							</c:when>
							<c:when test="${equestion.answer eq wenb}">
								<p>${status.count}.${equestion.title}</p>
								<div class="select-box js-ys <c:if test="${equestion.isrequired==shi}">js-select-box</c:if>">
									<input type="text"  style="border:1px solid #eaeaea;" data-q="${equestion.estimate_question_id}" 
										<c:if test="${ceqiList != null and !empty ceqiList}">
											<c:forEach var="ceq" items="${ceqiList}">
												<c:choose>
													<c:when test="${ceq.estimate_question_id==equestion.estimate_question_id and ceq.int_value != null}">
														value="${ceq.int_value}"
														class="assess-ys question_value on"
													</c:when>
												</c:choose>
											</c:forEach>
										</c:if> class="assess-ys question_value" placeholder="请输入数字"/>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:if>
				
			
			
			<div class="assess-button clearfix">
				<input class="blue-btn" type="button" onclick="tijiao()" value="确认提交" />
			</div>
		</div><!--中间container结束-->
		
		<!--footer尾部开始-->
				<%@ include  file="/views/nav/footer.jsp"%>
		<!--footer尾部结束-->

	<script data-main="/resource/js/project/main-assess" src="/resource/js/system/require.js"></script>
	<script type="text/javascript">
		window.f = true;
		function tijiao(){
			if(f){
				f = false;
				var flag = true;
				$(".js-select-box").each(function(){
					var $this = $(this);
					if(!$this.children().hasClass("on")){
						var warn = $("<span class='text-red ml-10'>(您还没有选择此项)</span>");
						if($this.prev("p").data("bf")=="1"){
							flag = false;
							f = true;
							return;
						}else{
							$this.prev("p").append(warn);
							$this.prev("p").data("bf","1");
							flag = false;
							f = true;
							return;
						}
					}
				});
				if(!flag) return;
				
				//放入提交代码	
				var as = $("#qs_box").find(".on").not("input");
				var cls = $(".question_value");
				var versionId = "${eqvi.version_id}";
				
				var list = new Array();
				for(var i=0; i<as.length; i++) {
					var estimateRecord = new Object;
					estimateRecord.estimateQuestionId = $(as[i]).attr("data-q");
					estimateRecord.estimateSelectId = $(as[i]).attr("data-s");
					list.push(estimateRecord);
				}
				
				for(var i=0; i<cls.length; i++) {
					if($(cls[i]).val()) {
						var estimateRecord = new Object;
						estimateRecord.estimateQuestionId = $(cls[i]).attr("data-q");
						estimateRecord.intValue = $(cls[i]).val();
						list.push(estimateRecord);
					}
				}
				
				if(versionId && list) {
					var data = saveEstimate(versionId, list);
					if(data.status==0) {
						window.location.href = "/estimate/result";
					} else if(data.status==9) {
						$(".js-login-alert,.js-alert-box").removeClass("hide");
			            $(".js-alert-mmbox").addClass("hide");
			            f=true;
					} else {
						f=true;
					}
				} else {
					f=true;
				}
				
			}
		}
		
		//保存
		function saveEstimate(versionId, list) {
			var datas="";
			$.ajax({
	            type:'post',
	            url:"/estimate/save",
	            data:{"list":JSON.stringify(list), "versionId":versionId},
	            async:false,
	            success:function(data) {
	            	datas=data;
	            }
	        });
			return datas;
		}
		
		</script>
</body>
</html>





