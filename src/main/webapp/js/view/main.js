Ext.define('czy.view.main', {
	extend : 'Ext.container.Viewport',

	alias : 'widget.main',

	layout : 'border',

	items : [ {
		region : 'north',
		html : '<h1 class="x-panel-header">Page Title</h1>',
		border : false

	}, {
		region : 'west',
		collapsible : true,
		title : 'Navigation'
		
	// could use a TreePanel or AccordionLayout for navigational items
	}, {
		region : 'south',
		title : 'South Panel',
		collapsible : true,
		html : 'Information goes here',
		split : true
	}, {
		region : 'east',
		title : 'East Panel',
		collapsible : true,
		split : true
	}, {
		region : 'center',
		xtype : 'tabpanel', // TabPanel itself has no title
		activeTab : 0, // First tab active by default
		items : {
			title : 'Default Tab',
			html : 'The first tab\'s content. Others may be added dynamically'
		}
	} ]

});