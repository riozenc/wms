/**
 *    Auth:riozenc
 *    Date:2018年5月11日 上午8:57:30
 *    Title:cis.webapp.sys.service.impl.UserServiceImpl.java
 **/
package wms.webapp.sys.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;
import com.riozenc.quicktool.common.util.cryption.en.WebPasswordUtils;

import wms.webapp.sys.dao.UserDAO;
import wms.webapp.sys.domain.UserDomain;
import wms.webapp.sys.service.IUserService;

@TransactionService
public class UserServiceImpl implements IUserService {

	@TransactionDAO
	private UserDAO userDAO;

	@Override
	public int insert(UserDomain t) {
		// TODO Auto-generated method stub
		t.setPassword(WebPasswordUtils.encodePassword(t.getPassword()));
		return userDAO.insert(t);
	}

	@Override
	public int delete(UserDomain t) {
		// TODO Auto-generated method stub
		return userDAO.delete(t);
	}

	@Override
	public int update(UserDomain t) {
		// TODO Auto-generated method stub
		if(t.getPassword()!=null) {
			t.setPassword(WebPasswordUtils.encodePassword(t.getPassword()));
		}
		return userDAO.update(t);
	}

	@Override
	public UserDomain findByKey(UserDomain t) {
		// TODO Auto-generated method stub
		return userDAO.findByKey(t);
	}

	@Override
	public List<UserDomain> findByWhere(UserDomain t) {
		// TODO Auto-generated method stub
		return userDAO.findByWhere(t);
	}

	@Override
	public String getPassword(UserDomain userDomain) {
		// TODO Auto-generated method stub
		return userDAO.getPassword(userDomain);
	}

}
