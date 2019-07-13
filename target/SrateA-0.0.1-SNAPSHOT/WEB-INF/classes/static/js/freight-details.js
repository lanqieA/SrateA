
function previewImage(file){
	  var MAXWIDTH  = 260; 
	  var MAXHEIGHT = 180;
	  var div = document.getElementById('preview');
	  if (file.files && file.files[0]){
		  div.innerHTML ='<img id=imghead>';
		  var img = document.getElementById('imghead');
		  img.onload = function(){
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			img.width  =  rect.width;
			img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
			img.style.marginTop = rect.top+'px';
		  }
		  var reader = new FileReader();
		  reader.onload = function(evt){img.src = evt.target.result;}
		  reader.readAsDataURL(file.files[0]);
	  }else{ //兼容IE
		var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
		div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
	  }
	}
	function clacImgZoomParam( maxWidth, maxHeight, width, height ){
		var param = {top:0, left:0, width:width, height:height};
		if( width>maxWidth || height>maxHeight ){
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;
			
			if( rateWidth > rateHeight ){
				param.width =  maxWidth;
				param.height = Math.round(height / rateWidth);
			}else{
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}
		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
	!function(){
		laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
		laydate({elem: '#demo'});//绑定元素
	}();
	
	//日期范围限制
	var start = {
		elem: '#start',
		format: 'YYYY-MM-DD',
		min: laydate.now(), //设定最小日期为当前日期
		max: '2099-06-16', //最大日期
		istime: true,
		istoday: false,
		choose: function(datas){
			 end.min = datas; //开始日选好后，重置结束日的最小日期
			 end.start = datas //将结束日的初始值设定为开始日
		}
	};
	
	var end = {
		elem: '#end',
		format: 'YYYY-MM-DD',
		min: laydate.now(),
		max: '2099-06-16',
		istime: true,
		istoday: false,
		choose: function(datas){
			start.max = datas; //结束日选好后，充值开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);
	
	//自定义日期格式
	laydate({
		elem: '#test1',
		format: 'YYYY年MM月DD日',
		festival: true, //显示节日
		choose: function(datas){ //选择日期完毕的回调
			alert('得到：'+datas);
		}
	});
	
	//日期范围限定在昨天到明天
	laydate({
		elem: '#hello3',
		min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
		max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
	});
    var app_url ='';
    $(document).ready(function(){	

	       $.post(app_url+"goods/",{num:0,type:8},function(data){
				if(data.resid){
						  $('#demo1').html(data.msg);
					}
				},'json');
			
		    $.post(app_url+"goods_count/",{},function(data){
			if(data.resid){
				      $('#zxhz').html(data.msg);	
					  $('#zxcy').html(data.msgs);
				}
			},'json');

		});  

    function setTab(name,cursel,n){
		for(i=1;i<=n;i++){
			if(cursel==i){
			   $('#'+name+i).attr("class","hover");
			   $('#'+name+i).css("background","#fff");
			   $('#'+name+i+" a").css("color","#F5841C");
			   $('#list_'+name+i).css("display","block");
			 }else{
			   $('#'+name+i).attr("class","");
			   $('#'+name+i).css("background","#F5841C");
			   $('#'+name+i+" a").css("color","#fff");
			   $('#list_'+name+i).css("display","none");
			 }
		}
}

    function setTab2(name,cursel,n){
		for(i=1;i<=n;i++){
			if(cursel==i){
			   $('#'+name+i).attr("class","tit_hover");
			   $('#'+name+i+" a").css("color","#29a25b");
			   $('#list_'+name+i).css("display","block");
			 }else{
			   $('#'+name+i).attr("class","");
			   $('#'+name+i+" a").css("color","#fff");
			   $('#list_'+name+i).css("display","none");
			 }
		}
}

	$(function(){
      $('.denglu').click(function(){
           $('.head').toggle();
		   
		   
	    });   
	  $('.guanbi').click(function(){
           $('.head').hide();
	    });
	 $('.guanbi1').click(function(){
           $('.header').hide();
	    });
      $('.chaxun').click(function(){
           var  search_begincity = $('#search_begincity').val();
		   var  search_endcity = $('#search_endcity').val();
		   if(search_begincity.length <= 0 &&search_endcity.length <= 0 ){
			    $('#demo1').show().html("<div style='font-size:12px; color:#50B1E3; line-height:24px; padding-left:20px;'>请输入出发城市或到达城市！</div>");
				return false;
			}
		   
		   var  type = $('#type').val();
		    $.post(app_url+"goods/",{num:0,type:type,search_begincity:search_begincity,search_endcity:search_endcity},function(data){
				if(data.resid){
						  $('#demo1').html(data.msg);
					}
				},'json');
	   
	    });
		
		$('#huo1').click(function(){
			$.post(app_url+"goods/",{num:0,type:8},function(data){
			if(data.resid){
					  $('#demo1').html(data.msg);
				}
			},'json');

		});
		
		$('#huo2').click(function(){
			var num = 0;
			$.post(app_url+"carlist/",{type:6},function(data){
				if(data.resid){
				    $('#demo2').html(data.msg);	
					}
				},'json');
		});
		$('#huo3').click(function(){
			var num = 0;
			$.post(app_url+"goods/",{type:1,num:0},function(data){
				if(data.resid){
				    $('#demo3').html(data.msg);	
					}
				},'json');
		});
		$('#huo4').click(function(){
			var num = 0;
			$.post(app_url+"carlist/",{type:1},function(data){
				if(data.resid){
				    $('#demo4').html(data.msg);	

					}
				},'json');
		});
		$('#huo5').click(function(){
			var num = 0;
			$.post(app_url+"carlist/",{type:2},function(data){
				if(data.resid){
				    $('#demo5').html(data.msg);	

					}
				},'json');
		});
		$('#huo6').click(function(){
			var num = 0;
			$.post(app_url+"goods/",{num:0,type:2},function(data){
				if(data.resid){
				    $('#demo6').html(data.msg);	

					}
				},'json');
		});		

		$('input').focus(function(){  
		   $(this).attr('placeholder','');
		})	
		$('.tp_bk_1').mouseover(function(){  	   
				 $(this).attr('class','tp_bk'); 
		});
		
       	$('.tp_bk_1').mouseout(function(){  
             $(this).attr('class','tp_bk_1'); 
		});
		   
		$('input').blur(function(){
			 var numb = $("#mobile").val();
			 var verify = $("#verify").val();
			 var code = $("#code").val();
			 var mobileno = $("#mobileno").val();
			 var goodstype = $("#goodstype").val();
			 if(mobileno == ''){
				$("#mobileno").attr('placeholder','请输入您的手机号');
			 }
			 if(numb == ''){
				$("#mobile").attr('placeholder','请输入您的手机号');
			 }
			 if(code == ''){
				$("#code").attr('placeholder','请输入验证码');
			 }
			 if(verify == ''){
				$("#verify").attr('placeholder','请输入验证码');
			 } 
		});
		

		$('.btnLogin,#btnLogin').click(function(){
   
			var mobileno = $('#mobileno').val();
			var begincity = $('#begincity').val();
			var endcity = $('#endcity').val();
			var get_addr = $('#get_addr').val();
			var put_addr = $('#put_addr').val();
			var endmobile = $('#endmobile').val();
			var goodstype = $('#goodstype').val();
			var yan_code = $('#yan_code').val();
			var verify = $('#car_verify').val();
             
		   
			if(begincity.length <= 0){
			    $('.begincity').show();
				$('#begincity').focus();
				return false;
			}else{
			    $('.begincity').show();
			}
			if(endcity.length <= 0){
				$('.endcity').show();
				$('#endcity').focus();
				return false;
			}else{
			    $('.endcity').hide();
			}
			
			if(goodstype.length <= 0){
			    $('.goodstype').show().html("货物名称不能为空");
				$('#goodstype').focus();
				return false;
			}else{
			    $('.good_stype').hide();
			}
			if(mobileno.length != 11){
				$('.mobileno').show().html("发货人电话不能为空");
				$('#mobileno').focus();
				return false;
			}else{
			    $('.mobileno').hide();
			}

			if(yan_code.length != 4){
			    $('.header').show();
				$('#car_mobileno').attr("value",mobileno);
				$('#btnLogin').attr("class","btnLogin");
				$('#yan_code').focus();
				return false;
			}else{
			    $('.yan_code').hide();
			}
			

			if(verify.length != 4){
			    $('.ve_rify').show();
				$('#car_verify').focus();
				return false;
			}else{
			    $('.ve_rify').hide();
			}
				$.post(app_url+"goods_send/",{mobileno:mobileno,get_addr:get_addr,put_addr:put_addr,endmobile:endmobile,begincity:begincity,endcity:endcity,verify:verify,goodstype:goodstype,ips:"61.52.245.52"},function(data){
				if(data.resid==1){
				    $('.header').hide();
					$('.btn_Login').html(data.msg);
				    $('.new_importance_tab').append(data.xinxi);
					//$('#items_denglu').html(denglu);
				}else if(data.resid==2){
				    $('.mobileno').show().html(data.msg);
					
				}else{
				    $('.mobileno').show().html(data.msg);
					
				}
			},'json');
			
			 var wait=100; 
             time(this);
			 function time(o) { 
                if (wait == 0) { 
                    o.removeAttribute("disabled");           
                    o.value="发布货源"; 
                    wait = 100; 
                } else { 
                    o.setAttribute("disabled", true); 
                    o.value=wait+"秒可再次发货"; 
                    wait--; 
                    setTimeout(function() { 
                        time(o) 
                    }, 
                    1000) 
                } 
               }
			
		});
		
 	$('.car_btnLogin').click(function(){
			var mobileno = $('#car_mobileno').val();
			var begincity = $('#car_begincity').val();
			var endcity = $('#car_endcity').val();
			var verify = $('#car_verify').val();
			var cartype = $('#cartype').val();
			var car_num = $('#car_num').val();
			var carlength = $('#carlength').val();
			var weight = $('#weight').val();
			var weight_unit = $('#weight_unit').val();
			var volume = $('#volume').val();
			var runtime = $('#runtime').val();
			var mob = $('#mob').val();
			if(begincity.length <= 0){
				$('.city').show().html("出发城市不能为空！");
				$('#car_begincity').focus();
				return false;
			}else{
			      $('.city').hide();
			}
			if(endcity.length <= 0){
				$('.city').show().html("到达城市不能为空！");
				$('#car_endcity').focus();
				return false;
			}else{
			      $('.city').hide();
			}
			
			if(weight.length > 5){
				$('.weight').show().html("重量填写过大");
				$('#weight').focus();
				return false;
			}else{
			    $('.weight').hide();
			}
			if(volume.length > 5){
				$('.weight').show().html("体积填写过大");
				$('#volume').focus();
				return false;
			}else{
			    $('.weight').hide();
			}
			if(runtime.length <= 0){
				$('.runtime').show().html("发车时间不能为空!");
				$('#runtime').focus();
				return false;
			}else{
			    $('.runtime').hide();
			}
			
			
			if(mobileno.length != 11){
				$('.header').show();
				$('#car_mobileno').attr("value",mob);
				$('#btnLogin').attr("id","");
				return false;
			}	
			
			if(verify.length != 4){
				$('.yz_ts').show().html('请输入正确验证码!');
				$('#car_verify').focus();
				return false;
			}	
						
			$.post(app_url+"car_send/",{mobileno:mobileno,begincity:begincity,endcity:endcity,verify:verify,cartype:cartype,car_num:car_num,carlength:carlength,weight:weight,weight_unit:weight_unit,volume:volume,runtime:runtime,ips:"61.52.245.52"},function(data){
				if(data.resid==1){
					$('.header').hide();
				    $('.new_importance_tab').append(data.xinxi);
				}else if(data.resid==2){
                    $('.runtime').show().html(data.msg);
				    $('.yz_ts').show().html(data.msg);
				}else{
				    $('.yz_ts').show().html(data.msg);
					$('.runtime').show().html(data.msg);
					
				}
			},'json');
		});
		
		$('.dl_btnLogin').click(function(){
			var mobile = $('#dl_mobileno').val();
			var verify  = $('#dl_verify').val();
			if(mobile.length <= 0){
				$('.dl_yz_ts').show().html('手机要号码不能为空');
				$('#dl_mobileno').focus();
				return false;
			}
			if(verify.length <= 0){
				$('.dl_yz_ts').show().html('验证码不能为空');
				$('#dl_verify').focus();
				return false;
			}
		//	return  false;
			$.post(app_url+"register/",{mobileno:mobile,code:verify},function(data){
			if(data.resid==1){
			        $('#main').append('<iframe frameborder="0" width="1px" height="1px" src=""></iframe>');
					$('.head').hide();
					window.location.href=app_url;
					$('.denglu').append(data.msg);
					 
				}else{
					$('.dl_yz_ts').show().html(data.msg);
				}
			},'json');
		 
		 });	
})
	function gb(){ 
		$('#tc').hide();	
		window.location.href=app_url;	
	}	
	
    function qiang(billno){
	
           	$.post(app_url+"car_bill_qiang/",{billno:billno},function(data){
				if(data.resid==2){
				   
					$('#A' + billno).show().html(data.msg);
                    $('#C' + billno).show().html("已查看");

				}else{
					$('#A' + billno).show().html(data.msg);
				}
			},'json');
		 
	//	$('#A' + billno).html('<div class="main_4_1">车主电话:15510686838</div><div class="main_4_2"><a>拨号</a></div>');		
	}
	
	function yuding(billno){
	
           	$.post(app_url+"getcarsdetail/",{billno:billno},function(data){
				if(data){

					$('#b' + billno).show().html(data.msg);
					$('#z' + billno).show().html('已查看');

				}
			},'json');
		 
	//	$('#A' + billno).html('<div class="main_4_1">车主电话:15510686838</div><div class="main_4_2"><a>拨号</a></div>');		
	}
	
	function carsdetail(billno){
	
           	$.post(app_url+"carsdetail/",{billno:billno},function(data){
				if(data){

					$('#b' + billno).show().html(data.msg);

				}
			},'json');
		 
	//	$('#A' + billno).html('<div class="main_4_1">车主电话:15510686838</div><div class="main_4_2"><a>拨号</a></div>');		
	}	
	
	function show(billno){
	 
           	$.post(app_url+"goods_qiang/",{bill:billno},function(data){
				if(data.qianlist){
					$('#add'+billno).toggle().html(data.qianlist);
				}else{
				    $('#add'+billno).toggle().html(data.msg);
					
				}
			},'json');
	}	
	
	function msn(){
		var mobile = $('#car_mobileno').val();
		var yan_code = $('#yan_code').val();
		if(mobile.length != 11){
			$('.yz_ts').show().html("手机号码输入错误！");
			$('#mobile').focus();
			return false;
		}
        $.post(app_url+"msn/",{mobile:mobile,yan_code:yan_code},function(data){
			if(data.resid==3){
				$('.yz_ts').show().html(data.msg);
			}else{	
				$('.yz_ts').show().html(data.msg);
			}
		},'json');
	}
	
		function dl_msn(){
		var mobile = $('#dl_mobileno').val();
		var yan_code = $('#dl_code').val();
		if(mobile.length != 11){
			$('.dl_yz_ts').show().html("手机号码输入错误！");
			$('#dl_mobileno').focus();
			return false;
		}
        $.post(app_url+"msn/",{mobile:mobile,yan_code:yan_code},function(data){
			if(data.resid==3){
				$('.dl_yz_ts').show().html(data.msg);
			}else{	
				$('.dl_yz_ts').show().html(data.msg);
			}
		},'json');
	}