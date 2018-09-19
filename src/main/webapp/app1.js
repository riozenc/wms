Ext.application({
	name : 'cis',
	appFolder : 'js',
	controllers : [ 'mainController' ],

	launch : function() {

		Ext.create('Ext.container.Viewport', {
			// 布局方式
			layout : {
				type : 'vbox',
				align : 'stretch'
			},

			items : [ {
				xtype : 'logo',
				height : '7%'// 这里可以写对应view的alias的属性
			}, {
				xtype : 'menuBar',
				height : '5%'
			}, {
				xtype : 'info',
				height : '82%'
			}, {
				xtype : 'bottom',
				height : '6%'
			} ]

		});

	}
});
