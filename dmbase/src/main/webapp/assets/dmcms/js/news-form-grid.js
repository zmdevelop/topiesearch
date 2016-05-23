var root = '<%=basePath%>'
var grid;
var channelTree;
var currentSiteId;
var currentChannelId;
var currentChannelType;

var channelSetting = {
	view : {
		showIcon : false,
		selectedMulti : false
	},
	edit : {
		enable : false,
		showRemoveBtn : false,
		showRenameBtn : false
	},
	check : {
		enable : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	async : {
		enable : true,
		dataType : "json",
		url : "",
		autoParam : [ "id", "name", "pId" ]
	},
	callback : {
		beforeAsync : function(treeId, treeNode) {
			if (typeof (currentSiteId) == "undefined") {
				return false;
			} else {
				return true;
			}
		},
		onAsyncSuccess : function(event, treeId, treeNode, msg) {
			if (msg.length == 0) {
				Metronic.alert({
					message : "该频道下暂无频道！",
					type : "warning",
					container : "#channel_tree_div",
					place : "prepend",
					close : false,
					closeInSeconds : 5
				});
			}
			channelTree.expandAll(true);
		},
		onClick : function(event, treeId, treeNode) {
			currentChannelId = treeNode.id;
			if (currentChannelType == treeNode.type) {
				if (currentChannelType == '0') {
					grid.reload({
						url : "./list?channelId=" + currentChannelId
					});
				} else if (currentChannelType == '5') {
					grid.reload({
						url : "../video/list?channelId=" + currentChannelId
					});
				} else if (currentChannelType == '6') {
					grid.reload({
						url : "../audio/list?channelId=" + currentChannelId
					}); 
				}
				else if (currentChannelType == '7') {
					grid.reload({
						url : "../novel/list?channelId=" + currentChannelId
					});
				}
			} else {
				currentChannelType = treeNode.type;
				if (currentChannelType == '0') {
					$("#content_grid").html("");
					options.url = "./list?channelId=" + currentChannelId;
					grid = $("#content_grid").dmGrid(options);
				} else if (currentChannelType == '5') {
					$("#content_grid").html("");
					videoOptions.url = "../video/list?channelId="
							+ currentChannelId;
					grid = $("#content_grid").dmGrid(videoOptions);
				} else if (currentChannelType == '6') {
					$("#content_grid").html("");
					audioOptions.url = "../audio/list?channelId="
							+ currentChannelId;
					grid = $("#content_grid").dmGrid(audioOptions);
				} 
				else if (currentChannelType == '7') {
					$("#content_grid").html("");
					novelOptions.url = "../novel/list?channelId="
							+ currentChannelId;
					grid = $("#content_grid").dmGrid(novelOptions);
				}
			}
		}
	}
};
function initSelect2Site() {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "../site/selectOptions",
		success : function(data) {
			if (data.length == 0) {
				Metronic.alert({
					message : "<a href='../site/page'>请先新建频道！</a>",
					type : "warning",
					container : "#channel_tree_div",
					place : "prepend",
					close : true
				});
			}
			$.each(data, function(i, o) {
				var option = $("<option></option>");
				option.text(o.text);
				option.attr("value", o.value);
				$("#select2_site").append(option);
			});
			if (data.length > 0) {
				refreshSite();
			}
			$("#select2_site").change(function() {
				refreshSite();
			});
			$("#select2_site").select2({
				allowClear : true
			});
		}
	});
}
function refreshSite() {
	if (typeof (channelTree) == "undefined") {
		$.fn.zTree.init($("#channel_tree"), channelSetting, "");
		channelTree = $.fn.zTree.getZTreeObj("channel_tree");
	}
	currentSiteId = $("#select2_site").val();
	channelTree.setting.async.url = "../channel/tree?siteId=" + currentSiteId;
	channelTree.reAsyncChildNodes(null, "refresh");
}

