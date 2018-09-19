/**
 *    Auth:riozenc
 *    Date:2018年5月10日 上午10:33:20
 *    Title:cis.web.interceptor.BaseInterceptor.java
 **/
package wms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.riozenc.quicktool.springmvc.interceptor.DefaultInterceptor;

import wms.common.security.filter.PasswordShiroFilter;

public class BaseInterceptor extends DefaultInterceptor {
	
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(httpServletRequest, httpServletResponse, object, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		// TODO Auto-generated method stub

//		String username = WebUtils.getCleanParam(httpServletRequest, PasswordShiroFilter.DEFAULT_USERNAME_PARAM);
//		if (StringUtils.isEmpty(username)) {
////			throw new AuthenticationException("账号为空,非法请求.");
//			return false;
//		}

		return super.preHandle(httpServletRequest, httpServletResponse, object);
	}

}
