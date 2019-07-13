//打开字滑入效果
window.onload = function() {
	$(".connect p").eq(0).animate({
		"left": "0%"
	}, 600);
	$(".connect p").eq(1).animate({
		"left": "0%"
	}, 400);
};

//jquery.validate表单验证
$(document).ready(function() {
	//登陆表单验证
	$("#loginForm").validate({
		rules: {
			username: {
				required: true, //必填
				minlength: 2, //最少2个字符
				maxlength: 10, //最多10个字符
			},
			password: {
				required: true,
				minlength: 3,
				maxlength: 10,
			},
		},
		
		//错误信息提示
		messages: {
			username: {
				required: "必须填写用户名",
				minlength: "用户名至少为2个字符",
				maxlength: "用户名至多为10个字符",
			},
			password: {
				required: "必须填写密码",
				minlength: "密码至少为3个字符",
				maxlength: "密码至多为10个字符",
			},
		},

	});
	
	//注册表单验证
	$("#registerForm").validate({
		rules: {
			username: {
				required: true, //必填
				minlength: 2, //最少2个字符
				maxlength: 10, //最多10个字符
				
			},
			password: {
				required: true,
				minlength: 3,
				maxlength: 32,
			},
			name: {
				required: true, //必填
				minlength: 2, //最少2个字符
				maxlength: 10, //最多10个字符
			},
			email: {
				required: true,
				email: true,
			},
			confirm_password: {
				required: true,
				minlength: 3,
				equalTo: '.password'
			},
			phone: {
				required: true,
				phone: true, //自定义的规则
				digits: true, //整数
			},
			idCard: {
				required: true,
				minlength: 18,
				maxlength: 18,
			}
		},
		
		//错误信息提示
		messages: {
			username: {
				required: "必须填写用户名",
				minlength: "用户名至少为2个字符",
				maxlength: "用户名至多为10个字符",
				
			},
			password: {
				required: "必须填写密码",
				minlength: "密码至少为3个字符",
				maxlength: "密码至多为32个字符",
			},
			name: {
				required: "必须填写用户名",
				minlength: "用户名至少为2个字符",
				maxlength: "用户名至多为10个字符",
			},
			email: {
				required: "请输入邮箱地址",
				email: "请输入正确的email地址"
			},
			confirm_password: {
				required: "请再次输入密码",
				minlength: "确认密码不能少于3个字符",
				equalTo: "两次输入密码不一致", //与另一个元素相同
			},
			phone: {
				required: "请输入手机号码",
				digits: "请输入正确的手机号码",
			},
			idCard: {
				required: "请输入身份证号码",
				minlength: "请输入正确的身份证号码",
				maxlength: "请输入正确的身份证号码", 
			}
		},
	});
	
	//添加自定义验证规则
	jQuery.validator.addMethod("phone", function(value, element) {
		var length = value.length;
		var phone = /^((1[0-9]{1})+\d{9})$/;
		return this.optional(element) || (length == 11 && phone.test(value));
	}, "手机号码格式错误");
});
	//邮箱验证码
	$("#yzm").click(function(){
		console.log("aa");
		$.ajax({
			type:"POST",
			url:"validateEmail",
			data:"userEmail="+$("#userEmail").val(),
			success:function(data){
				console.log(data);
			}
		});
	});
	
	//注册用户
	$("#submitzc").click(function(){
		var formData = $("form").serialize();
		console.log(formData);
		$.ajax({
			type:"POST",
			url:"vip/addVip",
			data:formData,
			success:function(data){
				console.log(data);
				if(data){
					window.location="login.html";
				}
			}
		});
		return false;
	});	
