/**
 *    Auth:riozenc
 *    Date:2018年6月19日 下午4:36:48
 *    Title:cis.webapp.sys.service.IMenuService.java
 **/
package wms.webapp.sys.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import wms.webapp.sys.domain.MenuDomain;

public interface IMenuService extends BaseService<MenuDomain> {
	
	public List<MenuDomain> getMenu(MenuDomain menuDomain);
}
