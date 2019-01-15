/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:26:14
 *    Title:wms.webapp.wrk.service.impl.TaskServiceImpl.java
 **/
package wms.webapp.wrk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.shiro.SecurityUtils;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;
import com.riozenc.quicktool.shiro.Principal;

import wms.webapp.rtm.domain.RewardTaskDomain;
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
		projectTaskDomain.setOperatorId(principal.getUserId());
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

	@Override
	public List<TaskDomain> getTasksByMap(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return taskDAO.getTasksByMap(params);
	}

	/**
	 * 任务 N->R 悬赏 0->1
	 */
	@Override
	public int releaseRewardTasks(List<TaskDomain> taskDomains) {
		// TODO Auto-generated method stub
		List<RewardTaskDomain> list = new ArrayList<>();
		Stream<TaskDomain> stream = taskDomains.stream().filter(TaskDomain::isNoR);

		if (stream.count() == 0) {
			return 0;
		}
		stream.forEach(task -> {
			task.setStatus(TaskDomain.TASK_STATUS.R.getStatus());
			RewardTaskDomain rewardTaskDomain = new RewardTaskDomain();
			rewardTaskDomain.setId(task.getId());
			rewardTaskDomain.setCreateDate(new Date());
			list.add(rewardTaskDomain);
		});
		taskDAO.release(taskDomains);
		return taskDAO.releaseRewardTasks(list);
	}
}
