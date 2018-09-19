var myData = [[1, 'gloomyfish', 'bfnh1998@hotmail.com'],
		[2, 'Bob Denoy', 'bobwindy@yahoo.com'],
		[3, 'Rose Kate', 'kittygroup@facebook.com']];

Ext.define('langhua.store.textStore', {

	extend : 'Ext.data.Store',

	alias : 'widget.textStore',

	buffered : true,
	model : 'textModel',
	autoLoad : true,
	data : myData
});