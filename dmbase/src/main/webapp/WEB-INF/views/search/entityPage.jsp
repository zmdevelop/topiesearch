<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String iport = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	String basePath = iport + path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>TOPIESEARCH | 查询管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>assets/global/plugins/bootstrap-select/bootstrap-select.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>assets/global/plugins/select2/select2.css" />
<%@include file="../includejsps/style.jsp"%>
<%@include file="../includejsps/plugin-style.jsp"%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-menu-fixed" class to set the mega menu fixed  -->
<!-- DOC: Apply "page-header-top-fixed" class to set the top menu fixed  -->
<body>
	<!-- BEGIN HEADER -->

	<%@include file="../includejsps/head.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN PAGE CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE HEAD -->
		<%-- <div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>频道管理</h1>
				</div>
				<!-- END PAGE TITLE -->
				<!-- BEGIN PAGE TOOLBAR -->
				<%@include file="../../includejsps/toolbar.jsp"%>
				<!-- END PAGE TOOLBAR -->
			</div>
		</div>  --%>
		<!-- END PAGE HEAD -->
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="row margin-top-10">


					<!-- BEGIN PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-bar-chart theme-font hide"></i> <span
									class="caption-subject theme-font bold uppercase">entity列表</span>
								<span class="caption-helper"></span>
							</div>
						</div>
						<div class="portlet-body" id="grid"></div>
					</div>
					<!-- END PORTLET-->
				</div>
				<!-- END PAGE CONTENT INNER -->
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<!-- END PAGE CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@include file="../includejsps/foot.jsp"%>
	<!-- END FOOTER-->
	<!-- BEGIN JAVASCRIPTS-->
	<%@include file="../includejsps/js.jsp"%>
	<%@include file="../includejsps/plugin-js.jsp"%>
	<script type="text/javascript"
		src="<%=basePath%>assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>assets/global/plugins/select2/select2.min.js"></script>
	<script type="text/javascript">
	
    var root = "<%=basePath%>";
		var grid;
		var model;
		var options = {
			url : "./list", // ajax地址
			pageNum : 1,//当前页码
			pageSize : 5,//每页显示条数
			idFiled : "id",//id域指定
			showCheckbox : true,//是否显示checkbox
			checkboxWidth : "3%",
			showIndexNum : false,
			indexNumWidth : "5%",
			pageSelect : [ 5, 15, 30, 50 ],
			cloums : [ {
				title : "名称",
				field : "entityName",
				width : "20%"
			}, {
				title : "数据源",
				field : "datasource",
				width : "20%"
			}, {
				title : "数据表",
				field : "tableName",
				width : "30%"
			} ],
			actionCloumText : "操作",//操作列文本
			actionCloumWidth : "10%",
			actionCloums : [ {
				text : "编辑",
				cls : "green btn-sm",
				handle : function(index, data) {
					modal = $.dmModal({
						id : "siteForm",
						title : "编辑",
						distroy : true
					});
					modal.show();
					if(data.deltaQuery=="1"){
						var form = modal.$body.dmForm(form_file_Opts);
						form.loadRemote("./load?id=" + data.id);
					}else{
						var form = modal.$body.dmForm(formOpts);
						form.loadRemote("./load?id=" + data.id);
					}
				}
			}, {
				text : "删除",
				cls : "red btn-sm",
				handle : function(index, data) {
					deleteItem(data.id);
				}
			} ],
			tools : [ {
				text : "添加数据库类型",
				cls : "btn green btn-sm",//按钮样式
				handle : function(grid) {
					modal = $.dmModal({
						id : "siteForm",
						title : "添加",
						distroy : true
					});
					modal.show();
					var form = modal.$body.dmForm(formOpts);
				}
			}, {
				text : "添加附件类型",
				cls : "btn green btn-sm",//按钮样式
				handle : function(grid) {
					modal = $.dmModal({
						id : "siteForm",
						title : "添加",
						distroy : true
					});
					modal.show();
					var form = modal.$body.dmForm(form_file_Opts);
					form.loadLocal({deltaQuery:'1'})
				}
			},{
				text : "构建",
				cls : "btn green btn-sm",//按钮样式
				handle : function(grid) {
					build();
				}
			} ],
		/*search : {
			rowEleNum : 4,
			//搜索栏元素
			items : [ {
				type : "text",
				label : "名称",
				name : "name",
				placeholder : "输入要搜索的名称"
			},{
				type : "select",
				label : "类型",
				name : "datasource",
				placeholder : "输入要搜索的名称",
				item:[{text:"mySql",value:"MYSQL"}]
			} ]
		} */
		};
		var forbidden = {
			type : 'radioGroup',
			name : 'forbidden',
			id : 'forbidden',
			label : '状态',
			cls : 'input-xlarge',
			items : [ {
				value : '0',
				text : '启用',
				checked : true
			}, {
				value : '1',
				text : '禁用'
			} ],
			rule : {
				required : true
			},
			message : {
				required : "请选择模板启用状态"
			}
		};
		var formOpts = {
			id : "channel_form",//表单id
			name : "channel_form",//表单名
			method : "post",//表单method
			action : "./insert",//表单action
			ajaxSubmit : true,//是否使用ajax提交表单
			labelInline : true,
			rowEleNum : 1,
			beforeSubmit : function() {

			},
			ajaxSuccess : function() {
				modal.hide();
				grid.reload();
			},
			submitText : "保存",//保存按钮的文本
			showReset : true,//是否显示重置按钮
			resetText : "重置",//重置按钮文本
			isValidate : true,//开启验证
			buttons : [ {
				type : 'button',
				text : '关闭',
				handle : function() {
					modal.hide();
				}
			} ],
			buttonsAlign : "center",
			//表单元素
			items : [ {
				type : 'hidden',
				name : 'id',
				id : 'id'
			}, {
				type : 'text',//类型
				name : 'entityName',//name
				id : 'entityName',//id
				label : '名称',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "必须填写"
				}
			}, {
				type : 'text',//类型
				name : 'tableName',//name
				id : 'tableName',//id
				label : '数据表',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "请输入"
				}
			}, {
				type : 'select',
				name : 'datasource',
				id : '数据源',
				label : '类型',
				cls : 'input-xlarge',
				items : [],
				itemsUrl : "../dataSource/loadAll"
			}, {
				type : 'text',//类型
				name : 'idFiled',//name
				id : 'idFiled',//id
				label : '主键',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "请输入"
				}
			}, {
				type : 'text',//类型
				name : 'titleFiled',//name
				id : 'titleFiled',//id
				label : '标题字段',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "必须填写"
				}
			},  {
				type : 'text',//类型
				name : 'urlFiled',//name
				id : 'urlFiled',//id
				label : 'url链接字段',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "必须填写"
				}
			},  {
				type : 'text',//类型
				name : 'contentFiled',//name
				id : 'contentFiled',//id
				label : '内容字段',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "必须填写"
				}
			},   {
				type : 'text',//类型
				name : 'authorFiled',//name
				id : 'authorFiled',//id
				label : '作者/来源字段',//左边label
				cls : 'input-xlarge'
			},  {
				type : 'text',//类型
				name : 'channelFiled',//name
				id : 'channelFiled',//id
				label : '所属栏目字段',//左边label
				cls : 'input-xlarge'
			},  {
				type : 'text',//类型
				name : 'publishtimeFiled',//name
				id : 'publishtimeFiled',//id
				label : '发布时间字段',//左边label
				cls : 'input-xlarge'
			}, /*  {
				type : 'text',//类型
				name : 'selectFileds',//name
				id : 'selectFileds',//id
				label : '其他字段',//左边label
				cls : 'input-xlarge'
			}, */  {
				type : 'textarea',//类型
				name : 'query',//name
				id : 'query',//id
				label : '索引sql',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "请输入"
				}
			}, {
				type : 'textarea',//类型
				name : 'deltaQuery',//name
				id : 'deltaQuery',//id
				label : '增量sql',//左边label
				cls : 'input-xlarge',
				rule : {
					required : true
				},
				message : {
					required : "请输入"
				}
			} ]
		};
		var form_file_Opts = {
				id : "channel_form",//表单id
				name : "channel_form",//表单名
				method : "post",//表单method
				action : "./insert",//表单action
				ajaxSubmit : true,//是否使用ajax提交表单
				labelInline : true,
				rowEleNum : 1,
				beforeSubmit : function() {

				},
				ajaxSuccess : function() {
					modal.hide();
					grid.reload();
					channelTree.reAsyncChildNodes(null, "refresh");
				},
				submitText : "保存",//保存按钮的文本
				showReset : true,//是否显示重置按钮
				resetText : "重置",//重置按钮文本
				isValidate : true,//开启验证
				buttons : [ {
					type : 'button',
					text : '关闭',
					handle : function() {
						modal.hide();
						channelTree.reAsyncChildNodes(null, "refresh");
					}
				} ],
				buttonsAlign : "center",
				//表单元素
				items : [ {
					type : 'hidden',
					name : 'id',
					id : 'id'
				},{
					type : 'hidden',
					name : 'deltaQuery',
					id : 'deltaQuery'
				}, {
					type : 'text',//类型
					name : 'entityName',//name
					id : 'entityName',//id
					label : '名称',//左边label
					cls : 'input-xlarge',
					rule : {
						required : true
					},
					message : {
						required : "必须填写"
					}
				}, {
					type : 'text',//类型
					name : 'tableName',//name
					id : 'tableName',//id
					label : '数据表',//左边label
					cls : 'input-xlarge',
					rule : {
						required : true
					},
					message : {
						required : "请输入"
					}
				}, {
					type : 'select',
					name : 'pid',
					id : 'pid',
					label : '依赖entity',
					cls : 'input-xlarge',
					items : [],
					itemsUrl : "./loadAll",
					rule : {
						required : true
					},
					message : {
						required : "必须填写"
					}
				}, {
					type : 'select',
					name : 'datasource',
					id : 'datasource',
					label : '数据源',
					cls : 'input-xlarge',
					items : [],
					itemsUrl : "../dataSource/loadAll",
						rule : {
							required : true
						},
						message : {
							required : "必须填写"
						}
				},  {
					type : 'text',//类型
					name : 'urlFiled',//name
					id : 'urlFiled',//id
					label : 'url链接字段',//左边label
					cls : 'input-xlarge',
					rule : {
						required : true
					},
					message : {
						required : "必须填写"
					}
				}/* ,  {
					type : 'textarea',//类型
					name : 'query',//name
					id : 'query',//id
					label : '索引sql',//左边label
					cls : 'input-xlarge',
					rule : {
						required : true
					},
					message : {
						required : "请输入"
					}
				}, {
					type : 'textarea',//类型
					name : 'deltaQuery',//name
					id : 'deltaQuery',//id
					label : '增量sql',//左边label
					cls : 'input-xlarge',
					rule : {
						required : true
					},
					message : {
						required : "请输入"
					}
				} */ ]
			};
		function deleteItem(id) {
			bootbox.confirm("确定删除吗？", function(result) {
				if (result) {
					$.ajax({
						type : "POST",
						data : "id=" + id,
						dataType : "json",
						url : "./delete",
						success : function(data) {
							if (data.status == 1) {
								grid.reload();
							} else {
								bootbox.alert(data.msg);
							}
						}
					});
				}
			});
		}
		function build() {
			bootbox.confirm("确定构建吗？", function(result) {
				if (result) {
					$.ajax({
						type : "POST",
						dataType : "json",
						url : "../searchConfig/build",
						success : function(data) {
							if (data.status == 1) {
								bootbox.alert('构建成功!');
							} else {
								bootbox.alert(data.msg);
							}
						}
					});
				}
			});
		}
		function deleteItems(ids) {
			if (ids.length > 0) {
				deleteItem(ids);
			} else {
				bootbox.alert("请选择要删除的选项！");
			}
		}
		var solrUrl = '<%=iport%>' + '/solr';
		function reloadSolrConfig() {
			$.ajax({
				url : solrUrl + '/cms_core/dataimport?command=reload-config',
				dataType : 'xml',
				context : $(this),
				beforeSend : function(xhr, settings) {

				},
				success : function(response, text_status, xhr) {
					bootbox.alert("加载成功");
				},
				error : function(xhr, text_status, error_thrown) {
					bootbox.alert("加载失败");
				},
				complete : function(xhr, text_status) {
					this.removeClass('loader');

					dataimport_fetch_config();
				}
			});
			return false;
		}
		jQuery(document).ready(function() {
			grid = $("#grid").dmGrid(options);
		});
		/* <div class="form-group">
		<label class="control-label col-md-3">Tags Support List</label>
		<div class="col-md-9">
			<div id="s2id_autogen61"
				class="select2-container select2-container-multi form-control select2_sample3">
				<ul class="select2-choices">
					<li class="select2-search-choice">
						<div>red</div>
						<a href="#" class="select2-search-choice-close" tabindex="-1"></a>
					</li>
					<li class="select2-search-choice">
						<div>blue</div>
						<a href="#" class="select2-search-choice-close" tabindex="-1"></a>
					</li>
					<li class="select2-search-field">
						<label for="s2id_autogen62" class="select2-offscreen"></label>
						<input aria-activedescendant="select2-result-label-502"
							placeholder="" style="width: 20px;" id="s2id_autogen62"
							autocomplete="off" autocorrect="off" autocapitalize="off"
							spellcheck="false" class="select2-input" type="text">
					</li>
				</ul>
			</div>
			<input tabindex="-1" class="form-control select2_sample3 select2-offscreen"
				value="red,blue" type="hidden">
		</div>
		</div> */
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
