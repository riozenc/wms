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

Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.form.field.Number',
    'Ext.form.field.Date',
    'Ext.tip.QuickTipManager'
]);





Ext.onReady(function(){

   // Ext.tip.QuickTipManager.init();
    
    
    var store = Ext.create('Ext.data.Store', {
		autoLoad : true,
		autoDestroy : true,
		pageSize : 15,
		fields : [ 'id', 'projectNo', 'taskNo', 'taskName', 'createDate', 'planDays', 'remark', 'status'],

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
		
		sorters: {property: 'id', direction: 'ASC'},
        groupField: 'projectNo'
	});


    var grid = Ext.create('Ext.grid.Panel', {
        width: 840,
        height: 450,
        frame: true,
        title: 'Sponsored Projects',
        iconCls: 'icon-grid',
        renderTo: document.body,
        store: store,
        plugins: {
            cellediting: {
                clicksToEdit: 1
            }
        },
        dockedItems: [{
            dock: 'top',
            xtype: 'toolbar',
            items: [{
                tooltip: 'Toggle the visibility of the summary row',
                text: 'Toggle Summary',
                enableToggle: true,
                pressed: true,
                handler: function() {
                    grid.getView().getFeature('group').toggleSummaryRow();
                }
            }]
        }],
        features: [{
            id: 'group',
            ftype: 'groupingsummary',
            groupHeaderTpl: '{name}',
            hideGroupedHeader: true,
            enableGroupingMenu: false
        }],
        columns: [{
            text: 'Task',
            flex: 1,
            tdCls: 'task',
            sortable: true,
            dataIndex: 'taskName',
            hideable: false,
            summaryType: 'count',
            summaryRenderer: function(value, summaryData, dataIndex) {
                return ((value === 0 || value > 1) ? '(' + value + ' Tasks)' : '(1 Task)');
            }
        }, {
            header: 'Project',
            width: 180,
            sortable: true,
            dataIndex: 'projectNo'
        }]
    });
});
</script>
<body>
</body>
</html>