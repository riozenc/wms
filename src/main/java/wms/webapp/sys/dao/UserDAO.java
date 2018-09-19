/**
 *    Auth:riozenc
 *    Date:2018年5月10日 下午5:51:46
 *    Title:cis.webapp.sys.dao.UserDAO.java
 **/
package wms.webapp.sys.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.sys.domain.UserDomain;

@TransactionDAO
public class UserDAO extends AbstractTransactionDAOSupport implements BaseDAO<UserDomain> {

	@Override
	public int insert(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public UserDomain findByKey(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<UserDomain> findByWhere(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public String getPassword(UserDomain userDomain) {
		return getPersistanceManager().load(getNamespace() + ".getPassword", userDomain);
	}

}
