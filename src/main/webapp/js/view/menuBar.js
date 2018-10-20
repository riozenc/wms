Ext.define('czy.view.menuBar', {
	extend : 'Ext.toolbar.Toolbar',

	alias : 'widget.menuBar',

	listeners : {
		afterrender : function(me, eOpts) {
			Ext.Ajax.request({
				url : 'menu.do?method=getRootMenu',

				success : function(response, opts) {
					var result = Ext.JSON.decode(response.responseText);
					Ext.Array.forEach(result, function(item) {
						me.loadButton(item);
					});
				},

				failure : function(response, opts) {
					Ext.Msg.alert('提示', '服务请求失败.');
				}
			});
		},

	},

	loadButton : function(menu) {
		var button = Ext.create('czy.custom.MenuButton', menu.id, menu.menuName);
		this.add(button);
	}

});
