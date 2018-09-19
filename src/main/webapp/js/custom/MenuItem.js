Ext.define('cis.custom.MenuItem', {
	extend : 'Ext.menu.Item',
	alias : 'widget.MenuItem',

	initComponent : function() {

		var me = this;
		if (!me.isLeaf) {
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
		}

	},

	constructor : function(args) { // 自定义初始化构造函数，先执行此再执行initComponet
		// do something init
		this.menuId = args.id;
		this.text = args.menuName;
		this.isLeaf = args.isLeaf;
		this.menuUrl = args.menuUrl;

		this.callParent();// 快捷调用父类函数
	},

	listeners : {
		click : function(item, e, eOpts) {
			if (item.isLeaf) {
				var tabs = Ext.getCmp('infoPanel');
				tabs.loadPanel(item);
			}
		}
	},

	addMenu : function(menu) {
		this.getMenu().add(Ext.create('cis.custom.MenuItem', menu));
	}

});
