requirejs(["configs"], function () {
    require(['jquery', 'common', 'formValidate', 'yzm', 'lay', 'tab'], function ($) {
        (function () {
            //下拉框替换
            function selectOption(dom) {
                $(dom).css("display", "none");
                // var str = '<div class="select"></div><div class="select"></div>';
                $(dom).each(function (i, dom) {
                    var $opts = $(this).children(),
                        htmlArr = ['<div class="select">', '<i></i>'],
                        $selbox = "",
                        s = $(".dd").eq(i).text(),
                        flag = false;
                    /* htmlArr.push('<p>' + $opts.eq(0).text() + '</p>');*/
                    htmlArr.push('<p>' + s + '</p>');
                    htmlArr.push('<ul>');
                    $opts.each(function (i, opt) {
                        var val = $(this).val();
                        htmlArr.push('<li value=' + val + '>' + opt.text + '</li>');

                    })
                    htmlArr.push('</ul>', '</div>');
                    $selbox = $('<div class="input input' + i + '"></div>');
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

                        $(this).addClass("dd");
                        label.text($(this).text());
                        dom.selectedIndex = $(this).index();
                        $(dom).trigger("change");
                        $(dom).find("option").eq(dom.selectedIndex).addClass("dd").siblings().removeClass("dd");
                        $(dom).find("option").eq(dom.selectedIndex).attr('selected', 'selected');
                        $(dom).find("option").eq(dom.selectedIndex).siblings().removeAttr('selected');

                    })

                })

            }

            selectOption(".search-select");
        })();
    });
});