/**
 *    Auth:riozenc
 *    Date:2018年5月11日 上午8:52:56
 *    Title:cis.common.security.realm.PasswordShiroRealm.java
 **/
package wms.common.security.realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.riozenc.quicktool.shiro.Principal;
import com.riozenc.quicktool.shiro.realm.AbstractPasswordShiroRealm;

import wms.webapp.sys.domain.UserDomain;
import wms.webapp.sys.service.IUserService;

@Service
public class PasswordShiroRealm extends AbstractPasswordShiroRealm {
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Override
	public String getPassword(String loginName) {
		// TODO Auto-generated method stub
		// 判断loginName
		
		UserDomain userDomain = new UserDomain();
		userDomain.setUserAccount(loginName);
		return userService.getPassword(userDomain);
	}

	@Override
	protected String getHashAlgorithmName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHashIterations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object createPrincipal(String loginName) {
		// TODO Auto-generated method stub
		Principal principal = new Principal();
		UserDomain userDomain = new UserDomain();
		userDomain.setUserAccount(loginName);
		userDomain = userService.findByKey(userDomain);
		userDomain.setPassword("Want password?");
		principal.setUserId(userDomain.getId());
		principal.setUserAccount(userDomain.getUserAccount());
		principal.setUserName(userDomain.getUserName());
		principal.setPhone(userDomain.getPhone());
		principal.setMailAddress(userDomain.getMailAddress());
		principal.setSex(userDomain.getSex());
		principal.setImageUrl(userDomain.getImageUrl());

		return principal;
	}

}
