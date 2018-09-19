/**
 *    Auth:riozenc
 *    Date:2018年5月14日 下午4:17:39
 *    Title:cis.support.webservice.SpringCXFServlet.java
 **/
package wms.support.webservice;

import java.io.InputStream;

import javax.servlet.ServletConfig;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.riozenc.quicktool.springmvc.context.SpringContextHolder;

public class SpringCXFServlet extends CXFServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2421201091512819958L;

	private static final String BUS_PARAMETER = "bus";
	
	private boolean busCreated;

	@Override
	protected void loadBus(ServletConfig servletConfig) {
		// TODO Auto-generated method stub

		ApplicationContext wac = SpringContextHolder.getApplicationContext();

		if (wac instanceof AbstractApplicationContext) {
			addListener((AbstractApplicationContext) wac);
		}

		String configLocation = servletConfig.getInitParameter("config-location");
		if (configLocation == null) {
			try {
				InputStream is = servletConfig.getServletContext().getResourceAsStream("/WEB-INF/cxf-servlet.xml");
				if (is != null && is.available() > 0) {
					is.close();
					configLocation = "/WEB-INF/cxf-servlet.xml";
				}
			} catch (Exception ex) {
				// ignore
			}
		}

		if (wac != null) {
			String busParam = servletConfig.getInitParameter(BUS_PARAMETER);
			String busName = busParam == null ? "cxf" : busParam.trim();

			setBus((Bus) wac.getBean(busName, Bus.class));
		} else {
			busCreated = true;
			setBus(BusFactory.newInstance().createBus());
		}

	}

	@Override
	public void destroyBus() {
		// TODO Auto-generated method stub
		if (busCreated) {
			// if we created the Bus, we need to destroy it. Otherwise, spring will
			// handleit.
			getBus().shutdown(true);
			setBus(null);
		}
	}
}
