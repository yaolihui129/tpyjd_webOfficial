<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>马耳他国债移民</title>
    <meta name="description" content="马耳他国债移民-太平洋加达为您移民保驾护航,马耳他国债移民,办理从速,太平洋加达移民20多年投资移民经验成功率100%,专业服务顾问团,过万件成功案例,业界首屈一指,详情咨询：400-610-8118.">
    <meta name="keywords" content="马耳他国债移民" />
    <!--系统css-->
	<link rel="stylesheet" href="/resource/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
	<link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
    <!--页面css-->
    <link href="/views/pcTopic/index20160619/style.css" type="text/css" rel="stylesheet" />
</head>

<body class="bg-grey">
	<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
	<!--header头部结束-->

	<!-- 内容开始 -->
    <div class="imgContainer" style="height:600px; background-color:#fff;" imgUrl="/views/pcTopic/index20160619/Banner1.jpg"></div>
    <div class="Malta_07">
        <a href="http://tb.53kf.com/code/client/10141314/1]" target="_blank">
            <div class="hte1"></div>
        </a>
    </div>
    <div class="Malta_08"></div>
    <div class="Malta_09"></div>
    <div class="Malta_10"></div>
    <div class="Malta_11"></div>
    <div class="Malta_12"></div>
    <div class="Malta_13"></div>
    <div class="Malta_14"></div>
    <div class="Malta_15"></div>
    <div class="Malta_16"></div>
    <div class="Malta_17"></div>
    <div class="Malta_18"></div>
    <div class="Malta_19"></div>
    <div class="Malta_20"></div>
    <div class="Malta_21"></div>
    <div class="Malta_22"></div>
    <div class="Malta_23"></div>
    <div class="Malta_24"></div>
    <div class="Malta_25"></div>
    <div class="Malta_26"></div>
    <div class="Malta_27"></div>
    <div class="Malta_28"></div>
    <div class="Malta_29"></div>
    <div class="Malta_30"></div>
    <div class="Malta_31"></div>
    <div class="Malta_32"></div>
    <div class="Malta_33">
        <a href="http://tb.53kf.com/code/client/10141314/1]" target="_blank">
            <div class="hte1"></div>
        </a>
    </div>
    <div class="Malta_34"></div>
    <div class="Malta_35"></div>
    <div class="Malta_36"></div>
    <div class="Malta_37"></div>
    <div class="Malta_38"></div>
    <div class="Malta_39"></div>
    <div class="Malta_40"></div>
    <div class="Malta_41"></div>
    <div class="Malta_42"></div>
    <div class="Malta_43"></div>
    <div class="Malta_44"></div>
    <div class="Malta_45"></div>
    <div class="Malta_46"></div>
    <div class="Malta_47"></div>
    <div class="Malta_48"></div>
    <div class="Malta_49"></div>
    <div class="Malta_50"></div>
    <div class="Malta_51"></div>
    <div class="Malta_52"></div>
    <div class="Malta_53"></div>
    <div class="Malta_54">
        <embed src="http://player.youku.com/player.php/sid/XMTUzNTYxNDg3Mg==/v.swf" allowFullScreen="true" quality="high" width="360" height="210" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>
        <embed src="http://player.youku.com/player.php/sid/XMTUzMzEzMjIyOA==/v.swf" allowFullScreen="true" quality="high" width="360" height="210" style="margin-left:50px;" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>
    </div>

    <!-- 内容结束 -->
 	
	<!--footer尾部开始-->
			<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->
	<!--系统js-->
	<script src="/resource/js/system/jquery-2.2.0.min.js"></script>
	<script src="/resource/js/system/layer.js"></script>
	<script src="/resource/js/modules/common.js"></script>
	<script src="/resource/js/modules/yzm.js"></script>
	<script src="/resource/js/modules/formValidate.js"></script>
    <script>
        $(function () {
            (function () {
                function loadImage() {
                    var imgContainers = $(".imgContainer");
                    var length = imgContainers.length;
                    var index = 0;
                    var item = null;
                    var imgUrl = null;
                    getImg();

                    function getImg() {
                        item = imgContainers.eq(index);
                        imgUrl = item.attr("imgUrl");
                        if (imgUrl != null || imgUrl != undefined) {
                            var image = new Image();
                            image.onload = function () {
                                item.css({
                                    "opacity": "1"
                                });
                                item.css({
                                    "background-image": "url(" + imgUrl + ")",
                                    "opacity": "1"
                                });
                                index++;
                                if (index < length) {
                                    getImg();
                                }
                            }
                            image.src = imgUrl;
                        } else {
                            index++;
                            if (index < length) {
                                getImg();
                            }
                        }
                    }
                }
                loadImage();
            })();
        });
        
    </script>
	</body>
</html>


