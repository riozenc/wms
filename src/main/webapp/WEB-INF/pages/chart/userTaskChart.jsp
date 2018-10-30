<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="scripts/ext-bootstrap.js"></script>
<script type="text/javascript"
	src="scripts/build/classic/locale/locale-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="scripts/build/classic/theme-crisp/resources/theme-crisp-all.css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>




</head>


<script type="text/javascript">
	function createEchart(startDate,endDate) {
		var dom = document.getElementById("container");
		var myChart;
		$.ajax({
			 url:"chart.do?method=getJson",
			 type:"post",
			 data:{
				 startDate : startDate,
				 endDate : endDate,
			 },
			success:function(rawData){
				myChart= echarts.init(dom);
				myChart.clear();
				option = makeOption(rawData);
				var item = makeSeries(rawData);
				option.series = item;
				myChart.setOption(option);
			}
	 
		 });

		function makeSeries(rawData) {
			var series = [];
			series[0] = {
				name : "辅助",
				type : "bar",
				stack : "总",
				itemStyle : {
					normal : {
						barBorderColor : 'rgba(0,0,0,0)',
						color : 'rgba(0,0,0,0)'
					},
					emphasis : {
						barBorderColor : 'rgba(0,0,0,0)',
						color : 'rgba(0,0,0,0)'
					}
				},
				data : rawData.blankData,
			//data : [ 0, 0, 0, 0, 0, 0, 0 ]
			};
			for (j = 0, len = rawData.yAxisData.length; j < len; j++) {
				series[j + 1] = makeSeriesDate(rawData.yAxisData[j],
						rawData.planStartData[j], rawData.actualStartData[j],
						rawData.users[j], j, len);
			}
			return series;
		}
		;

		function makeSeriesDate(name, planStartData, actualStartData, user,
				index, length) {

			var array = new Array();
			for (i = 0; i < length; i++) {
				if (i == index) {
					array.push(planStartData);
				} else {
					array.push(0);
				}
			}

			return {
				name : name,
				type : "bar",
				stack : "总",
				data : array,
				user : user
			};
		}

		function makeOption(rawData) {

			var _startDate = rawData.startDate;
			var _endDate = rawData.endDate;
			var xAxisDate = new Array();
			while (_endDate >= _startDate) {
				var date = new Date(_startDate);
				var date_time = (date.getFullYear() + "-"
						+ (date.getMonth() + 1) + "-" + date.getDate());
				xAxisDate.push(date_time);
				_startDate = _startDate + 1 * 24 * 60 * 60 * 1000;
			}

			return {
				rawData : rawData,
				title : {
					text : rawData.title,
					x : 'center'
				},
				calculable : false,
				tooltip : {
					show : true,
					axisPointer : {
						type : 'shadow'
					},
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar', 'stack', 'tiled' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					},
					formatter : function(params, a, b, c) {
						return option.series[params.dataIndex + 1].user
								+ '<br/>' + params.name + '<br/>计划时间 : '
								+ params.data + '天';
					}
				},
				axis : {
					splitLine : {
						show : false
					},
					splitArea : {
						show : false
					}
				},
				calculable : true,
				yAxis : [ {
					type : "category",
					splitLine : {
						show : true
					},
					data : rawData.yAxisData
				} ],
				xAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : function(value) {
							return xAxisDate[value * 1];
						}
					},
				} ],
			}
		}
	};

	Ext.onReady(function() {
		var panel = Ext.create("Ext.panel.Panel", {
			layout : {
				type : 'vbox',
				align : 'stretch',
				padding : 5,
			},

			items : [ new Ext.form.Panel({
				id : 'formPanel',
				layout : {
					type : 'hbox',
					pack : 'center',
					align : 'middle'
				},
				
				defaults: { // defaults are applied to items, not the container
					labelAlign : 'right',
					padding:'0 0 0 10'
				},

				items : [ {
					xtype : 'datefield',
					fieldLabel : '开始时间',
					name : 'startDate',
					format: 'Y-m-d',
				}, {
					xtype : 'datefield',
					fieldLabel : '结束时间',
					name : 'endDate',
					format: 'Y-m-d',
				}],
				flex : 1
			}),{
				xtype : 'splitter'
			}, {
				xtype: 'button',
				text: 'Click me',
			    renderTo: Ext.getBody(),
			    handler: function() {
			    	var params=Ext.getCmp("formPanel").getValues();
			    	createEchart(params.startDate,params.endDate);
			    }
			},{
				xtype : 'splitter'
			}, Ext.create("Ext.panel.Panel",{
				title:'cc',
				flex: 3,
				html:'<div id="container" style="width:100%;height:100%"></div>'
			})],
		});

		var viewport = Ext.create('Ext.container.Viewport', {
			id : this.baseViewportId,
			layout : 'fit',
			items : [ panel ]
		});

	});
	
</script>


</html>