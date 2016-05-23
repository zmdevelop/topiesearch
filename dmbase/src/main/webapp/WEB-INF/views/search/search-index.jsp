<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String iport = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	String basePath = iport
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
<title>TOPIESEARCH | 搜索管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link rel="stylesheet"
	href="<%=basePath%>assets/dmcms/plugins/code/css/codemirror.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=basePath%>assets/dmcms/plugins/code/css/ambiance.css"
	type="text/css">

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
		<%--  <div class="page-head">
        <div class="container">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
                <h1>搜索管理</h1>
            </div>
            <!-- END PAGE TITLE -->
            <!-- BEGIN PAGE TOOLBAR -->
            <%@include file="../includejsps/toolbar.jsp" %>
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
										class="caption-subject theme-font bold uppercase">搜索管理</span>
									<span class="caption-helper"></span>
								</div>
							</div>
							<div class="portlet-body form">
								<div class="form-body" id="config">
									<div class="row">
										<div class="col-md-6">
											<form class="form-horizontal" id="searchConfig" action="#">
												<div class="form-group">
													<div class="col-md-8">
														<input class="form-control" type="text" name="ipAddress"
															value="${searchConfig.ipAddress}" />
													</div>
													<label class="control-label">搜索服务器地址</label>
												</div>
												<%-- <div class="form-group"><div class="col-md-8"><input  type="checkbox" <c:if test="${searchConfig.cmsContent==true}">checked="checked"</c:if> name="cmsContent" value=true />检索cms内容 </div></div>--%>
			                       
												
												<input type="hidden" name="id" value="${searchConfig.id}" />
												<div class="form-group">
													<div class="col-md-8">
														<select name="snippets" class="form-control">
															<option value=1
																<c:if test="${searchConfig.snippets==1}">selected="selected"</c:if>>1</option>
															<option value=2
																<c:if test="${searchConfig.snippets==2}">selected="selected"</c:if>>2</option>
															<option value=3
																<c:if test="${searchConfig.snippets==3}">selected="selected"</c:if>>3</option>
														</select>
													</div>
													<label class="control-label">显示片段</label>
												</div>
												<div class="form-group">
													<div class="col-md-8">
														<input class="form-control" type="text" name="snippetsNum"
															value="${searchConfig.snippetsNum}" />
													</div>
													<label class="control-label">片段字符数</label>
												</div>
			                       <div class="form-group"><div class="col-md-8"><input  type="checkbox" <c:if test="${searchConfig.attachment==true}">checked="checked"</c:if> name="attachment" value=true />检索附件文档 </div></div>
												<div class="form-group">
													<div class="col-md-6">
														<input type="checkbox" name="highlight"
															<c:if test="${searchConfig.highlight==true}">checked="checked"</c:if>
															value=true />高亮显示
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-8">
														<input type="text" class="form-control" id="colorPicker"
															name="highlightcolor"
															value="${searchConfig.highlightcolor}" />
													</div>
													<label class="control-label">高亮颜色</label>
												</div>
												<div class="form-group">
													<div class="col-md-8">
														<input id="onekeyBtn" type="button" class="btn green"
															value="配置" /> 
														<input id="frontView" type="button"
															class="btn green" onclick="window.open('./searchText')"
															value="预览" /> 
														<a href="javascript:build();"
															id="buildfull" class="btn green">构建</a>
														<a href="javascript:loadConfig();"
															id="loadConfig" class="btn green">加载配置文件</a>
													</div>

												</div>
											</form>
										</div>
										<div class="col-md-6">
											<form class="form-horizontal" id="importConfigform" action="#">
											
											<div class="form-group">
													<div class="col-md-8">
														<select name="command" class="form-control" id="command">
															<option>full-import</option>
															<option>delta-import</option>
														</select>
													</div>
													<label class="control-label">Command</label>
												</div>
												<div class="form-group">
													<div class="col-md-6">
														<input
														name="clean" id="clean" value="true" checked="checked"
														type="checkbox"> Clean
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-6">
														<input
														name="commit" id="commit" value="true" checked="checked"
														type="checkbox"> Commit
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-6">
														<input
														name="optimize" id="optimize" value="true" type="checkbox">
														Optimize
													</div>
												</div>
													
												<div class="form-group">
													<input id="onekeyBtn" type="button" class="btn green" onclick="dataimportfetch();"
															value="更新" /> 
													<input id="dataimport_fetch_status" type="button"
															class="btn green" onclick="dataimportfetchstatus();"
															value="刷新状态" />

												</div>
												<div>
													<div id="current_state"  class="success">
														
														<div class="info">

															<strong></strong>
															<div class="details" style="display: block;">
																<div class="docs" id="docs_imp">
																	<abbr title="Total Requests made to DataSource">Requests</abbr>:
																	0 , <abbr title="Total Rows Fetched">Fetched</abbr>:
																	0 , <abbr
																		title="Total Documents Skipped">Skipped</abbr>: 0, <abbr
																		title="Total Documents Processed">Processed</abbr>: 0
																	
																</div>
																<div class="dates" id="dates_imp">
																	<abbr title="Full Dump Started">Last Update</abbr>: <abbr
																		class="time" ></abbr>
																</div>
															</div>

														</div>

													</div>
												</div>
											</form>
										</div>
									</div>
									<div class="form-group">
										<div id="configContext">
											<textarea id="code"><!--  config   --></textarea>
										</div>
									</div>
								</div>
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
		<%@include file="../includejsps/foot.jsp"%>
		<!-- END FOOTER-->
		<!-- BEGIN JAVASCRIPTS-->
		<%@include file="../includejsps/js.jsp"%>
		<%@include file="../includejsps/plugin-js.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>assets/dmcms/plugins/code/js/codemirror.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>assets/dmcms/plugins/code/js/mode/javascript/javascript.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>assets/dmcms/plugins/code/js/mode/css/css.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>assets/dmcms/plugins/code/js/mode/xml/xml.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>assets/dmcms/plugins/code/js/mode/htmlmixed/htmlmixed.js"></script>
		<script>
			$("#onekeyBtn").click(function() {
				bootbox.confirm("确定配置?", function(result) {
					if (result) {
						$.ajax({
							url : "./insertOrUpdate",
							data : $("#searchConfig").serialize(),
							type : "post",
							dataType : "json",
							success : function(result) {
								bootbox.alert(result.msg);
							}
						});
					}
				});
			});
			$('#colorPicker').minicolors({
				theme : 'bootstrap'
			});
			var handler_url = "<%=iport%>/solr/cms_core/dataimport";
			function loadConfig() {
				var that = $("#loadConfig");
				$.ajax({
					url : handler_url + '?command=reload-config',
					dataType : 'xml',
					context : $(this),
					beforeSend : function(xhr, settings) {
						that.removeClass('green').addClass('yellow');
					},
					success : function(response, text_status, xhr) {
						that.removeClass('yellow').addClass('green');
						/* window.setTimeout(
						  function() {
						    reload_config_element
						      .removeClass('success');
						  },
						  5000
						); */
					},
					error : function(xhr, text_status, error_thrown) {
						bootbox.alert("出错了,请检查数据源/查询配置");
					},
					complete : function(xhr, text_status) {

						dataimport_fetch_config();
						//that.addClass('yellow');

					}
				});
			}
			function dataimport_fetch_config() {
				$.ajax({
					url : handler_url + '?command=show-config&indent=true',
					dataType : 'text',
					context : $(this),
					beforeSend : function(xhr, settings) {
						editor.setValue("");

					},
					success : function(config, text_status, xhr) {

						editor.setValue(config);
					},
					error : function(xhr, text_status, error_thrown) {
						alert("出错了,请检查服务");
					},
					complete : function(xhr, text_status) {

					}
				});

			};
			var editor = CodeMirror.fromTextArea(document
					.getElementById("code"), { // 标识到textarea
				mode : "xml", // 模式
				// theme : "",  // CSS样式选择
				indentUnit : 2, // 缩进单位，默认2
				smartIndent : true, // 是否智能缩进
				tabSize : 2, // Tab缩进，默认4
				readOnly : true, // 是否只读，默认false
				showCursorWhenSelecting : true,
				lineNumbers : false
			// 是否显示行号
			// .. 还有好多，翻译不完。需要的去看http://codemirror.net/doc/manual.html#config
			});
			var handler_url1 = '<%=iport%>/solr/admin/cores';
			function loadcore(){
				var url=handler_url1+'?wt=json&_='+new Date();
				$.ajax({
		              url : url,
		              dataType : 'json',
		              type:'POST',
		              success : function( response, text_status, xhr )
		              {
		            	  
		              },
		              error:function(){
		            	  alert("加载出错!");
		              }
		              
		            });
			};
			loadcore();
			function dataimportfetch(){
				var param = $('#importConfigform').serialize();
				$.ajax({
			              url : handler_url,
			              dataType : 'json',
			              type:'POST',
			              data:param,
			              success : function( response, text_status, xhr )
			              {
			            	  dataimportfetchstatus();
			              },
			              error:function(){
			              }
			              
			            });
			}
			
			function dataimportfetchstatus(){
				$.ajax(
		            {
		              url : handler_url + '?command=status&indent=true&wt=json',
		              dataType : 'json',
		              success : function( response, text_status, xhr )
		              {
		                var state_element = $( '#current_state' );

		                var status = response.status;
		                var rollback_time = response.statusMessages.Rolledback || null;
		                var abort_time = response.statusMessages.Aborted || null;
		                
		                var messages = response.statusMessages;
		                var messages_count = 0;
		                for( var key in messages ) { messages_count++; }

		                function dataimportcomputedetails( response, details_element, elapsed_seconds )
		                {

		                  // --

		                  var document_config = {
		                    'Requests' : 'Total Requests made to DataSource',
		                    'Fetched' : 'Total Rows Fetched',
		                    'Skipped' : 'Total Documents Skipped',
		                    'Processed' : 'Total Documents Processed'
		                  };

		                  var document_details = [];
		                  for( var key in document_config )
		                  {
		                    var value = parseInt( response.statusMessages[document_config[key]], 10 );

		                    var detail = '<abbr title="' + document_config[key] + '">' + key + '</abbr>: ' +   value ;
		                    /* if( elapsed_seconds && 'skipped' !== key.toLowerCase() )
		                    {
		                      detail += ' <span>(' +  Math.round( value / elapsed_seconds ) + '/s)</span>'
		                    } */

		                    document_details.push( detail );
		                  };

		                  $( '#docs_imp' )
		                    .html( document_details.join( ', ' ) );

		                  // --

		                  var dates_config = {
		                      'Last Update' : 'Full Dump Started',
		                      'Aborted' : 'Aborted',
		                      'Rolledback' : 'Rolledback'
		                  };

		                  var dates_details = [];
		                  for( var key in dates_config )
		                  {
		                    var value = response.statusMessages[dates_config[key]];

		                    if( value )
		                    {
		                      var detail = '<abbr title="' + dates_config[key] + '">' + key + '</abbr>: '
		                                 + '<abbr class="time">' +  value + '</abbr>';
		                      dates_details.push( detail );                      
		                    }
		                  };

		                  var dates_element = $( '#dates_imp' );

		                  dates_element
		                    .html( dates_details.join( ', ' ) );

		                 /*  $( '.time')
		                    .removeData( 'timeago' )
		                    .timeago(); */
		                };
		                dataimportcomputedetails(response,'',1000);
		               /*  var get_time_taken = function get_default_time_taken()
		                {
		                  var time_taken_text = response.statusMessages['Time taken'];
		                  return app.convert_duration_to_seconds( time_taken_text );
		                };

		                var get_default_info_text = function default_info_text()
		                {
		                  var info_text = response.statusMessages[''] || '';

		                  // format numbers included in status nicely
		                  info_text = info_text.replace
		                  (
		                    /\d{4,}/g,
		                    function( match, position, string )
		                    {
		                      return app.format_number( parseInt( match, 10 ) );
		                    }
		                  );

		                  var time_taken_text = app.convert_seconds_to_readable_time( get_time_taken() );
		                  if( time_taken_text )
		                  {
		                    info_text += ' (Duration: ' + time_taken_text + ')';
		                  }

		                  return info_text;
		                };

		                var show_info = function show_info( info_text, elapsed_seconds )
		                {
		                  $( '.info strong', state_element )
		                    .text( info_text || get_default_info_text() );

		                  $( '.info .details', state_element )
		                    .hide();
		                };

		                var show_full_info = function show_full_info( info_text, elapsed_seconds )
		                {
		                  show_info( info_text, elapsed_seconds );

		                  dataimport_compute_details
		                  (
		                    response,
		                    $( '.info .details', state_element ),
		                    elapsed_seconds || get_time_taken()
		                  );
		                };

		                state_element
		                  .removeAttr( 'class' );

		                var current_time = new Date();
		                $( '.last_update abbr', state_element )
		                  .text( current_time.toTimeString().split( ' ' ).shift() )
		                  .attr( 'title', current_time.toUTCString() );

		                $( '.info', state_element )
		                  .removeClass( 'loader' );

		                if( 'busy' === status )
		                {
		                  state_element
		                    .addClass( 'indexing' );

		                  if( autorefresh_status )
		                  {
		                    $( '.info', state_element )
		                      .addClass( 'loader' );
		                  }

		                  var time_elapsed_text = response.statusMessages['Time Elapsed'];
		                  var elapsed_seconds = app.convert_duration_to_seconds( time_elapsed_text );
		                  time_elapsed_text = app.convert_seconds_to_readable_time( elapsed_seconds );

		                  var info_text = time_elapsed_text
		                                ? 'Indexing since ' + time_elapsed_text
		                                : 'Indexing ...';

		                  show_full_info( info_text, elapsed_seconds );
		                }
		                else if( rollback_time )
		                {
		                  state_element
		                    .addClass( 'failure' );

		                  show_full_info();
		                }
		                else if( abort_time )
		                {
		                  state_element
		                    .addClass( 'aborted' );

		                  show_full_info( 'Aborting current Import ...' );
		                }
		                else if( 'idle' === status && 0 !== messages_count )
		                {
		                  state_element
		                    .addClass( 'success' );

		                  show_full_info();
		                }
		                else 
		                {
		                  state_element
		                    .addClass( 'idle' );

		                  show_info( 'No information available (idle)' );
		                }

		                // show raw status

		                var code = $(
		                  '<pre class="syntax language-json"><code>' +
		                  app.format_json( xhr.responseText ) +
		                  '</code></pre>'
		                );

		                $( '#raw_output_container', content_element ).html( code );
		                hljs.highlightBlock( code.get(0) );

		                if( !app.timeout && autorefresh_status )
		                {
		                  app.timeout = window.setTimeout
		                  (
		                    function()
		                    {
		                      dataimport_fetch_status( true )
		                    },
		                    dataimport_timeout
		                  );
		                } */
		              },
		              error : function( xhr, text_status, error_thrown )
		              {
		                console.debug( arguments );
		                alert("粗错了");

		              }
		            }
		          );
			}
			function build() {
				bootbox.confirm("确定构建吗？", function(result) {
					if (result) {
						$.ajax({
							type : "POST",
							dataType : "json",
							url : "./build",
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
		</script>

		<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
