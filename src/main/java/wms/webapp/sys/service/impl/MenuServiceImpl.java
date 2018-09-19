/**
 *    Auth:riozenc
 *    Date:2018年6月19日 下午4:37:24
 *    Title:cis.webapp.sys.service.impl.MenuServiceImpl.java
 **/
package wms.webapp.sys.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;

import wms.webapp.sys.dao.MenuDAO;
import wms.webapp.sys.domain.MenuDomain;
import wms.webapp.sys.service.IMenuService;

@TransactionService
public class MenuServiceImpl implements IMenuService {

	@TransactionDAO
	private MenuDAO menuDAO;

	@Override
	public int insert(MenuDomain t) {
		// TODO Auto-generated method stub
		return menuDAO.insert(t);
	}

	@Override
	public int delete(MenuDomain t) {
		// TODO Auto-generated method stub
		return menuDAO.delete(t);
	}

	@Override
	public int update(MenuDomain t) {
		// TODO Auto-generated method stub
		return menuDAO.update(t);
	}

	@Override
	public MenuDomain findByKey(MenuDomain t) {
		// TODO Auto-generated method stub
		return menuDAO.findByKey(t);
	}

	@Override
	public List<MenuDomain> findByWhere(MenuDomain t) {
		// TODO Auto-generated method stub
		return menuDAO.findByWhere(t);
	}

	@Override
	public List<MenuDomain> getMenu(MenuDomain menuDomain) {
		// TODO Auto-generated method stub
		return menuDAO.getMenu(menuDomain);
	}

}
