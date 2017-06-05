function selectOption(dom) {
    $(dom).css("display", "none");
    // var str = '<div class="select"></div><div class="select"></div>';
    $(dom).each(function (i, dom) {
        var $opts = $(this).children(),
            htmlArr = ['<div class="select">', '<i></i>'],
            $selbox = "",
            flag = false;
        htmlArr.push('<p>' + $opts.eq(0).text() + '</p>');
        htmlArr.push('<ul>');
        $opts.each(function (i, opt) {
            htmlArr.push('<li>' + opt.text + '</li>');

        })
        htmlArr.push('</ul>', '</div>');
        $selbox = $('<div class="input"></div>');
        $selbox.html(htmlArr.join(""));
        //console.log($selbox.html());
        $(this).before($selbox);
        //点击其他地方
        $(document).click(function () {
            close($selbox);
            flag = false;
        });
        //给$selbox绑定事件
        $selbox.on("click", function (event) {
                event.stopPropagation();
                if (!flag) {
                    open($(this));
                    flag = true;
                } else {
                    close($(this));
                    flag = false;
                }
            })
            //展开菜单
        function open($sel) {
            $sel.find("ul").css("display", "block").end().addClass("up");
            $sel.siblings().find("ul").css("display", "none").end().removeClass("up");
        }
        //关闭菜单
        function close($sel) {
            $sel.find("ul").css("display", "none").end().removeClass("up");
        }
        //点击菜单选项
        $selbox.on("click", "li", function () {
            var label = $(this).parent().prev();
            label.text($(this).text());
            dom.selectedIndex = $(this).index();
            $(dom).trigger("change");
        })

    })

}

selectOption(".search-select");


$(function () {
    markShow();
    indexHeight();
    alertPic();
    SafariPosition();
});
var markShow = function () {
    $(".index3-pic").each(function (index) {
        //console.log(item);
        $(this).mouseenter(function () {
            $(this).find(".index3-pic-mark").css("display", "block");
            $(this).find("span").css("display", "none");
        })
        $(this).mouseleave(function () {
            $(this).find(".index3-pic-mark").css("display", "none");
            $(this).find("span").css("display", "block");
        })
    })
    $(".index4-img").each(function (index) {
        //console.log(item);
        $(this).mouseenter(function () {
            $(this).find(".index4-img-mark").css("display", "block");
            $(this).find(".index4-img-bottom").css("display", "none");
        })
        $(this).mouseleave(function () {
            $(this).find(".index4-img-mark").css("display", "none");
            $(this).find(".index4-img-bottom").css("display", "block");
        })
    });
    $(".Consultant-img").each(function (index) {
        //console.log(item);
        $(this).mouseenter(function () {
            $(this).find(".Consultant-pic-mark").css("display", "block");
            $(this).find(".Consultant-text").css("display", "none");
        })
        $(this).mouseleave(function () {
            $(this).find(".Consultant-pic-mark").css("display", "none");
            $(this).find(".Consultant-text").css("display", "block");
        })
    });

};
var indexHeight = function () {
    var index4Width = $(".index4-img").width();
    $(".index4-img").css("height", index4Width);

};
var alertPic = function () {
    $(".index5-img").each(function (i, dom) {
        $(this).on("click", function () {
            var img = $(this).find("img").attr("src"),
                text = $(this).find(".index5-pic-explain").html();
            var html = '<div class="index5-pic-bg">' + '<div class="index5-pic">' + '<div class="index5-pic-box">' +
                '<img class="index5-pic-content" src=' + img + '>' + '</div>' +
                '<p class="index5-pic-text">' + text + '</p>' +
                '<button class="index5-btn"></button>' +
                '</div>' + '</div>'
            $(".index-5").append(html);
            var imgWidth = $(this).find("img").width(),
                imgHeight = $(this).find("img").height();
            if (imgWidth > imgHeight) {
                $(".index5-pic-content").css({
                    "width": "90%",

                });
            } else if (imgWidth < imgHeight) {
                $(".index5-pic-content").css({
                    "width": "30%",
                });
            }
        });
        $("body").on("click", ".index5-btn", function () {
            $(".index5-pic-bg").remove();
        });
        $("body").on("click", ".bx-next,.bx-prev", function () {
            $(".index5-pic-bg").remove();
        });

    });

};
var SafariPosition = function () {
    /*var ua = window.navigator.userAgent;
    var isSafari = ua.indexOf("Safari") != -1 && ua.indexOf("Version") != -1;
    if (isSafari) {
        alert(Safari);
        $(".index-search").css("bottom", "4%");
    }*/
    function myBrowser() {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1;
        if (isOpera) {
            return "Opera"
        }; //判断是否Opera浏览器
        if (userAgent.indexOf("Firefox") > -1) {
            return "FF";
        } //判断是否Firefox浏览器
        if (userAgent.indexOf("Chrome") > -1) {
            return "Chrome";
        }
        if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } //判断是否Safari浏览器
        if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
            return "IE";
        }; //判断是否IE浏览器
    }

    //以下是调用上面的函数
    var mb = myBrowser();
    if ("IE" == mb) {
        /*alert("我是 IE");*/
    }
    if ("FF" == mb) {
        /* alert("我是 Firefox");*/
    }
    if ("Chrome" == mb) {
        /* alert("我是 Chrome");*/
    }
    if ("Opera" == mb) {
        /*  alert("我是 Opera");*/
    }
    if ("Safari" == mb) {
        //alert("我是 Safari");
        /*$(".index-search").css("bottom", "4%");*/
        if (screen.width > 1920) {
            $(".index-search").css("bottom", "-10.5%"); //此分辨率下你需要的操作
        } else if (screen.width <= 1280) {
            $(".index-search").css("bottom", "4%"); //这个分辨率下你的操作
        }
    }

}