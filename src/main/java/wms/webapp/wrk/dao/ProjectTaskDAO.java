/**
 *    Auth:riozenc
 *    Date:2018年9月6日 上午10:31:22
 *    Title:wms.webapp.wrk.dao.ProjectTaskDAO.java
 **/
package wms.webapp.wrk.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.wrk.domain.ProjectTaskDomain;

@TransactionDAO
public class ProjectTaskDAO extends AbstractTransactionDAOSupport implements BaseDAO<ProjectTaskDomain> {

	@Override
	public int delete(ProjectTaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", arg0);
	}

	@Override
	public ProjectTaskDomain findByKey(ProjectTaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", arg0);
	}

	@Override
	public List<ProjectTaskDomain> findByWhere(ProjectTaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", arg0);
	}

	@Override
	public int insert(ProjectTaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", arg0);
	}

	@Override
	public int update(ProjectTaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", arg0);
	}

}
