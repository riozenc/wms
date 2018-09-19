/**
 *    Auth:riozenc
 *    Date:2018年7月27日 下午2:25:31
 *    Title:cis.web.interceptor.AuthenticatingInterceptor.java
 **/
package wms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 认证拦截器 url白名单 用户信息认证
 * 
 * @author riozenc
 *
 */
public class AuthenticatingInterceptor extends com.riozenc.quicktool.springmvc.interceptor.AuthenticatingInterceptor {

	@Override
	protected String getLogin() {
		// TODO Auto-generated method stub
		return "login.html";
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, Exception exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(httpServletRequest, httpServletResponse, object);
	}

}
