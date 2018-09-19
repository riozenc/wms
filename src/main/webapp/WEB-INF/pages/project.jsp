<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="scripts/ext-bootstrap.js"></script>
<script type="text/javascript"
	src="scripts/build/classic/locale/locale-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="scripts/build/classic/theme-crisp/resources/theme-crisp-all.css" />
</head>

<script type="text/javascript">
	var selectButton = Ext.create('Ext.Button', {
		id : 'selectButton',
		text : '查询',
		handler : function() {
			grid.getStore().proxy.extraParams = form.getValues();
			grid.getStore().currentPage = 1;
			grid.getStore().load();
		}
	});

	var addButton = Ext.create('Ext.Button', {
		id : 'addButton',
		text : '新增',
		handler : function() {
			var form = new Ext.form.Panel({
						items : [{
									layout : 'column',
									border : false,
									items : [{
												layout : 'form',
												border : false,
												columnWidth : .45,
												items : {
													xtype : 'textfield',
													labelWidth : 80,
													labelAlign : 'right',
													fieldLabel : '项目编号',
													name : 'projectNo',
													allowBlank : false,
													msgTarget : 'title'
												}
											}, {
												layout : 'form',
												border : false,
												columnWidth : .45,
												items : {
													xtype : 'textfield',
													labelWidth : 80,
													labelAlign : 'right',
													fieldLabel : '项目名称',
													name : 'projectName',
													allowBlank : false,
													msgTarget : 'title'
												}
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												layout : 'form',
												border : false,
												columnWidth : .45,
												items : {
													xtype : 'datefield',
													format: 'Y-m-d',
													labelWidth : 80,
													labelAlign : 'right',
													fieldLabel : '启动时间',
													name : 'startDate',
													allowBlank : false,
													msgTarget : 'title'
												}
											}, {
												layout : 'form',
												border : false,
												columnWidth : .45,
												items : {
													xtype : 'textfield',
													labelWidth : 80,
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

	var form = new Ext.form.Panel({
		id : 'formPanel',
		layout : {
			type : 'hbox',
			pack : 'center',
			align : 'middle'
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : '项目编号',
			name : 'projectNo',
		}, {
			xtype : 'datefield',
			fieldLabel : '查询时间',
			name : 'date',
		}, {

		} ],
		flex : 1
	});

	var _gridStore = Ext.create('Ext.data.Store', {

		autoDestroy : true,
		pageSize : 10,

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
			extraParams : {
				name : 'czy'
			},
			reader : {
				type : 'json',
				rootProperty : 'list',
				totalProperty : 'totalRow'
			}
		},
		autoLoad : true
	});

	var grid = new Ext.grid.Panel({
		id : 'gridPanel',
		flex : 2,
		store : _gridStore,
		tbar : [ selectButton,addButton ],
		columns : [ {
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
        } ],

		forceFit : true,
		//列表行双击事件
		listeners : {
			itemdblclick : function(me, record, item, index, e, eOpts) {

			}
		},

		bbar : [ {
			xtype : 'pagingtoolbar',
			store : _gridStore,
			dock : 'bottom',
			displayInfo : true,
			doRefresh : function() {

				var me = this,
				// 当前分页器页数
				current = me.store.currentPage;

				if (me.fireEvent('beforechange', me, current) !== false) {
					// 数据不足时出现bug
					// me.store.loadPage(current);
					me.store.loadPage(1);
				}
			}
		} ]
	});

	var panel = Ext.create('Ext.panel.Panel', {
		id : 'bem-backlog-panel',
		title : '待办任务',

		layout : {
			type : 'vbox',
			align : 'stretch',
			padding : 5,
		},

		items : [ form, {
			xtype : 'splitter'
		}, grid ],
	});

	Ext.onReady(function() {

		Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			items : [ panel ]
		});

	});
</script>
<body>

</body>
</html>