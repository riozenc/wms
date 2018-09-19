/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:05:46
 *    Title:wms.webapp.wrk.service.ITaskService.java
 **/
package wms.webapp.wrk.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.TaskDomain;

public interface ITaskService extends BaseService<TaskDomain> {

	public int insert(TaskDomain taskDomain, Long projectId);

	public List<TaskDomain> getTasksByProject(ProjectDomain projectDomain);
}
