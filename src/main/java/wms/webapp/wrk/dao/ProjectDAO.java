/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午4:18:58
 *    Title:wms.webapp.sys.dao.ProjectDAO.java
 **/
package wms.webapp.wrk.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.PaginationSupport;
import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.wrk.domain.ProjectDomain;

@TransactionDAO
public class ProjectDAO extends AbstractTransactionDAOSupport implements BaseDAO<ProjectDomain> {

	@Override
	public int delete(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", arg0);
	}

	@Override
	public ProjectDomain findByKey(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", arg0);
	}

	@PaginationSupport
	@Override
	public List<ProjectDomain> findByWhere(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", arg0);
	}

	@Override
	public int insert(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", arg0);
	}

	@Override
	public int update(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", arg0);
	}
	
	public List<ProjectDomain> getProjects(ProjectDomain projectDomain){
		return getPersistanceManager().find(getNamespace() + ".findByWhere", projectDomain);
	}

	public ProjectDomain getProjectInfo(ProjectDomain projectDomain) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".getProjectInfo", projectDomain);
	}
}
