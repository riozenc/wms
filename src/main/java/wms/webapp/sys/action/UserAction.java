/**
 *    Auth:riozenc
 *    Date:2018年5月14日 下午2:42:44
 *    Title:cis.webapp.sys.action.UserAction.java
 **/
package wms.webapp.sys.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.webapp.sys.domain.UserDomain;
import wms.webapp.sys.service.IUserService;

@ControllerAdvice(assignableTypes = UserAction.class)
@RequestMapping("user")
public class UserAction extends BaseAction {

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(params = "method=getUsers")
	@ResponseBody
	public Object getUsers(UserDomain userDomain) {
		List<UserDomain> list = userService.findByWhere(userDomain);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
