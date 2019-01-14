/**
 *    Auth:riozenc
 *    Date:2018年5月14日 下午2:42:34
 *    Title:cis.webapp.sys.action.LoginAction.java
 **/
package wms.webapp.sys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.StringUtils;
import com.riozenc.quicktool.shiro.Principal;
import com.riozenc.quicktool.shiro.cache.LoginFailCache;
import com.riozenc.quicktool.shiro.cache.LoginSessionCache;

import wms.common.security.filter.PasswordShiroFilter;
import wms.web.result.HttpResult;

@ControllerAdvice
@RequestMapping("login")
public class LoginAction {
	@CrossOrigin(origins="http://172.21.29.43:8080",
			 methods= {RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST},
		     allowCredentials="true",
		     allowedHeaders="Origin, Access-Token,X-Requested-With, Content-Type, Accept,x-access-token,x-url-path",
			 maxAge=3600)
	@ResponseBody
	@RequestMapping(value = "/login")
	public Object login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	

		String errorClassName = (String) httpServletRequest
				.getAttribute(PasswordShiroFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (errorClassName == null) {
			// 成功
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();

			if (principal == null) {
				// 非法请求
				return loginFail("IncorrectCredentialsException", httpServletRequest, httpServletResponse);
			}

			LoginSessionCache.put(principal.getUserId(), subject.getSession().getId());

			return new HttpResult(HttpResult.SUCCESS, "登录成功,欢迎" + principal.getUserName() + "!");
		} else {
			// 失败
			return loginFail(errorClassName, httpServletRequest, httpServletResponse);
		}
	}

	public Object loginFail(String errorClassName, HttpServletRequest request, HttpServletResponse response) {

		String username = WebUtils.getCleanParam(request, PasswordShiroFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, PasswordShiroFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, PasswordShiroFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String) request.getAttribute(PasswordShiroFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(PasswordShiroFilter.DEFAULT_MESSAGE_PARAM);

		if (StringUtils.isBlank(message)) {
			message = "用户或密码错误, 请重试.";
		}

		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)) {
			if (!StringUtils.isBlank(username) && isValidateCodeLogin(username, true, false)) {
				request.getSession().setAttribute("isValidateCodeLogin", true);
			}
		}

		// 验证失败清空验证码
		// request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE,
		// IdGen.uuid());
		return new HttpResult(HttpResult.ERROR, message);
	}

	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {

		Integer loginFailNum = LoginFailCache.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			LoginFailCache.put(useruame, loginFailNum);
		}
		if (clean) {
			LoginFailCache.remove(useruame);
		}
		return loginFailNum >= 3;
	}
	
	@ResponseBody
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	@CrossOrigin(origins="http://172.21.29.43:8080",
				 methods= {RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST},
			     allowCredentials="true",
			     allowedHeaders="Origin, Access-Token,X-Requested-With, Content-Type, Accept,x-access-token,x-url-path",
				 maxAge=3600)
	public Principal getUser() {
		Principal user = (Principal) SecurityUtils.getSubject().getPrincipal();
		
		return user;
	}
}
