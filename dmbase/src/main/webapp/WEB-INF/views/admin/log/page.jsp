<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
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
    <meta charset="utf-8"/>
    <title>TOPIECMS | 日志管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <%@include file="../../includejsps/style.jsp" %>
    <%@include file="../../includejsps/plugin-style.jsp" %>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-menu-fixed" class to set the mega menu fixed  -->
<!-- DOC: Apply "page-header-top-fixed" class to set the top menu fixed  -->
<body>
<!-- BEGIN HEADER -->
<%@include file="../../includejsps/head.jsp" %>
<!-- END HEADER -->
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
    <!-- BEGIN PAGE HEAD -->
    <%-- <div class="page-head">
        <div class="container">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
                <h1>日志管理</h1>
            </div>
            <!-- END PAGE TITLE -->
            <!-- BEGIN PAGE TOOLBAR -->
            <%@include file="../../includejsps/toolbar.jsp" %>
            <!-- END PAGE TOOLBAR -->
        </div>
    </div> --%>
    <!-- END PAGE HEAD -->
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content">
        <div class="container">
            <!-- BEGIN PAGE CONTENT INNER -->
            <div class="row margin-top-10">
                <div class="col-md-12 col-sm-12">
                    <!-- BEGIN PORTLET-->
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption caption-md">
                                <i class="icon-bar-chart theme-font hide"></i> <span
                                    class="caption-subject theme-font bold uppercase">日志管理</span>
                                <span class="caption-helper"></span>
                            </div>
                        </div>
                        <div class="portlet-body" id="log_grid"></div>
                    </div>
                    <!-- END PORTLET-->
                </div>
            </div>
            <!-- END PAGE CONTENT INNER -->
        </div>
    </div>
    <!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../../includejsps/foot.jsp" %>
<!-- END FOOTER-->
<!-- BEGIN JAVASCRIPTS-->
<%@include file="../../includejsps/js.jsp" %>
<%@include file="../../includejsps/plugin-js.jsp" %>
<script type="text/javascript">
    var grid;
    var options = {
        url: "./ajaxList", // ajax地址
        pageNum: 1,//当前页码
        pageSize: 15,//每页显示条数
        idFiled: "id",//id域指定
        showCheckbox: true,//是否显示checkbox
        checkboxWidth: "3%",
        showIndexNum: true,
        indexNumWidth: "5%",
        pageSelect: [2, 15, 30, 50],
        cloums: [{
            title: "用户",
            field: "user",
            sort: true,
            width: "10%"
        }, {
            title: "ip",
            field: "ip",
            sort: true
        }, {
            title: "类型",
            field: "type",
            sort: true,
            format: function (i, data) {
                if (data.type == "0") {
                    return "登录日志";
                } else {
                    return "操作日志";
                }
            }
        }, {
            title: "内容",
            field: "content",
            width: "35%"
        }, {
            title: "日期",
            field: "date",
            width: "10%"
        }],
        actionCloumText: "操作",//操作列文本
        actionCloumWidth: "5%",
        actionCloums: [{
            text: "删除",
            cls: "red  btn-sm",
            handle: function (index, data) {
                deleteItem(data.id);
            }
        }],
        tools: [
            //工具属性
            {
                text: " 删 除",
                cls: "btn red  btn-sm",//按钮样式
                handle: function (grid) {
                    deleteItems(grid.getSelectIds());
                }
            }, {
                text: "删除所有日志",
                cls: "btn red btn-sm",//按钮样式
                handle: function () {
                    deleteAllLogs();
                }
            }],
        search: {
            rowEleNum: 4,
            //搜索栏元素
            items: [{
                type: "text",
                label: "用户名",
                name: "user",
                placeholder: "输入要搜索的用户名"
            }, {
                type: "text",
                label: "ip",
                name: "ip",
                placeholder: "输入要搜索的ip"
            }, {
                type: "select",
                label: "类型",
                name: "type",
                items: [{
                    text: "全部",
                    value: ""
                }, {
                    text: "登录日志",
                    value: "0"
                }, {
                    text: "操作日志",
                    value: "1"
                }]
            }, {
                type: "text",
                label: "内容",
                name: "content"
            }, {
            	id:"beginDate",
                type: "datepicker",
                label: "开始时间",
                name: "beginDate",
                maxDatepickerId:'endDate'
            }, {
            	id:'endDate',
                type: "datepicker",
                label: "结束时间",
                name: "endDate",
                minDatepickerId:'beginDate'
            }]
        }
    };
    function deleteItem(id) {
        bootbox.confirm("确定删除吗？", function (result) {
            if (result) {
                $.ajax({
                    type: "POST",
                    data: "logId=" + id,
                    dataType: "json",
                    url: "./ajaxDelete",
                    success: function (data) {
                    	if (data.status == 1) {
							bootbox.alert("删除成功!");
							grid.reload();
						}else{
							bootbox.alert("删除失败!");
						}
                    }
                });
            }
        });
    }
    function deleteItems(ids) {
        if (ids.length > 0) {
            bootbox.confirm("确定删除吗？", function (result) {
                if (result) {
                    $.ajax({
                        type: "POST",
                        data: "logId=" + ids,
                        dataType: "json",
                        url: "./ajaxDelete",
                        success: function (data) {
                        	if (data.status == 1) {
								bootbox.alert("删除成功!");
								grid.reload();
							}else{
								bootbox.alert("删除失败!");
							}
                        }
                    });
                }
            });
        } else {
            bootbox.alert("请选择要删除的选项！");
        }
    }

    function deleteAllLogs() {
        bootbox.confirm("确定删除所有日志吗？", function (result) {
            if (result) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "./ajaxDeleteAllLogs",
                    success: function (data) {
                        if (data.status == 1) {
                            grid.reload();
                            bootbox.alert("删除所有日志成功！");
                        }
                    }
                });
            }
        });
    }

    jQuery(document).ready(function () {
        grid = $("#log_grid").dmGrid(options);
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
