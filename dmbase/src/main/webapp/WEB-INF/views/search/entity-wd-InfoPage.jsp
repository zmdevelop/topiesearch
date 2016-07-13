<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">

<body>
	<form class="form-horizontal" role="form">
		<div class="form-body">
			<h3 class="form-section">配置查看</h3>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">名称:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				</div>
				<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">依赖:</label>
						<div class="col-md-9">
							<p class="form-control-static">${parentName }</p> 
							<button type="button" class="btn-min default green" onclick="show('${entity.pid}');">查看</button>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
			<div class="col-md-4"></div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">数据源:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.datasource }</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions " style="text-align:center;">
				<button type="button" class="btn default btn-lg" id="close" onclick="modal.hide();">关闭</button>
				&nbsp;
		</div>
	</form>
</body>
<!-- END BODY -->
<script>
	function show(pid){
		modal.$body.load("./showInfo?id="+pid);
	}
</script>
</html>
