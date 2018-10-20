Ext.define('czy.view.menu', {
			extend : 'Ext.tree.Panel',

			alias : 'widget.menu',

			region : 'west',
			title : '菜单',
			width : 200,

			rootVisible : true,
			collapsible : true,
			// singleExpand : true,//只显示一个子菜单
			animate : true,// 开启动画效果

			root : {
				id : 0,
				text : "总菜单",
				expanded : false
			},// 定位到根节点

			store : Ext.create('Ext.data.TreeStore', {

						proxy : {
							type : 'ajax',
							url : 'login.do?type=initMenu'

						},
						fields : ['id', 'text'],// 跟旧版本extjs一样，节点的id和显示文本
						nodeParam : 'id'

					}),

			listeners : {
				itemclick : function(view, record, item, index, e) {

					// var node = record.data;
					var node = record.raw;
					if (node.leaf) {

						var tabs = Ext.getCmp('infoPanel');
						tabs.loadPanel(node);

					}
				},
				load : function(me) {

					var result = me.proxy.reader.rawData;

					if (!result.success && result.success != undefined) {

						Ext.MessageBox.alert("提示", result.msg);
					}

				}
				// itemclick : function(view, record, items, index, e) {
				// alert(items);
				// if (record.get('leaf') == false) {
				// return;
				// } else {
				// Ext.MessageBox.show({
				// title : '节点操作',
				// msg : 'itemclick：index=' + index + ",text="
				// + record.data.text,
				// icon : Ext.MessageBox.INFO
				// });
				// }
				// },

			}

		});
