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
			<h3 class="form-section">配置信息</h3>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">名称:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.entityName } </p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">数据源:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.datasource }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">主键字段:</label>
						<div class="col-md-9">
							<p class="form-control-static"> ${entity.idFiled }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">标题字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.titleFiled }</p>
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
							<p class="form-control-static">${entity.contentFiled }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">发布时间字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.publishtimeFiled }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">栏目字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.channelFiled }</p>
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">作者字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.authorFiled }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">查询表字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.tableName }</p>whereFiled
						</div>
					</div>
				</div>
				<!--/span-->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">条件字段字段:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.whereFiled }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<h3 class="form-section">生成信息:</h3>
			<div class="row">
				<div class="col-md-9">
					<div class="form-group">
						<label class="control-label col-md-3">查询sql:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.query }</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-9">
					<div class="form-group">
						<label class="control-label col-md-3">增量查询sql:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.deltaQuery }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<div class="row">
				<div class="col-md-9">
					<div class="form-group">
						<label class="control-label col-md-3">增量sql:</label>
						<div class="col-md-9">
							<p class="form-control-static">${entity.deltaImportQuery }</p>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
		</div>
		
			
		<div class="form-actions " style="text-align:center;">
				<button type="button" class="btn default btn-lg" id="close" onclick="modal.hide();">关闭</button>
				&nbsp;
		</div>
	</form>
</body>
<!-- END BODY -->
</html>
