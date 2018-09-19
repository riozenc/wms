/**
 *    Auth:riozenc
 *    Date:2018年8月13日 下午2:36:57
 *    Title:cis.webapp.sys.dao.RoleDAO.java
 **/
package wms.webapp.sys.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.sys.domain.RoleDomain;

@TransactionDAO
public class RoleDAO extends AbstractTransactionDAOSupport implements BaseDAO<RoleDomain>{

	@Override
	public int insert(RoleDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace()+".insert", t);
	}

	@Override
	public int delete(RoleDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace()+".delete", t);
	}

	@Override
	public int update(RoleDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace()+".update", t);
	}

	@Override
	public RoleDomain findByKey(RoleDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace()+".findByKey", t);
	}

	@Override
	public List<RoleDomain> findByWhere(RoleDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace()+".findByWhere", t);
	}

}
