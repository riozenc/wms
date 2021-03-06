/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:05:22
 *    Title:wms.webapp.wrk.service.IProjectService.java
 **/
package wms.webapp.wrk.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import wms.webapp.wrk.domain.ProjectDomain;

public interface IProjectService extends BaseService<ProjectDomain> {

	public List<ProjectDomain> getProjects(ProjectDomain projectDomain);

	public ProjectDomain getProjectInfo(ProjectDomain projectDomain);
}
