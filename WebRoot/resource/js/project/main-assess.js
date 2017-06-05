requirejs(["configs"], function () {
    require(['jquery','bxslider','common','formValidate','yzm','lay'], function ($) {
		(function () { //移民评估单选按钮点击事件
            $(".assess-question .js-single a").on("click", function () {
            	var $this = $(this),
            		$this_parent = $this.parent(".select-box");
                $this.addClass("on").siblings().removeClass("on");
                $this_parent.prev("p").data("bf","0").find("span").remove();
            });
            $(".assess-question .js-double a").on("click", function () {//移民评估多选按钮点击事件
            	var $this = $(this),
            		$this_parent = $this.parent(".select-box");
                $this.toggleClass("on");
                if($this.hasClass("on")){
                	$this_parent.prev("p").data("bf","0").find("span").remove();
                }
            });
            $(".assess-question .js-ys input").keyup(function(){
            	var $this = $(this),
            	    this_parent = $this.parent(".js-ys");
            	if(!$this.val().trim()){
            		layer.alert("输入框不能为空")
	                $this.removeClass("on");
	                return;
            	}
            	if(!isNaN($this.val().trim())){
            		this_parent.prev("p").find("span").remove();
		    		this_parent.prev("p").data("bf","0");
		    		$this.addClass("on");
		    		
            	}else{
            		$this.removeClass("on");
            		layer.alert("请输入数字")
            	}
            });
        })();
    });
});