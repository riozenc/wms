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
<link rel="stylesheet" type="text/css" href="scripts/build/classic/theme-crisp/resources/theme-crisp-all.css"/>
</head>

<script type="text/javascript">
	
	Ext.require('js.custom.basicDataLayout');
	Ext.onReady(function() {
		
		//备注，鼠标指向浮现信息
		Ext.override(Ext.grid.GridPanel, {
			afterRender : Ext.Function.createSequence(Ext.grid.GridPanel.prototype.afterRender,function() {

		        /* 默认显示提示
		        if (!this.cellTip) {
		            return;
		        }*/
		        var view = this.getView();
		        
		        this.tip = new Ext.ToolTip({
		            target: view.el,
		            delegate : '.x-grid-cell-inner',
		            trackMouse: true, 
		            renderTo: Ext.getBody(),  
		            listeners: {
		                beforeshow: function updateTipBody(tip) {
		                    //取cell的值
		                    //fireFox  tip.triggerElement.textContent
		                    //IE  tip.triggerElement.innerText 
		                    var tipText = (tip.triggerElement.innerText || tip.triggerElement.textContent);
		                    if (Ext.isEmpty(tipText) || Ext.isEmpty(tipText.trim()) ) {
		                        return false;
		                   }
		                    tip.update(tipText);
		                }
		            }
		        });
		    
			})
		});
		
		
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
						margin: '0 0 0 5',
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
					},
// 					{
// 						xtype: 'container',
// 						layout: 'hbox',
// 						anchor: '50%',
// 						items : [{
// 							xtype : 'combobox',
// 							fieldLabel : '负责人',
// 							name : 'userId',
// 							store : userDropStore,
// 							queryMode : 'local',
// 							displayField : 'userName',
// 							valueField : 'userId',
// 						}]
// 					},
					{
						xtype: 'container',
						layout: 'hbox',
						items : [{
							xtype : 'textareafield',
							fieldLabel : '备注信息',
							grow : true,
							name : 'remark',
							allowBlank : true,
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
										if (json.statusCode==200) {
											Ext.MessageBox.alert('提示',json.message);
											Ext.getCmp("addWindow").close();
											reload();
										} else {
											Ext.MessageBox.alert('提示',json.msg);
										}
									},
									dataType : 'json'
								});
							}
						}
					},{
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
		
		var releaseButton = Ext.create('Ext.Button',{
			id : 'releaseButton',
			text : '发布',
			handler : function(me) {
				var datas = box._grid.selModel.selected.items;
				var _taskIds = [];
				Ext.Array.forEach(datas,function(item){
					_taskIds.push(item.data);
					//_taskIds += item.data.id + ",";
				});
				
				Ext.MessageBox.wait('Loadng','Please Wait...');
				Ext.MessageBox.updateProgress(1);
				Ext.Ajax.request({
					url : 'task.do?method=releaseTasks',
					//params : {taskIds : _taskIds},
					jsonData : Ext.JSON.encode(_taskIds),
					method : 'POST',
					success : function(response,config) {
						Ext.MessageBox.hide();
						var json = Ext.JSON.decode(response.responseText);
						if (json.statusCode==200) {
							Ext.MessageBox.alert('提示',json.message);
							reload();
						} else {
							Ext.MessageBox.alert('提示',json.msg);
						}
					},
					dataType : 'json',
					contentType : 'application/json;charset=UTF-8'
				});
			}
		});
		
		var box = Ext.create('js.custom.basicDataLayout', {
			_gridTbar : [addButton,'-',releaseButton],
			_ischeckBox : true,//多选框
			_formItems : [ {
				xtype : 'datefield',
				fieldLabel : '查询时间',
				name : 'date',
				format: 'Y-m-d',
			}, {
				xtype : 'hidden',
				name : 'id',
				value : params.projectId
			}],

			_gridColumns : [
				{
					text : 'ID',
					dataIndex : 'id',
					hidden : true,
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
					text : '状态',
					dataIndex : 'status'
				}, {
					text : '备注',
					dataIndex : 'remark',
				}
			],

			_gridStore : Ext.create('Ext.data.Store', {
				autoLoad : true,
				autoDestroy : true,
				pageSize : 15,
				fields : [ 'id', 'projectNo', 'projectName', 'userName', {
					autoLoad : true,
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