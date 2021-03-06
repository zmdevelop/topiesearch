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
			<h2 class="margin-bottom-20">配置查看</h2>
			<h3 class="form-section">${entity.entityName }</h3>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">主键字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">标题字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">内容字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">更新时间字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">来源字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">作者字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<h3 class="form-section">生成信息:</h3>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">查询sql:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">增量查询sql:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">增量sql:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
		</div>
		<div class="form-actions">
			<div class="row">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-offset-3 col-md-9">
							<button type="button" class="btn default" onclick="model.hide();">关闭</button>
						</div>
					</div>
				</div>
				<div class="col-md-6"></div>
			</div>
		</div>
	</form>
</body>
<!-- END BODY -->
</html>
