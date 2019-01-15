/**
 *    Auth:riozenc
 *    Date:2018年6月19日 下午3:08:47
 *    Title:cis.webapp.sys.action.MenuAction.java
 **/
package wms.webapp.sys.action;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.shiro.Principal;
import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.webapp.sys.domain.MenuDomain;
import wms.webapp.sys.service.IMenuService;

/**
 * 菜单
 * 
 * @author riozenc
 *
 */

@ControllerAdvice(assignableTypes = MenuAction.class)
@RequestMapping("menu")
public class MenuAction extends BaseAction {
	@Autowired
	@Qualifier("menuServiceImpl")
	private IMenuService menuService;

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "menu.jsp";
	}

	@ResponseBody
	@RequestMapping(params = "method=getRootMenu")
	public Object getRootMenu(MenuDomain menuDomain) {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		menuDomain.setParentId(0);
		menuDomain.setUserId(principal.getUserId());
		List<MenuDomain> list = menuService.getMenu(menuDomain);
		return list;
	}

	@ResponseBody
	@RequestMapping(params = "method=getSubMenu")
	public Object getSubMenu(MenuDomain menuDomain) {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		menuDomain.setUserId(principal.getUserId());
		List<MenuDomain> list = menuService.getMenu(menuDomain);
		return list;
	}

	@ResponseBody
	@RequestMapping(params = "method=getMenu")
	public Object getMenu() {
		MenuDomain menuDomain = new MenuDomain();
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		menuDomain.setUserId(principal.getUserId());
		List<MenuDomain> list = menuService.getMenu(menuDomain);
		return list;
	}

}
