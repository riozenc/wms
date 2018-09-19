/**
 *    Auth:riozenc
 *    Date:2018年5月15日 下午5:04:22
 *    Title:cis.support.webservice.AbstractCXFFilter.java
 **/
package wms.support.webservice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.classloader.ClassLoaderUtils;
import org.apache.cxf.common.classloader.ClassLoaderUtils.ClassLoaderHolder;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.transport.servlet.ServletController;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

public class AbstractCXFFilter extends OncePerRequestFilter {

	private ServletController controller;
	protected Bus bus;// SpringBus
	private ClassLoader loader;

	private FilterChainResolver filterChainResolver;

	public void setController(ServletController controller) {
		this.controller = controller;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public void setLoader(ClassLoader loader) {
		this.loader = loader;
	}

	public FilterChainResolver getFilterChainResolver() {
		return filterChainResolver;
	}

	public void setFilterChainResolver(FilterChainResolver filterChainResolver) {
		this.filterChainResolver = filterChainResolver;
	}

	@Override
	protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
			final FilterChain chain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClassLoaderHolder origLoader = null;
		Bus origBus = null;
		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
			try {
				if (loader != null) {
					origLoader = ClassLoaderUtils.setThreadContextClassloader(loader);
				}
				if (bus != null) {
					origBus = BusFactory.getAndSetThreadDefaultBus(bus);
				}
				HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
				if (controller.filter(new HttpServletRequestFilter(httpRequest, "cis"),
						(HttpServletResponse) servletResponse)) {
					return;
				}
			} finally {
				if (origBus != bus) {
					BusFactory.setThreadDefaultBus(origBus);
				}
				if (origLoader != null) {
					origLoader.reset();
				}
			}
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	private static class HttpServletRequestFilter extends HttpServletRequestWrapper {
		private String filterName;
		private String servletPath;
		private String pathInfo;

		HttpServletRequestFilter(HttpServletRequest request, String filterName) {
			super(request);
			this.filterName = filterName;
		}

		@Override
		public String getServletPath() {
			if (servletPath == null) {
				try {
					Method m = ServletContext.class.getMethod("getFilterRegistration", new Class[] { String.class });
					Object registration = m.invoke(super.getServletContext(), new Object[] { filterName });
					if (registration != null) {
						m = registration.getClass().getMethod("getUrlPatternMappings", new Class[] {});
						Collection<String> mappings = CastUtils
								.cast((Collection<?>) m.invoke(registration, new Object[] {}));
						if (!mappings.isEmpty()) {
							String mapping = mappings.iterator().next();
							if (mapping.endsWith("/*")) {
								servletPath = mapping.substring(0, mapping.length() - 2);
							}
						}
					}
				} catch (Throwable ex) {
					// ignore
				}
				if (servletPath == null) {
					servletPath = "";
				}
			}

			return servletPath;
		}

		@Override
		public String getPathInfo() {
			if (pathInfo == null) {
				pathInfo = super.getPathInfo();
				if (pathInfo == null) {
					pathInfo = getRequestURI();
				}
				String prefix = super.getContextPath() + this.getServletPath();
				if (pathInfo.startsWith(prefix)) {
					pathInfo = pathInfo.substring(prefix.length());
				}
			}
			return pathInfo;
		}
	}
}
