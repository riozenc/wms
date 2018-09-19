/**
 *    Auth:riozenc
 *    Date:2018年8月13日 下午2:50:56
 *    Title:cis.webapp.sys.service.impl.RoleServiceImpl.java
 **/
package wms.webapp.sys.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;

import wms.webapp.sys.dao.RoleDAO;
import wms.webapp.sys.domain.RoleDomain;
import wms.webapp.sys.service.IRoleService;

@TransactionService
public class RoleServiceImpl implements IRoleService {

	@TransactionDAO
	private RoleDAO roleDAO;

	@Override
	public int insert(RoleDomain t) {
		// TODO Auto-generated method stub
		return roleDAO.insert(t);
	}

	@Override
	public int delete(RoleDomain t) {
		// TODO Auto-generated method stub
		return roleDAO.delete(t);
	}

	@Override
	public int update(RoleDomain t) {
		// TODO Auto-generated method stub
		return roleDAO.update(t);
	}

	@Override
	public RoleDomain findByKey(RoleDomain t) {
		// TODO Auto-generated method stub
		return roleDAO.findByKey(t);
	}

	@Override
	public List<RoleDomain> findByWhere(RoleDomain t) {
		// TODO Auto-generated method stub
		return roleDAO.findByWhere(t);
	}

}
