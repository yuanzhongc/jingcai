	/* 处理右边宽度  */
	function autoRigthWidth(){
		var width = getClientWidth();
		var leftW = $(".leftArea").width() + 20;
		$(".rightArea").css({"width":(width-leftW) + "px"});
		var rightW = $(".rightArea").width();
		var btw = parseInt($(".rightArea").css("borderLeftWidth")) * 4;
		$(".rightArea").css({"width":(rightW - btw) + "px"});
	};
	
	function autoLeftHeight(){
		var height = $(document).height();
		var hheight = $(".header").height()  + 33;
		var blw = parseInt($(".header").css("borderLeftWidth")) * 4;
		$(".leftArea").css({"height":(height - hheight - blw) + "px"});
	}