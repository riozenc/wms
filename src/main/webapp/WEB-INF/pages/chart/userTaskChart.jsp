<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>

</head>

<body style="height: 100%; margin: 0">
	<div id="container" style="height: 100%"></div>

	<script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);

		$.get('chart.do?method=getJson', function(rawData) {

			

			option = makeOption(rawData);

			var item = makeSeries(rawData);
			option.series = item;

			myChart.setOption(option);
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
				series[j+1] = makeSeriesDate(rawData.yAxisData[j],
						rawData.planStartData[j], rawData.actualStartData[j],
						j, len);
			}

			return series;
		};

		function makeSeriesDate(name, b, c, index, length) {
			
			// 			name : "项目确定",
			// 			type : "bar",
			// 			stack : "总",

			var array = new Array();
			for (i = 0; i < length; i++) {
				if (i == index) {
					array.push(b);
				} else {
					array.push(0);
				}
			}

			return {
				name : name,
				type : "bar",
				stack : "总",
				data : array
			};
		}

		function makeOption(rawData) {

			var startDate = rawData.startDate;
			var endDate = rawData.endDate;
			var xAxisDate = new Array();
			while (endDate >= startDate) {
				var date = new Date(startDate);
				var date_time = (date.getFullYear() + "-"
						+ (date.getMonth() + 1) + "-" + date.getDate());
				xAxisDate.push(date_time);
				startDate = startDate + 1 * 24 * 60 * 60 * 1000;
			}

			return {

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
					formatter : function(params,a,b,c) {
						debugger;
						return params.name + '<br/>计划时间 : ' + params.data + '天';
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
// 				series : [ {
// 					name : "辅助",
// 					type : "bar",
// 					stack : "总",
// 					itemStyle : {
// 						normal : {
// 							barBorderColor : 'rgba(0,0,0,0)',
// 							color : 'rgba(0,0,0,0)'
// 						},
// 						emphasis : {
// 							barBorderColor : 'rgba(0,0,0,0)',
// 							color : 'rgba(0,0,0,0)'
// 						}
// 					},
// 					// 					data : rawData.planStartData,
// 					data : [ 0, 1, 2, 3, 4, 5, 6 ]
// 				}, {
// 					name : "项目确定",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 1, 0, 0, 0, 0, 0, 0 ]
// 				}, {
// 					name : "问卷设计",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 0, 1, 0, 0, 0, 0, 0 ]
// 				}, {
// 					name : "试访",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 0, 0, 2, 0, 0, 0, 0 ]
// 				}, {
// 					name : "问卷确定",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 0, 0, 0, 1, 0, 0, 0 ]
// 				}, {
// 					name : "实地执行",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 0, 0, 0, 0, 4, 0, 0 ]
// 				}, {
// 					name : "数据录入",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 0, 0, 0, 0, 0, 1, 0 ]
// 				}, {
// 					name : "数据分析",
// 					type : "bar",
// 					stack : "总",
// 					data : [ 0, 0, 0, 0, 0, 0, 3 ]
// 				} ]
			}
		}
	</script>
</body>

</html>