Ext.define('czy.view.bottom', {
			extend : 'Ext.toolbar.Toolbar',
			padding : 10,
			alias : 'widget.bottom',

			region : 'south',

			html : '欢迎使用',
			items : [
					// begin using the right-justified button container
					'->', // same as { xtype: 'tbfill' }
					{
						xtype : 'button',
						text : '退出',
						handler : function() {
							window.location.href = 'login.do?type=logout';
						}
					}]
		});


