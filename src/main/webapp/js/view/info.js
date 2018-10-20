Ext.define('czy.view.info', {
	extend : 'Ext.tab.Panel',

	id : 'infoPanel',
	alias : 'widget.info',
	tabPosition : 'bottom',

	items : [ {
		title : 'Home',
		html : 'Home Screen'
	}],

	loadPanel : function(menu) {
		var id =  'menuTab-' + menu.id;
		var tab = this.getComponent(id);

		if (tab) {
			this.setActiveTab(tab);
			document.getElementById('iframe-' + menu.id).src = menu.menuUrl + '&button=' + menu.button;
		} else {
			var p = this.add(new Ext.panel.Panel({
				
				id : id,
				title : menu.text,
				closable : true,
				html : "<iframe id='iframe-" + menu.id
						+ "' width='100%' height='100%' frameborder='0' src='"
						+ menu.menuUrl + "&button=" + menu.button + "'></iframe>"
			}));
			this.setActiveTab(p);
		}

	}
});
