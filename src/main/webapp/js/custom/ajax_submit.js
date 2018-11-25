function ajaxSubmit(url,params,successCallback,failureCallback){
	Ext.MessageBox.wait('Loadng','Please Wait...');
	Ext.MessageBox.updateProgress(1);
	Ext.Ajax.request({
		url : url,
		params : params,
		method : 'POST',
		success : function(response,config) {
			successCallback(response,config);
		},
		failure: function(response, opts) {
			failureCallback(response,config);
		},
		dataType : 'json'
	});
}