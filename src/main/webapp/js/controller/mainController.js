Ext.define('czy.controller.mainController', {
	extend : 'Ext.app.Controller',
	views : ['logo', 'info', 'bottom', 'menuBar', 'button'],
	requires: ['czy.custom.MenuButton','czy.custom.MenuItem'],
	

	addNewTab : function(id, title, url) {

		var tabs = Ext.getCmp('infoPanel');

		if (tabs) {
			var _tab = tabs.getComponent(id);

			if (_tab) {
				document.getElementById('iframe_' + id).src = url;
			} else {
				_tab = tabs.add(new Ext.panel.Panel({
					id : id,
					closable : true,
					title : title,
					html : "<iframe id='iframe_"
							+ id
							+ "' width='100%' height='100%' frameborder='0' src='"
							+ url + "'></iframe>"
				}));
			}
			tabs.setActiveTab(_tab);
		}

	},

	deleteTab : function(id) {
		var tabs = Ext.getCmp("infoPanel");

		if (tabs) {
			var _tab = tabs.getComponent(id);

			if (_tab) {
				tabs.remove(_tab);
			} else {
				alert("都没了，删除啥");
			}
		}

	},
	
	test:function(){
		alert("123");
	}

});