<!DOCTYPE html>
						<html>
						<head>
						<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
						<meta http-equiv="X-UA-Compatible" content="IE=edge">
						<title>【蓝海】信息网</title>
						<link type="text/css" rel="stylesheet" href="${ipAddress}/css/base.css"/>
						<link type="text/css" rel="stylesheet" href="${ipAddress}/css/indexstyle.css"/>
						<link type="text/css" rel="stylesheet" href="${ipAddress}/css/publicstyle.css"/>
						<link type="text/css" rel="stylesheet" href="${ipAddress}/css/quicklink.css"/>
			<link type="text/css" rel="stylesheet" href="${ipAddress}/css/orbit.other.css"/>
						</head>
						
						<body>
						
						<!--topnav-->
						<div class="topnav fn-clear">
								 <div class="topnavwrap"><!--
											<div class="topnav-user fn-right">
													 <div class="log ">
																<ul>
																		 <li id="login_on"><a href="###">登录</a></li>
														 </ul>
																 <div class="loginbox" id="loginbox">
																		 <div>
																				<input type="text" value="请输入用户名" onfocus="if (value =='请输入用户名'){value =''}" onblur="if (value ==''){value='请输入用户名'}" />
																				<div class="blank_10"></div>
																				<input type="text" value="请输入密码" onfocus="if (value =='请输入密码'){value =''}" onblur="if (value ==''){value='请输入密码'}" />
																			 <button class="loginbutton ">立即登录</button>
																		 </div>
														 </div>
												</div>
									 </div > -->
											 <div class=" favoriter fn-left">
													 <div><a href="#">收藏我们</a></div>
											 </div> 
								 </div>
						</div>
						<!--header-->
						<div class="header fn-clear">
							 <div class="headerwrap">
									 <div class="logo"><img src="${ipAddress}/images/pic_logo.png"/></div>
									
												<div class="searchbox">
														<div class="searchform">
														<input type="text" value="请输入搜索关键词" onfocus="if (value =='请输入搜索关键'){value =''}" onblur="if (value ==''){value='请输入搜索关键名'}">
														</div> 
														 <a href="#" class="searchbutton ">搜索</a> </div> 
														 <div class="searchhot">
																 <p><span><a href="#">金银岛</a></span><span><a href="#">海洋气候</a></span><span><a href="#">三亚</a></span><span><a href="#">海洋地图</a></span><span><a href="#">三亚</a></span></p>
														 </div>
										 
										
							 </div>
						</div>
						<!--header end-->
						<!--nav-->
						<div class="nav fn-clear">
								 <div class="navmain">
										<div class="navshop"><a href="###">海洋商城</a></div>
										<div class="navbar">
										<ul>
							<li>
							<a href="${ipAddress}/html/lanhai/index.html">首页</a>
							</li>
								<@channelListDirective siteId=2 order=1>
									 <#list channels as channel>
										 <li>
								<a href="${channel.url}" >${channel.displayName}</a>
										 </li>
									 </#list>
								 </@channelListDirective>      
										</ul></div>
								</div>
						</div>
						<div class="blank_20"></div>
		<!--floor1-->
		<!--searchsite -->
 <div class="searchsite">
	<form class="search-form">
		<div class="searchsite-box">
				<div class="searchsite-main">
				 <h4>站内搜索</h4>
		 <select class="select-searchsite" name="entity">
		 <option value="cmsContent">新闻</option>
		 <option value="cmsNovel">小说</option>
		 <option value="cmsVideo">视频</option>
		 <option value="cmsAudio">音乐</option>
		 </select>
		 <select class="select-searchsite" name="days">
		 <option value="">时间不限</option>
		 <option value="7">一周</option>
		 <option value="30">一个月</option>
		 <option value="90">三个月</option></select>
		 <input size="" class="input-searchsite" type="text" name="text"/>
		 <a href="javascript:void(0);" class="btn-searchsite">搜索</a>
			 </div>
		</div>
		<div class="searchsite-tool">
			<div class="searchsite-sort">
			<label><input type="radio" name="sortby" value="publishDate_desc" /><span>按时间降序</span></label>
			<label><input type="radio" name="sortby" value="publishDate_asc"/><span>按时间升序</span></label>
			<label><input type="radio" name="sortby" value="sortby"/><span>按相关度</span></label>
			</div>
		</div>
		</form>
 </div>
 <!--searchsite end-->
 
 <!--list-->
 <div class="newschannel-floor fn-clear">
	 <div class="moretabs news-cont tline" >
									<div class="newslist">
											
											
									</div>
									<div class="page_wrap">
											<div class="page"> <a href="#" class="page_last">上一页</a> <a href="#" class="page_current">1</a> <a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a> <a href="#" class="page_next">下一页</a> </div>
									</div>
									<div class=" blank_20"></div>
						 
		</div>
		<!--点击排行-->
		<div class="leftlistwrap">
			 <div class="hotlist">
						<h4><span>搜索热点排行</span></h4>
						<ul >
							 <li><em class="top">1</em><a href="###">俄军空袭</a></li>
							 <li><em class="top">2</em><a href="###">春运</a></li>
							 <li><em class="top">3</em><a href="###">北京艺校学生吸毒 300余人接受路秒</a></li>
							 <li><em>4</em><a href="###">外媒称美军12月或再次巡航南</a></li>
							 <li><em>5</em><a href="###">北京提严控人口规模:2300万为"天花板</a></li>
							 <li><em>6</em><a href="###">律师申请专车新政信息公开被</a></li>
								<li><em>7</em><a href="###">外媒称美军12月或再次巡航南</a></li>
							 <li><em>8</em><a href="###">北京提严控人口规模:2300万为"天花板</a></li>
							 <li><em>9</em><a href="###">律师申请专车新政信息公开被</a></li>
							 <li><em>10</em><a href="###">律师申请专车新政信息公开被</a></li>
						</ul>
			 </div>
			 </div>
			</div> 
		<div class="  blank_20"></div>
						<!--!footer-->
						<div class="footer">
							<div class="footerwrap">
								<div class="linklist">
										<a href="###">关于我们</a>  |  <a href="###">联系我们</a>   |<a href="###">  友情链接 </a> |<a href="###">  客服热线：400-898-9898</a> </div>
										<div class="copyright">
								<p><span>海洋信息网  版权声明 </span>  <span>京ICP备093817344号</span>  <span>京ICP证B2-21366号</span>   <span>  北京市公安局海淀分局备案编号：21341345699 </span>       </p>
						</div>
							</div>
						</div>
						<!--!footer end-->
						
						<!--右侧贴边导航quick_links.js控制-->
						<div class="mui-mbar-tabs">
							<div class="quick_link_mian">
								<div class="quick_links_panel">
									<div id="quick_links" class="quick_links">
										<li>
											<a href="#" class="my_qlinks"><i class="setting"></i></a>
											<div class="ibar_login_box status_login">
												<div class="avatar_box">
													<p class="avatar_imgbox"><img src="${ipAddress}/images/no-img_mid_.jpg" /></p>
													<ul class="user_info">
														<li>用户名：sl19931003</li>
														<li>级&nbsp;别：普通会员</li>
													</ul>
												</div>
												<div class="login_btnbox">
													<a href="#" class="login_order">我的订单</a>
													<a href="#" class="login_favorite">我的收藏</a>
												</div>
												<i class="icon_arrow_white"></i>
											</div>
										</li>
										<li>
											<a href="#" class="history_list"><i class="view"></i></a>
											<div class="mp_tooltip" style=" visibility:hidden;">我的消息<i class="icon_arrow_right_black"></i></div>
										</li>
										<li>
											<a href="#" class="mpbtn_histroy"><i class="zuji"></i></a>
											<div class="mp_tooltip">我的足迹<i class="icon_arrow_right_black"></i></div>
										</li>
										<li>
											<a href="#" class="mpbtn_wdsc"><i class="wdsc"></i></a>
											<div class="mp_tooltip">我的收藏<i class="icon_arrow_right_black"></i></div>
										</li>
										
									</div>
									<div class="quick_toggle">
										<li>
											<a href="#"><i class="kfzx"></i></a>
											<div class="mp_tooltip">客服中心<i class="icon_arrow_right_black"></i></div>
										</li>
										<li>
											<a href="#none"><i class="mpbtn_qrcode"></i></a>
											<div class="mp_qrcode" style="display:none;"><img src="${ipAddress}/images/weixin_code_145.png" width="148" height="175" /><i class="icon_arrow_white"></i></div>
										</li>
										<li><a href="#top" class="return_top"><i class="top"></i></a></li>
									</div>
								</div>
								<div id="quick_links_pop" class="quick_links_pop hide"></div>
							</div>
						</div>
						
						
						<!--[if lte IE 8]>
						<script src="${ipAddress}/js/ieBetter.js"></script>
						<![endif]-->
						
						</body>
						<!--                       Javascripts 注意不要改变顺序                      -->
						<script type="text/javascript"  src="${ipAddress}/js/jquery-1.11.3.min.js"></script>
						<script type="text/javascript" src="${ipAddress}/js/jquery-migrate-1.1.1.min.js"></script>
			<!-- 焦点图 -->
			<script type="text/javascript" src="${ipAddress}/js/jquery.orbit.min.js"></script>
			<script type="text/javascript">
			$(window).load(function() {
				$('#bookscroll').orbit({
					bullets : true,
					startClockOnMouseOut : true,
					captionAnimation : 'slideOpen'
				});
		 $(".btn-searchsite").on("click", function () {			
			var value = $(".searchsite-main .input-searchsite").val();
			if(value.length==0)return false;
			var data = $(".search-form").serialize();
		var url = "http://localhost/dmbase/searchConfig/search";
			$.ajax({  
				url:url,
				dataType:'json',  
				data:data,
				success:function(result) { 
					var htmlstr='';
					if(result.status==0){
						return ;
					}
					var list = result.list;
					for(var i=0;i<list.length;i++){
						var item = list[i];
						 htmlstr+='<div class="newslist-item">'
							+'<h2><em class="list-descor">'+item.channel
							+'</em><a href="'+item.url+'">'+item.title+'</a></h2>'
							+item.actor
						 +' <span>'+item.content+'</span>'
							+'<p><em class="interact"><a href="###">评论</a><i>|</i><a href="###">分享</a></em><em class="time">'+
							item.publishDate+'</em></p>'
						+'</div>';
							}
					
					$(".newslist").html(htmlstr);
					
				},  
				timeout:3000  
			 });
		});
			});
		</script>
						<script type="text/javascript" src="${ipAddress}/js/quick_links.js"></script>
			
						<!-- 右侧栏 
						<script type="text/javascript">
							$(".quick_links_panel li").mouseenter(function(){
								$(this).children(".mp_tooltip").animate({left:-92,queue:true});
								$(this).children(".mp_tooltip").css("visibility","visible");
								$(this).children(".ibar_login_box").css("display","block");
							});
							$(".quick_links_panel li").mouseleave(function(){
								$(this).children(".mp_tooltip").css("visibility","hidden");
								$(this).children(".mp_tooltip").animate({left:-121,queue:true});
								$(this).children(".ibar_login_box").css("display","none");
							});
							$(".quick_toggle li").mouseover(function(){
								$(this).children(".mp_qrcode").show();
							});
							$(".quick_toggle li").mouseleave(function(){
								$(this).children(".mp_qrcode").hide();
							});
							</script>-->
					</script>
						</html>
		
	 
	
 