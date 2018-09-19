/**
 *    Auth:riozenc
 *    Date:2018年6月19日 下午4:16:17
 *    Title:cis.webapp.sys.dao.MenuDAO.java
 **/
package wms.webapp.sys.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.sys.domain.MenuDomain;
@TransactionDAO
public class MenuDAO extends AbstractTransactionDAOSupport implements BaseDAO<MenuDomain> {

	@Override
	public int insert(MenuDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MenuDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MenuDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".update", t);
	}

	@Override
	public MenuDomain findByKey(MenuDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<MenuDomain> findByWhere(MenuDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<MenuDomain> getMenu(MenuDomain menuDomain) {
		return getPersistanceManager().find(getNamespace() + ".getMenu", menuDomain);
	}

}
