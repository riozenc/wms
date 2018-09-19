Ext.Ajax.on('requestcomplete', checkUserSessionStatus, this);
function checkUserSessionStatus(conn, response, options) {
	var status = response.getResponseHeader("sessionStatus");
	if (status == "timeOut") {
		window.location.href = "login.html";
	}
}