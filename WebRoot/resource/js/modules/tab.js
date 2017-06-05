/*
	introduce:tab切换fn
	auther:liuyuxi
	time：2017.4.25
	use ：父元素 lyx-tab, 子元素：lyx-tab-hand,lyx-tab-container,分别根据设计情况，子元素内放入相应的内容即可。
	notice ：此函数对标签类别未做任何硬性要求。只要符合w3c标签书写模式即可。
*/ 
;(function(){
	function lyxTab(opt){
		var lyx_tab = opt && opt.box || ".lyx-tab",
		    li = lyx_tab + ">.lyx-tab-hand>li";			 
		$("body").on("click",li,function(){
			var $this = $(this),
			    index = $(this).index();
			$this.addClass("on").siblings().removeClass("on");
			$this.parent(".lyx-tab-hand").siblings(".lyx-tab-container").children(".lyx-tab-box").eq(index).addClass("show").siblings().removeClass("show");
		});	
	};
	window.lyxTab = lyxTab;
	lyxTab();
})();