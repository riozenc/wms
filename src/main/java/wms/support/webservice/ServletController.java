/**
 *    Auth:riozenc
 *    Date:2018年5月16日 下午3:46:37
 *    Title:cis.support.webservice.ServletController.java
 **/
package wms.support.webservice;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.cxf.transport.http.DestinationRegistry;

public class ServletController extends org.apache.cxf.transport.servlet.ServletController {

	public void init() {

	}

	public ServletController(DestinationRegistry destinationRegistry) {
		super(destinationRegistry, null, null);
		// TODO Auto-generated constructor stub
	}

}
