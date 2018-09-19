/**
 *    Auth:riozenc
 *    Date:2018年5月15日 下午5:02:26
 *    Title:cis.support.webservice.CXFSpringFilterFactoryBean.java
 **/
package wms.support.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.ServletController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CXFSpringFilterFactoryBean implements FactoryBean, BeanPostProcessor {
	private static transient final Logger log = LoggerFactory.getLogger(CXFSpringFilterFactoryBean.class);

	private ServletController controller;
	private Bus bus;
	private ClassLoader loader;
	private AbstractCXFFilter instance;
	
	public CXFSpringFilterFactoryBean() {
		loader = Thread.currentThread().getContextClassLoader();
	}

	public ServletController getController() {
		return controller;
	}

	public void setController(ServletController controller) {
		this.controller = controller;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public ClassLoader getLoader() {
		return loader;
	}

	public void setLoader(ClassLoader loader) {
		this.loader = loader;
	}

	@Override
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		if (instance == null) {
			instance = createInstance();
		}
		return instance;
	}

	@Override
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return SpringCXFFilter.class;
	}

	protected AbstractCXFFilter createInstance() throws Exception {

		log.debug("Creating CXF Filter instance.");

		ServletController controller = getController();
		if (controller == null) {
			String msg = "ServletController property must be set.";
			throw new BeanInitializationException(msg);
		}
		Bus bus = getBus();
		if (bus == null || !(bus instanceof SpringBus)) {
			String msg = "The bus does not implement the SpringBus interface.";
			throw new BeanInitializationException(msg);
		}
		ClassLoader classLoader = getLoader();
		if (classLoader == null) {
			String msg = "ClassLoader property must be set.";
			throw new BeanInitializationException(msg);
		}

		return new SpringCXFFilter(controller, bus, classLoader);
	}

	private static final class SpringCXFFilter extends AbstractCXFFilter {

		protected SpringCXFFilter(ServletController controller, Bus bus, ClassLoader loader) {
			super();
			if (controller == null) {
				throw new IllegalArgumentException("controller property cannot be null.");
			}
			setController(controller);
			if (bus == null) {
				throw new IllegalArgumentException("bus property cannot be null.");
			}
			setBus(bus);
			if (loader == null) {
				throw new IllegalArgumentException("loader property cannot be null.");
			}
			setLoader(loader);
		}
	}

}
