<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>悬赏平台</title>

<script type="text/javascript" src="scripts/ext-bootstrap.js"></script>
<script type="text/javascript"
	src="scripts/build/classic/locale/locale-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="scripts/build/classic/theme-crisp/resources/theme-crisp-all.css" />

<script type="text/javascript">
	Ext.require('js.custom.basicDataLayout');
	Ext.onReady(function() {

		var box = Ext.create('js.custom.basicDataLayout', {

			_formItems : [ {
				xtype : 'datefield',
				fieldLabel : '起始时间',
				name : 'date',
				format : 'Y-m-d',
			}, {
				xtype : 'datefield',
				fieldLabel : '结束时间',
				name : 'date',
				format : 'Y-m-d',
			} ],

			_gridColumns : [ {
				text : 'ID',
				dataIndex : 'id',
				hidden : true,
			}, {
				text : '任务名称',
				dataIndex : 'taskName',
	            sortable: true,
	            summaryType: 'count',
	            summaryRenderer: function(value, summaryData, dataIndex) {
	                return ((value === 0 || value > 1) ? '(' + value + ' Tasks)' : '(1 Task)');
	            }
			}, {
				text : '承接人',
				dataIndex : 'userName',
			}, {
				text : '创建时间',
				dataIndex : 'createDate'
			}, {
				text : '计划日',
				dataIndex : 'planDays'
			}, {
				text : '备注',
				dataIndex : 'remark',
			}, {
				text : '状态',
				dataIndex : 'status'
			}, {
	            xtype: 'widgetcolumn',
		        text:'操作',
		        widget: {
		        	xtype : 'button',
		            text : '接取',
		            flex : 1,
		            // handle a click on the button itself
		            handler: function(me,e) {
		            	var data = me.$widgetRecord.data;
		            	
		            },
		        },
		        renderer : function(a,b,c,d,e){
		        	debugger;
		        }
			} ],

			_gridStore : Ext.create('Ext.data.Store', {
				autoLoad : true,
				autoDestroy : true,
				pageSize : 15,
				fields : [ 'id', 'projectNo', 'projectName', 'taskNo','taskName','userName', {
					autoLoad : true,
					name : "createDate",
					convert : function(v, record) {
						//将一个long型的time转换为标准的日期对象
						//此时V为一个long型的时间毫秒数
						if (v != null && v != '') {
							return Ext.util.Format.date(new Date(v), "Y-m-d");
						}
					}
				},'planDays', 'remark', 'status' ],

				proxy : {
					type : 'ajax',
					url : 'rewardTask.do?method=getTasks',
					extraParams : {
						name : 'czy',
					},
					reader : {
						type : 'json',
						rootProperty : 'list',
						totalProperty : 'totalRow'
					}
				},
				
				//sorters: {property: 'id', direction: 'ASC'},
		        groupField: 'projectName'
			})
		});

		box._grid.getStore().on('beforeload', function(store, operation) {
			var value = box._form.getValues();
			store.proxy.extraParams = value;
		});

		box.init({});

	});
</script>
</head>
<body>

</body>
</html>