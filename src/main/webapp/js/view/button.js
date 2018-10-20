Ext.define('czy.view.button', {
	extend : 'Ext.button.Button',

	alias : 'widget.menuButton',

	scale : 'medium',

	
	padding : 5,

	constructor : function(config) {
		config.text = '<h1>' + config.text + '</h1>';

		this.callParent(arguments); // calls Ext.panel.Panel's constructor
		// ...
	}

});
