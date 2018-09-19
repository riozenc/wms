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
<link rel="stylesheet" type="text/css"
	href="scripts/build/classic/theme-triton/resources/theme-triton-all.css" />


</head>

<script type="text/javascript">
	var selectButton = Ext.create('Ext.Button', {
		id : 'selectButton',
		text : '查询',
		handler : function() {
			grid.getStore().currentPage = 1;
			grid.getStore().load();
		}
	});
	
	var form = new Ext.form.Panel({
		id : 'formPanel',
		region: 'center',
		
		items : [ {
			xtype : 'textfield',
			fieldLabel : '项目编号',
			name : 'projectNo',
		}, {
			xtype : 'datefield',
			fieldLabel : '查询时间',
			name : 'date',
		}],
	});
	
	var _gridStore = Ext.create('Ext.data.Store', {
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
		autoLoad : true,
		pageSize : 10,

		proxy : {
			type : 'ajax',
			url : 'project.do?method=getProjects',
			reader : {
				type : 'json',
				rootProperty : 'list',

				// Do not attempt to load orders inline.
				// They are loaded through the proxy
				implicitIncludes : false
			}
		}

	});

	var grid = new Ext.grid.Panel({
		store : _gridStore,
		tbar : [ selectButton ],
		forceFit : true,
		region: 'south',
		height:'60%',
		columns : [
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
		]

	});

	var panel = Ext.create('Ext.panel.Panel', {
		id : 'bem-backlog-panel',
		title : '待办任务',
	
		 

		items : [ form,grid ],
	});
	
	var panel1 = Ext.create('Ext.Panel', {
	   
	    title: "VBoxLayout Panel",
	    layout: 'border',
	    
	    items: [form,grid]
	});

	Ext.onReady(function() {

		Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			items : [ panel1 ]
		});

	});
</script>

<body>

</body>
</html>
