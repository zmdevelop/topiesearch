
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body>
	<div class="portlet-body form">
		<form novalidate="novalidate" id="channel_form" name="channel_form"
			action="./insert" method="post" enctype="multipart/form-data"
			class="form-horizontal">
			<div class="form-body" id="channel_form_body">
				<input id="id" name="id" class="form-control" type="hidden">
				<div data-row="0" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">名称</label>
							<div class="col-md-5">
							<div class="input-group">
								<input showicon="false" id="entityName" name="entityName"
									class="form-control input-xlarge" placeholder="" type="text">
											</div>
							</div>
						</div>
					</div>
				</div>
				<div data-row="1" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">数据源</label>
							<div class="col-md-9">
								<select aria-invalid="false" id="datasource" name="datasource" onchange="changeDataSource(this);"
									class="form-control input-xlarge"> </select>
							</div>
						</div>
					</div>
				</div>
				<div data-row="2" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">查询主表</label>
							<div class="col-md-5">
							<div class="input-group">
								<select aria-describedby="tableName-error" aria-invalid="false"
									aria-required="true" id="tableName" name="tableName" onchange="changeTable(this);"
									class="form-control input-xlarge tableName">
									</select>
									<span class="input-group-addon btn" onclick="addleftjoin(this);">
											添加关联表
									</span>
									<span
									class="help-block help-block-error" id="tableName-error"></span>
							</div></div>
						</div>
					</div>
				</div>
				<div data-row="3" class="row" >
					<div class="col-md-12" id="leftjointypes">
					
					</div>
				</div>
				<div data-row="4" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">主键</label>
							<div class="col-md-9">
								<select id="idFiled" name="idFiled"
									class="form-control input-xlarge columnName"> </select>
							</div>
						</div>
					</div>
				</div>
				<div data-row="5" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">标题字段</label>
							<div class="col-md-9">
								<select id="titleFiled" name="titleFiled"
									class="form-control input-xlarge columnName"> </select>
							</div>
						</div>
					</div>
				</div>
				<div data-row="6" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">url链接字段</label>
							<div class="col-md-9">
								<select id="urlFiled" name="urlFiled"
									class="form-control input-xlarge columnName"> </select>
							</div>
						</div>
					</div>
				</div>
				<div data-row="7" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">内容字段</label>
							<div class="col-md-9">
								<select id="contentFiled" name="contentFiled"
									class="form-control input-xlarge columnName"> </select>
							</div>
						</div>
					</div>
				</div>
				<div data-row="8" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">作者/来源字段</label>
							<div class="col-md-9">
								<select id="authorFiled" name="authorFiled"
									class="form-control input-xlarge columnName"> </select>
							</div>
						</div>
					</div>
				</div>
				<div data-row="9" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">所属栏目字段</label>
							<div class="col-md-9">
								<select id="channelFiled" name="channelFiled"
									class="form-control input-xlarge columnName"> 
							</div>
						</div>
					</div>
				</div>
				<div data-row="10" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">发布时间字段</label>
							<div class="col-md-9">
								<select id="publishtimeFiled" name="publishtimeFiled"
									class="form-control input-xlarge columnName"> 
							</div>
						</div>
					</div>
				</div>
				<div data-row="11" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">过滤条件</label>
							<div class="col-md-5">
							<div class="input-group">
								<input showicon="false" id="whereFiled" name="whereFiled"
									class="form-control input-xlarge" placeholder="" type="text">
											</div>
							</div>
						</div>
					</div>
				</div>
				<!-- <div data-row="11" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">索引sql</label>
							<div class="col-md-9">
								<textarea id="query" name="query"
									class="form-control input-xlarge" rows="3"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div data-row="12" class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-3">增量sql</label>
							<div class="col-md-9">
								<textarea id="deltaQuery" name="deltaQuery"
									class="form-control input-xlarge" rows="3"></textarea>
							</div>
						</div>
					</div>
				</div> -->
			</div>
			<div class="form-actions" style="text-align:center;">
				<!-- <button type="button" class="btn default btn-lg" role="reset">重置</button>
				&nbsp; -->
				<button type="button" class="btn blue btn-lg" id="sub">保存</button>
				&nbsp;
				<button type="button" class="btn default btn-lg" id="close" onclick="modal.hide();">关闭</button>
				&nbsp;
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">

	jQuery(document).ready(function() { 
		var datasourceele = $("#datasource");
		 $.ajax({
	            type: "POST",
	            dataType: "json",
	            url: "../dataSource/loadAll",
	            success: function (options) {
	            		 datasourceele.append('<option value=""> 请选择</option>');
		                $.each(options, function (i, option) {
		                    var opt = '<option value="'+option.value+'">'+option.text+'</option>'
		                    datasourceele.append(opt);
	                 });
	            }
	        });		
		$("#sub").bind("click",function(){
			var formData = $('#channel_form').serialize();
			$.ajax({
				 type: "POST",
                 data: formData,
                 dataType: "json",
                 url: './insert',
                 success: function (data) {
                	 if(data.status==1){
                		 modal.hide();
                		 grid.reload();
                	 }else{
                		 bootbox.alert(data.msg);
                	 }
				},
				error:function(){
					alert("请求错误");
				}
			})
		});
		
	});
	function addleftjoin(that){
		var selectValue = $("#datasource").val();
		var url = "../getTables?dataSourceName="+selectValue;
		//var url ="../getColumn?dataSourceName="+id+"&tableName="+tableName;
		var tableoptions='<option value=""> 请选择</option>';
		if(selectValue!=""){
	        $.ajax({
	            type: "POST",
	            dataType: "json",
	            url: url,
	            success: function (options) {
		                $.each(options, function (i, option) {
	                    var opt = '<option value="'+option.value+'">'+option.text+'</option>'
	                    tableoptions+=opt;
	               		 });
						$("#leftjointypes").append('<div class="form-group"><label class="control-label col-md-3">关联表</label><div class="col-md-2">'
								+'<select aria-invalid="false"  name="tableName" class="form-control input-small tableName" onchange="changeTable(this);">'
								+tableoptions+'</select>'
								+'</div><label class="control-label col-md-1">关联关系</label><div class="col-md-2">'
								+'<select aria-invalid="false" name="onFileds" class="form-control input-medium columnName"> '
								+'</select>'
								+'</div><label class="control-label col-md-1">=</label><div class="col-md-3">'
								+'<select aria-invalid="false"  name="onFileds" class="form-control input-medium columnName"> '
								+'</select>'
								+' </div></div>');
            	}
        	});
		}else{
			$("#leftjointypes").append('<div class="form-group"><label class="control-label col-md-3">关联表</label><div class="col-md-2">'
					+'<select aria-invalid="false"  name="tableName" class="form-control input-small tableName" onchange="changeTable(this);">'
					+tableoptions+'</select>'
					+'</div><label class="control-label col-md-1">关联关系</label><div class="col-md-2">'
					+'<select aria-invalid="false" name="joincolumn" class="form-control input-medium columnName"> '
					+'</select>'
					+'</div><label class="control-label col-md-1">=</label><div class="col-md-3">'
					+'<select aria-invalid="false"  name="joincolumn" class="form-control input-medium columnName"> '
					+'</select>'
					+' </div></div>');
		}
	}
	function changeTable(that){
		var tabelName = $("select.tableName");
		var selectValue = "";
		 $.each(tabelName,function(i,ele){
			 if($(ele).val()!=""){
				 selectValue += $(ele).val()+",";
			 }
		 });
		 selectValue =selectValue.substr(0,selectValue.length-1);
		var id = $("#datasource").val();
		var url ="../getColumn?dataSourceName="+id+"&tableName="+selectValue;
        var eles = $("select.columnName");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            success: function (options) {
            	 $.each(eles,function(i,ele){
                 	$(ele).html('<option value=""> 请选择</option>');
	                $.each(options, function (i, option) {
	                    /* var opt = $.tmpl(optionTmpl, {
	                        "value_": option.value,
	                        "text_": option.text,
	                        "selected": (option.selected ? "selected"
	                            : "")
	                    }); */
	                    var opt = '<option value="'+option.value+'">'+option.text+'</option>'
	                    //alert(opt);
	                    $(ele).append(opt);
	                });
                 });
            }
        });
	}
	function changeDataSource(that){
		var selectValue = $(that).val();
		var url = "../getTables?dataSourceName="+selectValue;
		//var url ="../getColumn?dataSourceName="+id+"&tableName="+tableName;
        var eles = $(".tableName");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            success: function (options) {
            	 $.each(eles,function(i,ele){
                 	$(ele).html('<option value=""> 请选择 </option>');
	                $.each(options, function (i, option) {
	                    /* var opt = $.tmpl(optionTmpl, {
	                        "value_": option.value,
	                        "text_": option.text,
	                        "selected": (option.selected ? "selected"
	                            : "")
	                    }); */
	                    var opt = '<option value="'+option.value+'">'+option.text+'</option>'
	                    //alert(opt);
	                    $(ele).append(opt);
	                });
                 });
            }
        });
	}
</script>
</html>
