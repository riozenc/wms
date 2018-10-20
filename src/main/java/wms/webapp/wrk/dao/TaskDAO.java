/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午4:13:14
 *    Title:wms.webapp.sys.dao.TaskDAO.java
 **/
package wms.webapp.wrk.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.sys.domain.UserDomain;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.TaskDomain;

@TransactionDAO
public class TaskDAO extends AbstractTransactionDAOSupport implements BaseDAO<TaskDomain> {

	@Override
	public int delete(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", arg0);
	}

	@Override
	public TaskDomain findByKey(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", arg0);
	}

	@Override
	public List<TaskDomain> findByWhere(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", arg0);
	}

	@Override
	public int insert(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", arg0);
	}

	@Override
	public int update(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", arg0);
	}

	public List<TaskDomain> getTasksByProject(ProjectDomain projectDomain) {
		return getPersistanceManager().find(getNamespace() + ".getTasksByProject", projectDomain);
	}

	public List<TaskDomain> getTasksByUser(UserDomain userDomain) {
		return getPersistanceManager().find(getNamespace() + ".getTasksByUser", userDomain);
	}
}
