Ext.define('czy.custom.MenuButton', {
	extend : 'Ext.button.Button',
	alias : 'widget.MenuButton',

	initComponent : function() {

		var me = this;

		me.setMenu(Ext.create('Ext.menu.Menu', {}));

		Ext.Ajax.request({
			url : 'menu.do?method=getSubMenu',
			method : 'post',
			params : {
				parentId : me.menuId
			},
			success : function(response, opts) {
				var result = Ext.JSON.decode(response.responseText);
				Ext.Array.forEach(result, function(item) {
					me.addMenu(item);
				});
			},

			failure : function(response, opts) {
				Ext.Msg.alert('提示', '服务请求失败.');
			}
		});

	},

	constructor : function(id, menuName) { // 自定义初始化构造函数，先执行此再执行initComponet
		// do something init
		this.menuId = id;
		this.text = menuName;
		this.callParent();// 快捷调用父类函数
	},

	addMenu : function(menu) {
		this.getMenu().add(Ext.create('czy.custom.MenuItem', menu));
	}

});
