requirejs(["configs"], function () {
    require(['jquery', 'common', 'formValidate', 'yzm', 'lay', 'bxslider'], function ($) {
        (function () {
            $('.counselor-bxslider').bxSlider({
                mode: 'horizontal',
                moveSlides: 1,
                slideMargin: 10,
                infiniteLoop: true,
                slideWidth: 200,
                minSlides: 4,
                maxSlides: 4,
                speed: 800,
            });
        })();
        (function () {
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
        })();
        (function () {

            $(".weixin").on("click", function () {
                $(".erwei-bg").toggleClass("show");
            })
        })()
    });
});