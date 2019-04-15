//统计变量
var logid=false;
var pwd=false;
var rpwd=false;
var yzm=false;

//窗体加载完毕
$(document).ready(function(){
	
	//切换验证码
	$("input[name='yzm']").next("img").css({"cursor":"pointer"}).click(function(){
		yzm=false;
		$("input[name='yzm']").val("");
		$(this).next("span").html("");
		//浏览器缓存，保证不重名的请求
		$(this).attr("src","createyzm?r="+Math.random());
	});
	
	
	//用户名
	$("input[name='logid']").blur(function(){
		//正则
		if(/^[A-Za-z]\w{2,15}$/.test($(this).val())){
			//重名
			$.ajax({
				type:"post",
				url:"rname",
				data:"logid="+$(this).val(),
				success:function(result){
					if(result=="0"){
						logid=true;
						$("input[name='logid']").next("span").css({"color":"#00ff00"}).html("√");
					}
					else{
						logid=false;
						$("input[name='logid']").next("span").css({"color":"#ff0000"}).html("重名！");
					}
				}
			});
		}
		else{
			logid=false;
			$(this).next("span").css({"color":"#ff0000"}).html("格式错误！");
		}
	});
	
	
	
	//判断验证码
	$("input[name='yzm']").blur(function(){
		//正则
		if(/^[A-Za-z]{4}$/.test($(this).val())){
			$.ajax({
				type:"post",
				url:"checkyzm",
				data:"yzm="+$(this).val(),
				success:function(result){
					if(result=="1"){
						yzm=true;
						$("input[name='yzm']").next("img").next("span").css({"color":"#00ff00"}).html("√");
					}
					else{
						yzm=false;
						$("input[name='yzm']").next("img").next("span").css({"color":"#ff0000"}).html("X");
					}
				}
			});
		}
		else{
			yzm=false;
			$(this).next("img").next("span").css({"color":"#ff0000"}).html("格式错误！");
		}
	});
	
	
	//密码和确认密码过正则表达式就可以了（缺省）
	pwd=true;
	rpwd=true;
	
	//提交
	$("#sub").click(function(){
		
		if(logid && pwd && rpwd && yzm){
			$("form").attr("action","register").submit();
		}
		else{
			alert("信息有误，请完善！");
		}
		
	});
	
	
	
	
	
	
	
});