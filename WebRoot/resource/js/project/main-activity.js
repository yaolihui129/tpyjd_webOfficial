requirejs(["configs"], function () {
    require(['jquery','bxslider','common','formValidate','yzm','lay','lyxAlert'], function ($) {
		function showmonth(obj){//活动页中展示收缩盒子
			var $obj = $(obj);
			$obj.on("click",function(){
				var $this = $(this),
					$this_next = $this.next(),
					$this_other = $this.parent().siblings().find(obj),
					$this_similar = $this_other.next();
				$this_next.slideToggle(500);
				$this_similar.slideUp(500);
				$this.toggleClass("on");
				$this_other.removeClass("on");
			});
		}
		showmonth(".js-activity-times");		
		$(".js-time-box:first").find(".js-activity-times").addClass("on");
		
    });
});