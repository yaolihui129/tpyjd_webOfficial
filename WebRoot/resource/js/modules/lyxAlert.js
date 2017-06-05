;(function(){
	function lyxAlert(opt){
		var btn = opt && opt.btn || '.js-messageBtn',
			title = opt && opt.title || '我们将对您的信息严格保密，请放心填写',
			inputs = opt && opt.inputs || ['您的姓名','手机号码'],
			input_class = opt && opt.inputs || ['js-name','js-tel'],
			button_val = opt && opt.buttonVal || '确认提交';
				
		var html = '<div class="leave-message-box"><h2>'+title+'<span class="leave-close js-close">×</span></h2><ul class="js-message-input"></ul><p class="text-center"><input class="blue-btn js-tijiao" type="button" value="'+ button_val +'" /></p>';
		$("body").append($(html));			
		//渲染input
		var li = '';
		for(var i=0;i<inputs.length;i++){
			li += '<li><label>'+ inputs[i] +'</label><input class="'+ input_class[i] +' forbid" type="text" />'+'<p class="person-tips hide">请输入正确格式的'+ inputs[i] +'！</p></li>';
		}
		$(".js-message-input").append($(li));
		$(".js-tijiao").on("click",function(){
			opt && opt.back && opt.back();
		});
		
		$(".js-close").on("click",function(){
			close();
		});
		function close(){
			flag = true;
			$(".leave-message-box").remove();
		}
	}
	window.lyxAlert = lyxAlert;

})();
