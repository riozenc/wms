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
	//url地址参数
	var params = Ext.urlDecode(location.search.substring(1));
	var userDropStore = Ext.create("Ext.data.Store", {
		fields : [ "userId", "userName" ],
		autoLoad : true,
		proxy : {
			type : "ajax",
			url : "user.do?method=getUsers",
			reader : {
				type : "json",
			}
		}
	});

	var selectButton = Ext.create('Ext.Button', {
		id : 'selectButton',
		text : '查询',
		handler : function() {
			grid.getStore().proxy.extraParams = form.getValues();
			grid.getStore().currentPage = 1;
			grid.getStore().load();
		}
	});

	var addButton = Ext.create('Ext.Button',{
						id : 'addButton',
						text : '新增',
						handler : function() {
							
							var form = new Ext.form.Panel({
								bodyPadding: 10,
								fieldDefaults : {
									labelAlign: 'right',
								    labelWidth: 60,
									msgTarget : 'title',
									allowBlank : false,
									flex:2,
									margin: '1 0 0 6',
								},
								layout: 'anchor',
								defaults: {
						            anchor: '100%'
						        },
								items : [{
									xtype: 'container',
									layout: 'hbox',
									items : [{
										xtype : 'textfield',
										fieldLabel : '任务编号',
										name : 'taskNo',
									},{
										xtype : 'textfield',
										fieldLabel : '任务名称',
										name : 'taskName',
									}]
								},{
									xtype: 'container',
									layout: 'hbox',
									items : [{
										xtype : 'datefield',
										format : 'Y-m-d',
										fieldLabel : '启动时间',
										name : 'startDate',
									},{
										xtype : 'textfield',
										fieldLabel : '计划天数',
										name : 'planDays',
									}]
								},{
									xtype: 'container',
									layout: 'hbox',
									anchor: '50%',
									items : [{
										xtype : 'combobox',
										fieldLabel : '负责人',
										name : 'userId',
										store : userDropStore,
										queryMode : 'local',
										displayField : 'userName',
										valueField : 'userId',
									}]
								},{
									xtype: 'container',
									layout: 'hbox',
									items : [{
										xtype : 'textareafield',
										fieldLabel : '备注信息',
										grow : true,
										name : 'remark',
									}]
								},{
									xtype : 'hidden',
									name : 'projectId',
									value : params.projectId
								}]
							});

							
							var addWindow = new Ext.Window({
										id : 'addWindow',
										title : '新增任务',
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
															Ext.MessageBox.wait('Loadng','Please Wait...');
															Ext.MessageBox.updateProgress(1);
															Ext.Ajax.request({
																url : 'task.do?method=insert',
																params : form.getValues(),
																method : 'POST',
																success : function(response,config) {
																			Ext.MessageBox.hide();
																			var json = Ext.JSON.decode(response.responseText);
																			if (json.success) {
																				Ext.MessageBox.alert('提示',json.msg);
																				ExtgetCmp("addWindow").close();
																				reload();
																			} else {
																				Ext.MessageBox.alert('提示',json.msg);
																			}
																		},
																		dataType : 'json'
																	});
														}
													}
												},
												{
													text : '关闭',
													cls : "x-btn-text btn-query",
													style : 'padding:10px',
													handler : function() {
														Ext.getCmp("addWindow")
																.close();
													}
												} ]
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
			xtype : 'datefield',
			fieldLabel : '查询时间',
			name : 'date',
		}, {
			xtype : 'hidden',
			name : 'id',
			value : params.projectId
		} ],
		flex : 1
	});

	var _gridStore = Ext.create('Ext.data.Store', {

		autoDestroy : true,
		pageSize : 10,

		fields : [ 'id', 'projectNo', 'projectName', 'userName', {
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
			url : 'task.do?method=getTasksByProject',
			extraParams : {
				name : 'czy',
				id : params.projectId
			},
			reader : {
				type : 'json',
				rootProperty : 'list',
				totalProperty : 'totalRow'
			}
		},

		selModel : {
			selType : 'checkboxmodel'
		},
		autoLoad : true
	});

	var grid = new Ext.grid.Panel({
		id : 'gridPanel',
		flex : 2,
		store : _gridStore,
		tbar : [ selectButton, addButton ],
		columns : [ {
			text : 'ID',
			dataIndex : 'id'
		}, {
			text : '任务编号',
			dataIndex : 'taskNo'
		}, {
			text : '任务名称',
			dataIndex : 'taskName'
		}, {
			text : '负责人',
			dataIndex : 'userName',
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