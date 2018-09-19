/**
 *    Auth:riozenc
 *    Date:2018年5月11日 上午10:23:15
 *    Title:cis.common.security.filter.KickoutSessionFilter.java
 **/
package wms.common.security.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riozenc.quicktool.shiro.cache.LoginSessionCache;

@Service
public class KickoutSessionFilter extends AdviceFilter {

	/**
	 * The static logger available to this class only
	 */
	private static final Logger log = LoggerFactory.getLogger(AdviceFilter.class);

	@Autowired
	private SessionManager sessionManager;

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	/**
	 * Returns {@code true} if the filter chain should be allowed to continue,
	 * {@code false} otherwise. It is called before the chain is actually
	 * consulted/executed.
	 * <p/>
	 * The default implementation returns {@code true} always and exists as a
	 * template method for subclasses.
	 *
	 * @param request  the incoming ServletRequest
	 * @param response the outgoing ServletResponse
	 * @return {@code true} if the filter chain should be allowed to continue,
	 *         {@code false} otherwise.
	 * @throws Exception if there is any error.
	 */
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("KickoutSessionFilter preHandle");

		ShiroHttpServletRequest shiroHttpServletRequest = ((ShiroHttpServletRequest) request);
		String uri = shiroHttpServletRequest.getRequestURI();

		if (uri.indexOf("login") > -1) {

		} else {
			return false;
		}

		if (sessionManager instanceof DefaultWebSessionManager) {
			DefaultWebSessionManager defaultWebSessionManager = (DefaultWebSessionManager) sessionManager;

			SessionDAO sessionDAO = defaultWebSessionManager.getSessionDAO();
			Collection<Session> sessions = sessionDAO.getActiveSessions();

			String loginName = request.getParameter(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);

			if (loginName == null) {
				return true;
			}

			Serializable oldSessionId = LoginSessionCache.get(loginName);
			if (oldSessionId == null) {
				return true;
			}

			if (oldSessionId.equals(shiroHttpServletRequest.getRequestedSessionId())) {
				return false;
			}

			for (Session session : sessions) {
				if (oldSessionId.equals(session.getId())) {
					session.setTimeout(0);// 设置session立即失效，即将其踢出系统
					System.out.println("踢了" + session.getId());
					break;
				}
			}
		}

		// org.apache.shiro.session.Session session =
		// SecurityUtils.getSubject().getSession();
		//
		// System.out.println(sessionManager);
		// DelegatingSession delegatingSession = (DelegatingSession)
		// sessionManager
		// .getSession(new WebSessionKey(session.getId(), request, response));
		//
		//
		//
		// delegatingSession.stop();

