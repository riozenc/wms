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

<script type="text/javascript" src="js/view/basicDataLayout.js"></script>

<script type="text/javascript">

Ext.onReady(function() {

	
	var selectButton = new Ext.button.Button({
				text : '查询',
				
				scale : 'large',
				handler : function() {
					selectFunction(this);
				}
			});

	var tbarItems = [selectButton];

	var box = new cis.view.basicDataLayout({

				_formItems : [{
							xtype : 'textfield',
							id : 'id',
							name : 'id',
							labelAlign : 'right',
							fieldLabel : '主机号',
							labelWidth : 180
						}, {
							xtype : 'textfield',
							id : 'name',
							name : 'name',
							labelAlign : 'right',
							fieldLabel : '主机名',
							labelWidth : 180
						}, {
							xtype : 'textfield',
							id : 'operator',
							name : 'operator',
							labelAlign : 'right',
							fieldLabel : '操作人',
							labelWidth : 180
						}, {
							xtype : 'textfield',
							id : 'workState',
							name : 'workState',
							labelAlign : 'right',
							fieldLabel : '工作状态',
							labelWidth : 180
						}],

				_isCustomButton : true,
				// 自定义按钮
				_gridTbar : tbarItems,

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
									root : 'list'
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