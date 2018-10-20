<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'host.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="scripts/ext-bootstrap.js"></script>
<script type="text/javascript"
	src="scripts/build/classic/locale/locale-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="scripts/build/classic/theme-crisp/resources/theme-crisp-all.css"/>


</head>

<script type="text/javascript">
	Ext.require('js.custom.basicDataLayout');
	Ext.onReady(function() {
		var addButton = Ext.create('Ext.Button', {
			id : 'addButton',
			text : '新增',
			handler : function() {
				var form = new Ext.form.Panel({
					items : [{
						layout : 'column',
						border : false,
						defaults: {
							columnWidth : .45,
							layout : 'form',
							border : false,
						},
						items : [{
							items : {
								xtype : 'textfield',
								fieldLabel : '项目编号',
								name : 'projectNo',
								allowBlank : false,
								msgTarget : 'title',
								labelAlign : 'right',
							}
						}, {
							items : {
								xtype : 'textfield',
								fieldLabel : '项目名称',
								name : 'projectName',
								allowBlank : false,
								msgTarget : 'title',
								labelAlign : 'right',
							}
						}]
					},{
						layout : 'column',
						border : false,
						defaults: { 
							columnWidth : .45,
							layout : 'form',
							border : false,
						},
						items : [{
							items : {
								xtype : 'datefield',
								format: 'Y-m-d',
								labelAlign : 'right',
								fieldLabel : '启动时间',
								name : 'startDate',
								allowBlank : false,
								msgTarget : 'title'
							}
						}, {
							items : {
								xtype : 'textfield',
								labelAlign : 'right',
								fieldLabel : '计划天数',
								name : 'planDays',
								allowBlank : false,
								msgTarget : 'title'
							}
						}]
					}]
				});

				var addWindow = new Ext.Window({
					id : 'addWindow',
					title : '添加项目信息',
					autoHeight : true,
					// autoWidth:true,
					width : '70%',
					// height:'70%',
					autoScroll : true,
					items : form,
					modal : true,
					buttonAlign : 'center',
					buttons : [{
						text : '保存',
						cls : "x-btn-text btn-query",
						style : 'padding:10px',
						handler : function() {
							if (form.isValid()) {
								Ext.MessageBox.wait('Loadng', 'Please Wait...');
								Ext.MessageBox.updateProgress(1);
								Ext.Ajax.request({
											url : 'project.do?method=insert',
											params : form.getValues(),
											method : 'POST',
											success : function(response, config) {
												Ext.MessageBox.hide();
												var json = Ext.JSON
														.decode(response.responseText);
												if (json.success) {
													Ext.MessageBox.alert('提示',
															json.msg);
													Ext.getCmp("addWindow").close();
													reload();
												} else {
													Ext.MessageBox.alert('提示',
															json.msg);
												}
											},
											dataType : 'json'
										});
							}
						}
					}, {
						text : '关闭',
						cls : "x-btn-text btn-query",
						style : 'padding:10px',
						handler : function() {
							Ext.getCmp("addWindow").close();
						}
					}]
				});
				addWindow.show();
			
			}
		});
		
		

		var box = Ext.create('js.custom.basicDataLayout', {
			_gridTbar : [addButton],
			
			_formItems : [ {
				xtype : 'textfield',
				fieldLabel : '项目编号',
				name : 'projectNo',
			}, {
				xtype : 'datefield',
				fieldLabel : '启动时间',
				name : 'startDate',
				format: 'Y-m-d',
			}],

			_gridColumns : [
				{
					text : 'ID',
					dataIndex : 'id'
				}, {
					text : '项目编号',
					dataIndex : 'projectNo'
				}, {
					text : '项目名称',
					dataIndex : 'projectName'
				}, {
					text : '启动时间',
					dataIndex : 'startDate'
				}, {
					text : '结束时间',
					dataIndex : 'endDate'
				}, {
					text : '计划日',
					dataIndex : 'planDays'
				}, {
					text : '实际日',
					dataIndex : 'actualDays'
				}, {
					text : '备注',
					dataIndex : 'remark'
				},{
		            xtype: 'widgetcolumn',
			        text:'操作',
			        widget: {
			            xtype: 'button',
			            text: '任务清单',
			            // handle a click on the button itself
			            handler: function(me,e) {
			            	var data = me.$widgetRecord.data;
			            	var tabs = parent.Ext.getCmp('infoPanel');
							tabs.loadPanel({'id':data.projectNo,'menuUrl':'task.do?method=index&projectId='+data.id,'text':data.projectName});
			            },
			        }
		        }
			],

			_gridStore : Ext.create('Ext.data.Store', {
				autoLoad : true,
				autoDestroy : true,
				pageSize : 15,
				fields : [ 'id', 'projectNo', 'projectName', {
					name : "startDate",
					convert : function(v, record) {
						//将一个long型的time转换为标准的日期对象
						//此时V为一个long型的时间毫秒数
						if (v != null && v != '') {
							return Ext.util.Format.date(new Date(v), "Y-m-d");
						}
					}
				}, {
					name : "endDate",
					convert : function(v, record) {
						//将一个long型的time转换为标准的日期对象
						//此时V为一个long型的时间毫秒数
						if (v != null && v != '') {
							return Ext.util.Format.date(new Date(v), "Y-m-d");
						}
					}
				}, 'planDays', 'actualDays', 'remark' ],
				proxy : {
					type : 'ajax',
					url : 'project.do?method=getProjects',
					reader : {
						type : 'json',
						rootProperty : 'list'
					}
				}
			})
		});


		box._grid.getStore().on('beforeload', function(store, operation) {
			var value = box._form.getValues();
			store.proxy.extraParams = value;
		});
		
		box.init({});

	});
</script>

<body>
</body>
</html>