		return true;
	}

	/**
	 * Allows 'post' advice logic to be called, but only if no exception occurs
	 * during filter chain execution. That is, if {@link #executeChain executeChain}
	 * throws an exception, this method will never be called. Be aware of this when
	 * implementing logic. Most resource 'cleanup' behavior is often done in the
	 * {@link #afterCompletion(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Exception)
	 * afterCompletion(request,response,exception)} implementation, which is
	 * guaranteed to be called for every request, even when the chain processing
	 * throws an Exception.
	 * <p/>
	 * The default implementation does nothing (no-op) and exists as a template
	 * method for subclasses.
	 *
	 * @param request  the incoming ServletRequest
	 * @param response the outgoing ServletResponse
	 * @throws Exception if an error occurs.
	 */
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("KickoutSessionFilter postHandle");
	}

	/**
	 * Called in all cases in a {@code finally} block even if {@link #preHandle
	 * preHandle} returns {@code false} or if an exception is thrown during filter
	 * chain processing. Can be used for resource cleanup if so desired.
	 * <p/>
	 * The default implementation does nothing (no-op) and exists as a template
	 * method for subclasses.
	 *
	 * @param request   the incoming ServletRequest
	 * @param response  the outgoing ServletResponse
	 * @param exception any exception thrown during {@link #preHandle preHandle},
	 *                  {@link #executeChain executeChain}, or {@link #postHandle
	 *                  postHandle} execution, or {@code null} if no exception was
	 *                  thrown (i.e. the chain processed successfully).
	 * @throws Exception if an error occurs.
	 */
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		System.out.println("KickoutSessionFilter afterCompletion");
	}

	/**
	 * Actually executes the specified filter chain by calling
	 * <code>chain.doFilter(request,response);</code>.
	 * <p/>
	 * Can be overridden by subclasses for custom logic.
	 *
	 * @param request  the incoming ServletRequest
	 * @param response the outgoing ServletResponse
	 * @param chain    the filter chain to execute
	 * @throws Exception if there is any error executing the chain.
	 */
	protected void executeChain(ServletRequest request, ServletResponse response, FilterChain chain) throws Exception {
		System.out.println("KickoutSessionFilter executeChain");
	}

	/**
	 * Actually implements the chain execution logic, utilizing
	 * {@link #preHandle(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 * pre},
	 * {@link #postHandle(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 * post}, and
	 * {@link #afterCompletion(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Exception)
	 * after} advice hooks.
	 *
	 * @param request  the incoming ServletRequest
	 * @param response the outgoing ServletResponse
	 * @param chain    the filter chain to execute
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an IO error occurs
	 */
	public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("KickoutSessionFilter doFilterInternal");
		Exception exception = null;

		try {

			boolean continueChain = preHandle(request, response);
			if (log.isTraceEnabled()) {
				log.trace("Invoked preHandle method.  Continuing chain?: [" + continueChain + "]");
			}

			if (continueChain) {
				executeChain(request, response, chain);
			}

			postHandle(request, response);
			if (log.isTraceEnabled()) {
				log.trace("Successfully invoked postHandle method");
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			exception = e;
		} finally {
			cleanup(request, response, exception);
		}
	}

	/**
	 * Executes cleanup logic in the {@code finally} code block in the
	 * {@link #doFilterInternal(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * doFilterInternal} implementation.
	 * <p/>
	 * This implementation specifically calls
	 * {@link #afterCompletion(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Exception)
	 * afterCompletion} as well as handles any exceptions properly.
	 *
	 * @param request  the incoming {@code ServletRequest}
	 * @param response the outgoing {@code ServletResponse}
	 * @param existing any exception that might have occurred while executing the
	 *                 {@code FilterChain} or pre or post advice, or {@code null} if
	 *                 the pre/chain/post execution did not throw an
	 *                 {@code Exception}.
	 * @throws ServletException if any exception other than an {@code IOException}
	 *                          is thrown.
	 * @throws IOException      if the pre/chain/post execution throw an
	 *                          {@code IOException}
	 */
	protected void cleanup(ServletRequest request, ServletResponse response, Exception existing)
			throws ServletException, IOException {
		System.out.println("KickoutSessionFilter cleanup");
		Exception exception = existing;
		try {
			afterCompletion(request, response, exception);
			if (log.isTraceEnabled()) {
				log.trace("Successfully invoked afterCompletion method.");
			}
		} catch (Exception e) {
			if (exception == null) {
				exception = e;
			} else {
				log.debug("afterCompletion implementation threw an exception.  This will be ignored to "
						+ "allow the original source exception to be propagated.", e);
			}
		}
		if (exception != null) {
			if (exception instanceof ServletException) {
				throw (ServletException) exception;
			} else if (exception instanceof IOException) {
				throw (IOException) exception;
			} else {
				if (log.isDebugEnabled()) {
					String msg = "Filter execution resulted in an unexpected Exception "
							+ "(not IOException or ServletException as the Filter API recommends).  "
							+ "Wrapping in ServletException and propagating.";
					log.debug(msg);
				}
				throw new ServletException(exception);
			}
		}
	}

}