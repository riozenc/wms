/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:26:14
 *    Title:wms.webapp.wrk.service.impl.TaskServiceImpl.java
 **/
package wms.webapp.wrk.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;
import com.riozenc.quicktool.shiro.Principal;

import wms.webapp.sys.domain.UserDomain;
import wms.webapp.wrk.dao.ProjectTaskDAO;
import wms.webapp.wrk.dao.TaskDAO;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.ProjectTaskDomain;
import wms.webapp.wrk.domain.TaskDomain;
import wms.webapp.wrk.service.ITaskService;

@TransactionService
public class TaskServiceImpl implements ITaskService {

	@TransactionDAO
	private TaskDAO taskDAO;
	@TransactionDAO
	private ProjectTaskDAO projectTaskDAO;

	@Override
	public int delete(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return taskDAO.delete(arg0);
	}

	@Override
	public TaskDomain findByKey(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return taskDAO.findByKey(arg0);
	}

	@Override
	public List<TaskDomain> findByWhere(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return taskDAO.findByWhere(arg0);
	}

	@Override
	public int insert(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return taskDAO.insert(arg0);
	}

	@Override
	public int update(TaskDomain arg0) {
		// TODO Auto-generated method stub
		return taskDAO.update(arg0);
	}

	@Override
	public int insert(TaskDomain taskDomain, Long projectId) {
		// TODO Auto-generated method stub
		
		taskDAO.insert(taskDomain);
		ProjectTaskDomain projectTaskDomain = new ProjectTaskDomain();
		projectTaskDomain.setTaskId(taskDomain.getId());
		projectTaskDomain.setProjectId(projectId);
		projectTaskDomain.setCreateDate(new Date());
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		projectTaskDomain.setOperatorId(principal.getId());
		return projectTaskDAO.insert(projectTaskDomain);
	}

	@Override
	public List<TaskDomain> getTasksByProject(ProjectDomain projectDomain) {
		// TODO Auto-generated method stub
		return taskDAO.getTasksByProject(projectDomain);
	}

	@Override
	public List<TaskDomain> getTasksByUser(UserDomain userDomain) {
		// TODO Auto-generated method stub
		return taskDAO.getTasksByUser(userDomain);
	}

}
