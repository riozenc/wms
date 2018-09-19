/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:20:28
 *    Title:wms.webapp.wrk.service.impl.ProjectServiceImpl.java
 **/
package wms.webapp.wrk.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;

import wms.webapp.wrk.dao.ProjectDAO;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.service.IProjectService;

@TransactionService
public class ProjectServiceImpl implements IProjectService {

	@TransactionDAO
	private ProjectDAO projectDAO;

	@Override
	public int delete(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return projectDAO.delete(arg0);
	}

	@Override
	public ProjectDomain findByKey(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return projectDAO.findByKey(arg0);
	}

	@Override
	public List<ProjectDomain> findByWhere(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return projectDAO.findByWhere(arg0);
	}

	@Override
	public int insert(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return projectDAO.insert(arg0);
	}

	@Override
	public int update(ProjectDomain arg0) {
		// TODO Auto-generated method stub
		return projectDAO.update(arg0);
	}

}
