<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>美国EB-5延期至2017年4月28日</title>
    <meta name="description" content="太平洋出国全球TOP10投资详情咨询：400-610-8118.">
    <meta name="keywords" content="US-EB5" />

    <!--系统css-->
    <link rel="stylesheet" href="/resource/css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="/resource/css/jquery.bxslider.css" type="text/css" />
    <link rel="stylesheet" href="/resource/css/layer.css" type="text/css" />
    <link rel="stylesheet" href="/resource/css/common.css" type="text/css" />
    <!--页面css-->
    <link href="/views/pcTopic/us_20170214_xyt_pc_1/style.css" type="text/css" rel="stylesheet" />
    <!--系统js-->
    <script src="/resource/js/system/jquery-2.2.0.min.js"></script>
    <!--页面js-->
    <script type="text/javascript" src="/views/pcTopic/us_20170214_xyt_pc_1/superslide.2.1.js"></script>
</head>

<body class="bg-grey">
	<!-- 登录窗口 -->
			<%@ include  file="/views/nav/loginbox.jsp"%>
	<!--header头部开始-->
		<%@ include  file="/views/nav/header.jsp"%>
	<!--header头部结束-->

	<!-- 内容开始 -->
    <div class="imgContainer" style="height:599px; width: 100%;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/banner.jpg">
        <a style="bottom: 120px; left: 50%; margin-left: -105px; width: 210px; height: 40px;" class="click" href="http://tb.53kf.com/code/client/10141314/1]" target="_blank"></a>
    </div>
    <div class="container">
        <!--<div class="center" style="padding-top: 30px;">2016年，太平洋出国帮助数以千计的家庭成功移民、海外置业、海外投资。依托精品项目护航，太平洋出国使命必达，成就客户实现梦想，托起合作伙伴。全球优选，四海一家，为您揭晓2017年海外优质投资项目TOP10，供您投资参考。</div>-->
        <div class="imgContainer" style="height:361px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/read.jpg">
            <div class="fullSlide1">
                <div class="bd1">
                    <ul>
                        <li><img src="us_20170214_xyt_pc_1/lunbo1-1.jpg" alt="" /></li>
                        <li><img src="us_20170214_xyt_pc_1/lunbo1-2.jpg" alt="" /></li>
                        <li><img src="us_20170214_xyt_pc_1/lunbo1-3.jpg" alt="" /></li>

                    </ul>
                </div>
                <div class="hd1">
                    <ul></ul>
                </div>
            </div>
            <!--fullSlide end-->
            <script type="text/javascript">
                $(".fullSlide1").slide({
                    titCell: ".hd1 ul",
                    mainCell: ".bd1 ul",
                    effect: "fold",
                    autoPlay: true,
                    autoPage: true,
                    trigger: "click",
                    startFun: function (i) {
                        var curLi = jQuery(".fullSlide1 .bd1 li").eq(i);
                        if (!!curLi.attr("_src")) {
                            curLi.css("background-image", curLi.attr("_src")).removeAttr("_src")
                        }
                    }
                });
            </script>
        </div>
        <div id="a1" class="imgContainer" style="height:640px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/1.jpg"></div>
        <div id="a2" class="imgContainer " style="height:761px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/2.jpg">
            <div class="fullSlide2">
                <div class="bd2">
                    <ul>
                        <li><img src="us_20170214_xyt_pc_1/lunbo2-1.jpg" alt="" /></li>
                        <li><img src="us_20170214_xyt_pc_1/lunbo2-2.jpg" alt="" /></li>
                    </ul>
                </div>
                <div class="hd2">
                    <ul></ul>
                </div>
            </div>
            <!--fullSlide end-->
            <script type="text/javascript">
                $(".fullSlide2").slide({
                    titCell: ".hd2 ul",
                    mainCell: ".bd2 ul",
                    effect: "fold",
                    autoPlay: true,
                    autoPage: true,
                    trigger: "click",
                    startFun: function (i) {
                        var curLi2 = jQuery(".fullSlide2 .bd2 li").eq(i);
                        if (!!curLi2.attr("_src")) {
                            curLi2.css("background-image", curLi2.attr("_src")).removeAttr("_src")
                        }
                    }
                });
            </script>
        </div>
        <div class="imgContainer " style="height:565px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/2-2.jpg">
            <a style="bottom: 0px; left: 50%; margin-left: 300px; width: 125px; height: 40px;" class="click" href="http://tb.53kf.com/code/client/10141314/1]" target="_blank"></a>
            <iframe style="position: absolute; bottom:43px; left: 50%; margin-left: -485px;" height=380 width=630 src='http://player.youku.com/embed/XMjUxNDgyODA4OA==' frameborder=0 'allowfullscreen'></iframe>
        </div>
        <div id="a3" class="imgContainer " style="height:636px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/3.jpg">
            <div class="fullSlide3">
                <div class="bd3">
                    <ul>
                        <li><img src="/views/pcTopic/us_20170214_xyt_pc_1/lunbo3-1.jpg" alt="" /></li>
                        <li><img src="/views/pcTopic/us_20170214_xyt_pc_1/lunbo3-2.jpg" alt="" /></li>
                    </ul>
                </div>
                <div class="hd3">
                    <ul></ul>
                </div>
            </div>
            <!--fullSlide end-->
            <script type="text/javascript">
                $(".fullSlide3").slide({
                    titCell: ".hd3 ul",
                    mainCell: ".bd3 ul",
                    effect: "fold",
                    autoPlay: true,
                    autoPage: true,
                    trigger: "click",
                    startFun: function (i) {
                        var curLi = jQuery(".fullSlide3 .bd3li").eq(i);
                        if (!!curLi.attr("_src")) {
                            curLi.css("background-image", curLi.attr("_src")).removeAttr("_src")
                        }
                    }
                });
            </script>
        </div>
        <div id="a4" class="imgContainer " style="height:613px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/4.jpg"></div>
        <div id="a5" class="imgContainer " style="height:431px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/5.jpg"></div>
        <div id="a6" class="imgContainer " style="height:443px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/6.jpg">
            <a style="bottom: 10px; left: 50%; margin-left: -452px; width: 210px; height: 40px;" class="click" href="http://tb.53kf.com/code/client/10141314/1]" target="_blank"></a>
        </div>
        <div id="a7" class="imgContainer " style="height:436px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/7.jpg"></div>
        <div id="a8" class="imgContainer " style="height:446px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/8.jpg"></div>
        <div id="a9" class="imgContainer " style="height:437px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/9.jpg">
            <a style="bottom: 68px; left: 50%; margin-left: -264px; width: 191px; height: 40px;" class="click" href="http://tb.53kf.com/code/client/10141314/1]" target="_blank"></a>
        </div>
        <div class="imgContainer " style="height:474px;" imgUrl="/views/pcTopic/us_20170214_xyt_pc_1/10.jpg"></div>




        <!--<div class="imgContainer mt-90" style="height:490px;position: relative;" imgUrl="/views/pcTopic/20170214-US-XYT-PC-1/ad4.png">
				<a class="click" href="http://tb.53kf.com/code/client/10141314/1]" target="_blank"></a>
			</div>-->

    </div>
    <div class="fixed">
        <div class="save-a">
            <a href="#a1" title="已签订租赁合同">01 <span class="a-title">已签订租赁合同</span></a><a href="#a2">02 <span class="a-title" style="left: 62px;">知名地产开发商</span></a><a href="#a3">03 <span class="a-title" style="left:112px">优越的地理位置</span></a><a href="#a4">04 <span class="a-title" style="top: 53px; left: 10px;">安全的资金结构</span></a><a href="#a5">05 <span class="a-title" style="top: 53px; left: 63px;">强大的贷款机构</span></a><a href="#a6">06 <span class="a-title" style="top: 53px; left: 112px;">极高的就业盈余</span></a><a href="#a7">07 <span class="a-title" style="top: 103px; left: 10px;">安全地资金监管</span></a><a href="#a8">08 <span class="a-title" style="top: 103px; left: 62px;">独立的项目管理</span></a><a href="#a9">09 <span class="a-title" style="top: 103px; left: 112px;">可靠的贷款偿付</span></a>
        </div>

    </div>

    <!-- 内容结束 -->
 	
	<!--footer尾部开始-->
			<%@ include  file="/views/nav/footer.jsp"%>
	<!--footer尾部结束-->
	<!--系统js-->
    <!--  <script src="../resource/js/system/jquery-2.2.0.min.js"></script>-->
    <script src="/resource/js/system/layer.js"></script>
    <script src="/resource/js/modules/common.js"></script>
    <script src="/resource/js/modules/yzm.js"></script>
    <script src="/resource/js/modules/formValidate.js"></script>

    <script>
        $(function () {
            $(".save-a a").on("click", function () {
                $(this).addClass("on").siblings().removeClass("on");
            });
            $(".save-a a").hover(function () {
                $(this).find("span").show();
                $(this).siblings().find("span").hide();
            }, function () {
                $(this).find("span").hide();
            });
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
    <script type="text/javascript">
        function clicks(click, show) {
            click.on("hover", function () {
                var $this = $(this),
                    _index = $this.index();
                $this.addClass("on").siblings().removeClass("on");
                show.eq(_index).show().siblings().hide();
            });
        };
        clicks($(".select li"), $(".show li"));
    </script>
	</body>
</html>