/** **********普通新闻表格选项*************** */
var options = {
	url : "./list", // ajax地址
	pageNum : 1,// 当前页码
	pageSize : 5,// 每页显示条数
	idFiled : "id",// id域指定
	showCheckbox : true,// 是否显示checkbox
	checkboxWidth : "3%",
	showIndexNum : true,
	indexNumWidth : "5%",
	pageSelect : [ 2, 15, 30, 50 ],
	cloums : [ {
		title : "标题",
		field : "title",
		sort : true
	}, {
		title : "来源",
		field : "origin",
		sort : true
	}, {
		title : "状态",
		field : "status",
		format : function(i, c) {
			if (c.status == "0")
				return "新稿";
			if (c.status == "1")
				return "待审核";
			if (c.status == "2")
				return "已发布";
			if (c.status == "3")
				return "未通过";
			return "--";
		}
	} ],
	actionCloumText : "操作",// 操作列文本
	actionCloumWidth : "30%",
	actionCloums : [ {
		text : "预览",
		cls : "green btn-sm",
		icon : "fa fa-search",
		handle : function(index, data) {
			window.open(data.url);
		}
	}, {

		text : "编辑",
		cls : "green btn-sm",
		visable : function(i, c) {
			if (c.status == "1")
				return false;
			return true;
		},
		handle : function(index, data) {
			// index为点击操作的行数
			// data为该行的数据
			modal = $.dmModal({
				id : "siteForm",
				title : "编辑内容信息-" + data.title,
				distroy : true
			});
			modal.show();
			var form = modal.$body.dmForm(getForm(data.contentType));
			form.loadRemote("./load?contentId=" + data.id);
		}
	}, {
		text : "排序",
		cls : "yellow btn-sm",
		handle : function(i, c) {
			sortfun(i, c,c.title);
		}
	} ],
	tools : [// 工具属性
	{
		text : "添加",
		cls : "btn green btn-sm",
		handle : function(grid) {// 按钮点击事件
			if(currentChannelId==undefined)
				bootbox.alert("请先选择频道");
			else
				showForm(0, "文本内容");
		}
	}, {
		text : "移动",
		cls : "btn green btn-sm",
		handle : function(grid) {
			cutOrCopyfun(grid.getSelectIds(), "移动", "radio", "../content/cutTo");
		}
	}, {
		text : "复制",
		cls : "btn green btn-sm",
		handle : function(grid) {
			cutOrCopyfun(grid.getSelectIds(), "复制", "checkbox", "../content/copyTo");
		}
	}, {
		text : "提交",
		cls : "btn green btn-sm",
		handle : function(grid) {
			var ids = grid.getSelectIds();
			if (ids.length > 0) {
				var url = "./commit?contentId=" + ids;
				$.ajax({
					url : url,
					type : "POST",
					dataType : "json",
					success : function(res) {
						bootbox.alert(res.msg);
						grid.reload();
					},
					error : function() {
						bootbox.alert("请求异常！");
					}
				});
			} else {
				bootbox.alert("请选择要提交的项");
			}
		}
	}, {
		text : " 删 除",
		cls : "btn red btn-sm",// 按钮样式
		handle : function(grid) {
			deleteItems(grid.getSelectIds());
		}
	} ],
	search : {
		rowEleNum : 2,
		// 搜索栏元素
		items : [ {
			type : "text",
			label : "标题",
			name : "title",
			placeholder : "输入要搜索的内容信息标题"
		} ]
	}
};
// form
/** **************普通内容表单选项*************** */
function getForm(contentType) {
	var items = [
			{
				type : 'hidden',
				name : 'id',
				id : 'id'
			},
			{
				type : 'hidden',
				name : 'contentType',
				id : 'contentType'
			},
			{
				type : "tree",
				name : "channelId",
				id : "channelId",
				label : "所属频道",
				url : "../channel/tree?siteId=" + currentSiteId,
				autoParam : [ "id", "name", "pId" ],
				expandAll : true,
				beforeCheck : function(treeId, treeNode) {
					if (treeNode.isParent) {
						return false;
					}
				},
				chkStyle : "radio"
			},
			{
				type : 'text',// 类型
				name : 'title',// name
				id : 'title',// id
				label : '内容信息标题',// 左边label
				cls : 'input-large',
				rule : {
					required : true,
					maxlength : 64
				},
				message : {
					required : "请输入内容信息标题",
					maxlength : "最多输入64字节"
				}
			},
			{
				type : 'textarea',// 类型
				row : 3,
				name : 'brief',// name
				id : 'brief',// id
				label : '摘要',// 左边label
				cls : 'input-large',
				rule : {
					required : true,
					maxlength : 256
				},
				message : {
					required : "请输入内容信息摘要",
					maxlength : "最多输入256字节"
				}
			},
			{
				type : 'text',// 类型
				name : 'origin',// name
				id : 'origin',// id
				label : '来源',// 左边label
				cls : 'input-large',
				rule : {
					required : true,
					maxlength : 12
				},
				message : {
					required : "请输入来源信息",
					maxlength : "最多输入12字节"
				}
			},
			{
				type : 'select',
				name : 'templateId',
				id : 'templateId',
				label : '页面模板',
				cls : 'input-large',
				items : [ {
					value : '',
					text : '默认模版'
				} ],
				itemsUrl : "../template/selects?templateType=2&siteId="
						+ currentSiteId
			} ];
	var titleImg = {
		type : 'image',
		id : 'titleImageUrl',
		name : 'titleImageUrl',
		label : '标题图',
		isAjaxUpload : true,
		autoUpload : true,
		uploadUrl : '../attachment/singleUpload',
		onSuccess : function(data) {
			if (data.status == "1") {
				$("#titleImageUrl")
						.attr("value", data.attachment.attachmentUrl);
			} else {
				alert(data.msg);
			}
		},
		deleteHandle : function() {
			$("#titleImageUrl").attr("value", "");
		}
	};
	var titleImgTitle = {
		type : 'textarea',// 类型
		row : 3,
		name : 'titleImageIllustrate',// name
		id : 'titleImageIllustrate',// id
		label : '标题图说明',// 左边label
		cls : 'input-large',
		rule : {
			maxlength : 200
		},
		message : {
			maxlength : "最多输入200字节"
		}
	};
	var contentText = {
		type : 'kindEditor',
		name : 'contentText',
		id : 'contentText',
		label : '内容文本',
		height : "300px",
		width : "500px",
		rule : {
			required : true
		},
		message : {
			required : "请输入内容文本"
		}
	};
	var offic = {
		type : 'files',
		id : 'attachmentIds',
		name : 'attachmentIds',
		limit : 3,
		allowType : ".pdf,.doc,.docx,.xls,.xlsx,.ppt,.htm,.html,.zip,.rar,.gz,.bz2",// 用,分开
		uploadUrl : "../attachment/multipleUpload",
		convertData : function(data) {
			var arrays = [];
			arrays.push(data.attachment.id);
			arrays.push(data.attachment.attachmentName);
			arrays.push(data.attachment.attachmentUrl);
			return arrays;
		},
		fileInfoUrl : "../attachment/detail",
		dataParam : "attachmentId",
		label : '上传附件',
		detail : "最多上传3个附件"
	};
	if (contentType == 0) {
		items.push(titleImg);
		items.push(titleImgTitle);
		items.push(contentText);
		items.push(offic);
	}
	var formOpts = {
		id : "site_form",// 表单id
		name : "site_form",// 表单名
		method : "post",// 表单method
		action : "./insertOrUpdate",// 表单action
		ajaxSubmit : true,// 是否使用ajax提交表单
		labelInline : true,
		rowEleNum : 1,
		beforeSubmit : function() {

		},
		ajaxSuccess : function() {
			modal.hide();
			grid.reload();
		},
		submitText : "保存",// 保存按钮的文本
		showReset : true,// 是否显示重置按钮
		resetText : "重置",// 重置按钮文本
		isValidate : true,// 开启验证
		buttons : [ {
			type : 'button',
			text : '关闭',
			handle : function() {
				modal.hide();
			}
		} ],
		buttonsAlign : "center",
		// 表单元素
		items : items
	};
	return formOpts;
}
