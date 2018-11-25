/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:27:30
 *    Title:wms.webapp.wrk.action.TaskAction.java
 **/
package wms.webapp.wrk.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.quicktool.common.util.json.JSONUtil;
import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.web.result.HttpResult;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.TaskDomain;
import wms.webapp.wrk.service.ITaskService;

@ControllerAdvice(assignableTypes = TaskAction.class)
@RequestMapping("task")
public class TaskAction extends BaseAction {
	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "task.jsp";
	}

	@Autowired
	@Qualifier("taskServiceImpl")
	private ITaskService taskService;

	@ResponseBody
	@RequestMapping(params = "method=insert")
	public Object insert(TaskDomain taskDomain, Long projectId) {

		if (projectId == null) {
			// 错误
			return null;
		}

		int i = taskService.insert(taskDomain, projectId);
		if (i > 0) {
			return new HttpResult(HttpResult.SUCCESS, taskDomain.getTaskName() + "新增成功.");
		} else {
			return new HttpResult(HttpResult.ERROR, taskDomain.getTaskName() + "新增失败.");
		}
	}

	@ResponseBody
	@RequestMapping(params = "method=getTasksByProject")
	public Object getTasksByProject(ProjectDomain projectDomain) {

		List<TaskDomain> list = taskService.getTasksByProject(projectDomain);
		return list;
	}

	@ResponseBody
	@RequestMapping(params = "method=releaseTasks")
	public Object releaseTasks(@RequestBody String data) throws JsonParseException, JsonMappingException, IOException {

		List<TaskDomain> taskDomains = JSONUtil.readValue(data, new TypeReference<List<TaskDomain>>() {
		});
		int count = taskService.releaseRewardTasks(taskDomains);

		return count;
	}

}
