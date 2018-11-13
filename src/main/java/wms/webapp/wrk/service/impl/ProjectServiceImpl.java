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
import wms.webapp.wrk.dao.TaskDAO;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.TaskDomain;
import wms.webapp.wrk.service.IProjectService;

@TransactionService
public class ProjectServiceImpl implements IProjectService {

	@TransactionDAO
	private ProjectDAO projectDAO;
	@TransactionDAO
	private TaskDAO taskDAO;

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

	@Override
	public List<ProjectDomain> getProjects(ProjectDomain projectDomain) {
		// TODO Auto-generated method stub
		List<ProjectDomain> list = projectDAO.getProjects(projectDomain);
		return list;
	}

	/**
	 * 获取项目的信息 任务总数、已完成任务数量、未完成任务数量
	 */
	@Override
	public ProjectDomain getProjectInfo(ProjectDomain projectDomain) {
		// TODO Auto-generated method stub
		List<TaskDomain> taskList = taskDAO.getTasksByProject(projectDomain);
		long completedTasks = taskList.stream().filter(t->t.getStatus()!=null).filter(t -> TASK_STATUS.COMPLETE.getValue() == t.getStatus()).count();// 已完成
		long unfinishedTasks = taskList.size()-completedTasks;// 未完成
		projectDomain.setTotalTasks((long) taskList.size());
		projectDomain.setCompletedTasks(completedTasks);
		projectDomain.setUnfinishedTasks(unfinishedTasks);
		return projectDomain;
	}

	enum TASK_STATUS {
		COMPLETE(1), UNFINISH(0);

		private int value;

		TASK_STATUS(int value) {
			this.value = value;
		}

		int getValue() {
			return value;
		}

	}

}
