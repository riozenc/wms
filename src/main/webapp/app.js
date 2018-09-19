Ext.application({
			name : 'SmartHome',
			appFolder : 'js',
			controllers : ['mainController'],

			launch : function() {

				Ext.create('Ext.container.Viewport', {
							// 布局方式
							layout : 'border',

							items : [{
										xtype : 'logo',
										height:'7%'// 这里可以写对应view的alias的属性
									}, {
										xtype : 'info'
									}, {
										xtype : 'bottom'
									}, {
										xtype : 'menu'
									}]

						});

			}
		});
