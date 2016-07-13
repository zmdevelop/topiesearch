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
<!--<![endif]-->
<!-- BEGIN HEAD -->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>TOPIESEARCH | 数据源管理</title>
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
										class="caption-subject theme-font bold uppercase">数据源列表</span>
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
			cloums : [ 
			{
				title : "名称",
				field : "name",
				width : "15%"
			}, {
				title : "类型",
				field : "type",
				width : "15%"
			},{
				title : "地址",
				field : "address",
				width : "20%"
			},{
				title : "数据库",
				field : "database",
				width : "20%"
			} ],
			actionCloumText : "操作",//操作列文本
			actionCloumWidth : "20%",
			actionCloums : [
					  {
						text : "编辑",
						cls : "green btn-sm",
						handle : function(index, data) {
							 modal = $.dmModal({
								id : "siteForm",
								title : "编辑",
								distroy : true
							});
							modal.show();
							var form = modal.$body.dmForm(formOpts);
							form.loadRemote("./load?id=" + data.id);
						} 
					},{
						text : "删除",
						cls : "red btn-sm",
						handle : function(index, data) {
							deleteItem(data.id);
						}
					}
					],
			tools : [ {
				text : "添加",
				cls : "btn green btn-sm",//按钮样式
				handle : function(grid) {
					 modal = $.dmModal({
						id : "siteForm",
						title : "编辑",
						distroy : true
					});
					modal.show();
					var form = modal.$body.dmForm(formOpts);
				}
			} ] ,
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
					name : "type",
					placeholder : "输入要搜索的名称",
					item:[{text:"mySql",value:"MYSQL"}]
				} ]
			} */
		};
			var forbidden={
				type:'radioGroup',
				name:'forbidden',
				id:'forbidden',
				label:'状态',
				cls:'input-large',
				items : [ {
							value : '0',
							text : '启用',
							checked:true
						},{
							value:'1',
							text:'禁用'
						} 
					],
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
				}, {
					type : 'text',//类型
					name : 'name',//name
					id : 'name',//id
					label : '名称',//左边label
					cls : 'input-large',
					rule : {
						required : true
					},
					message : {
						required : "必须填写"
					}
				}, {
					type : 'text',//类型
					name : 'user',//name
					id : 'user',//id
					label : '用户名',//左边label
					cls : 'input-large',
					rule : {
						required : true
					},
					message : {
						required : "必须填写"
					}
				}, {
					type : 'text',//类型
					name : 'password',//name
					id : 'password',//id
					label : '密码',//左边label
					cls : 'input-large',
					rule : {
						required : true
					},
					message : {
						required : "必须填写"
					}
				},{
					type : 'text',//类型
					name : 'address',//name
					id : 'address',//id
					label : '数据库IP',//左边label
					cls : 'input-large',
					rule : {
						required : true
					},
					message : {
						required : "请输入"
					}	
			},{
				type : 'text',//类型
				name : 'database',//name
				id : 'database',//id
				label : '数据库',//左边label
				cls : 'input-large',
				rule : {
					required : true
				},
				message : {
					required : "请输入"
				}	
		},{
				type : 'select',
				name : 'type',
				id : 'type',
				label : '类型',
				cls : 'input-large',
				items : [ {
					value : '',
					text : ''
				}, {
					value : 'MYSQL',
					text : 'mySql'
				},{
					value:'ORACLE',
					text:'oracle'
				},{
					value:'SQLSERVER',
					text:'sqlServer'
				},{
					value:'URLWDOC',
					text:'附件文档'
				}/* ,{
					value:'DOC',
					text:'本地word'
				},{
					value:'PDF',
					text:'本地pdf'
				} */  ]
			//,	itemsUrl : "../template/selects?templateType=1&siteId="+currentSiteId
			} ]
			};
		function deleteItem(id) {
	        bootbox.confirm("确定删除吗？", function (result) {
	            if (result) {
	                $.ajax({
	                    type: "POST",
	                    data: "id=" + id,
	                    dataType: "json",
	                    url: "./delete",
	                    success: function(data){
	                    	if (data.status == 1) {
	                            grid.reload();
	                        }else{
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
		jQuery(document).ready(function() {
			grid = $("#grid").dmGrid(options);
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
