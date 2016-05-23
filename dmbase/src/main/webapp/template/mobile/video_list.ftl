<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0,minimum-scale=1.0, user-scalabele=no"/><!-- 手机屏幕网页初始大小占屏幕面积100% -->
<title>视频</title>
<!--css-->
<link type="text/css" rel="stylesheet" href="${htmlFolder}${htmlMobileFolder}/css/global.css"><!--全局样式-->
<link type="text/css" rel="stylesheet" href="${htmlFolder}${htmlMobileFolder}/css/main.css">
<!--javaScript-->
<script src="${htmlFolder}${htmlMobileFolder}/js/retina.js"></script><!--适应2倍屏-->

<style>
  .imgList{
    width:414px;
    
  }
</style>
</head>

<body>

<!--头部开始-->
<header id="header" class="ub ub-pj">
    <@channelDirective channelId=own><div class="ub ub-ac ub-pc f20 cWh mg-l2"><a href="${site.mUrl}"><span style="color:white">首页</span></a><div class="jtRit mg-l2 mg-r2 ub ub-ac ub-pc"></div><span>${channel.displayName}</span></div></@channelDirective>
    <span class="right"></span>
</header>
<#include "/template/mobile/daohang.ftl" />
<!-- 头部结束 -->

<!--MainBody-->
<!--Card News-->

<div class="ub ub-ver">
    <div class="ub lineB-blu1 ub-pj mg-t2">
        <@channelDirective channelId=own><div class="ub zwywTit">${channel.displayName}</div></@channelDirective>
       
    </div>
    <div>
        <div class="ub mg-a2 mg-b0 pd-b2 ub-ver">
            
              <@videoListDirective channelId=own pageSize=6 pageNum=pageNum titleLeft=25 order=1>
              <div class="ub mg-b2"> 
              <#list cmsVideos as content> 
              <#if content_index &lt; 2>
             <div class="ub ub-f1 videoWrap mg-r1 bgGra1 ub-ver link">
                  <a href="${content.mUrl}"><img src="${content.imageUrl!}" class="imgList"></a>
                  <div class="ub ub-f1 f16 pd-a1"><a href="${content.mUrl}">${content.name!}</a></div>
                  <div class="v-cont ub ub-f1 ub-ac f14 mg-b2">
                    <div class="ub mg-r2"><img src="${htmlFolder}${htmlMobileFolder}/img/ico-video.png"><div class="ub">${content.count!}</div></div>
                      <div class="ub"><img src="${htmlFolder}${htmlMobileFolder}/img/ico-message.png"><div class="ub">${content.commentCount!}</div></div>
                  </div>
              </div>
              </#if>
              </#list>
               </div>
               <div class="ub mg-b2"> 
              <#list cmsVideos as content> 
              <#if content_index &gt; 1 &&  content_index &lt; 4>
             <div class="ub ub-f1 videoWrap mg-r1 bgGra1 ub-ver link">
                  <a href="${content.mUrl}"><img src="${content.imageUrl!}" class="imgList"></a>
                  <div class="ub ub-f1 f16 pd-a1"><a href="${content.mUrl}">${content.name!}</a></div>
                  <div class="v-cont ub ub-f1 ub-ac f14 mg-b2">
                    <div class="ub mg-r2"><img src="${htmlFolder}${htmlMobileFolder}/img/ico-video.png"><div class="ub">${content.count!}</div></div>
                      <div class="ub"><img src="${htmlFolder}${htmlMobileFolder}/img/ico-message.png"><div class="ub">${content.commentCount!}</div></div>
                  </div>
              </div>
              </#if>
              </#list>
               </div>
                <div class="ub mg-b2"> 
              <#list cmsVideos as content> 
              <#if content_index &gt; 3>
             <div class="ub ub-f1 videoWrap mg-r1 bgGra1 ub-ver link">
                   <a href="${content.mUrl}"><img src="${content.imageUrl!}" class="imgList"></a>
                  <div class="ub ub-f1 f16 pd-a1"><a href="${content.mUrl}">${content.name!}</a></div>
                  <div class="v-cont ub ub-f1 ub-ac f14 mg-b2">
                    <div class="ub mg-r2"><img src="${htmlFolder}${htmlMobileFolder}/img/ico-video.png"><div class="ub">${content.count!}</div></div>
                      <div class="ub"><img src="${htmlFolder}${htmlMobileFolder}/img/ico-message.png"><div class="ub">${content.commentCount!}</div></div>
                  </div>
              </div>
              </#if>
              </#list>
               </div>
              
        </div>
    </div>
     
</div>
<div class="mPage">
            ${paginationMobile}
</div>
</@videoListDirective>   

<!--End-->

<!--End MainBody-->

</body>
<#include "/template/mobile/foot.ftl" />
</html>
