/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午5:02:01
 *    Title:wms.webapp.wrk.action.ProjectAction.java
 **/
package wms.webapp.wrk.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.springmvc.PageResult;
import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.web.result.HttpResult;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.service.IProjectService;

@ControllerAdvice(assignableTypes=ProjectAction.class)
@RequestMapping("project")
public class ProjectAction extends BaseAction {

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "project1.jsp";
	}

	@Autowired
	@Qualifier("projectServiceImpl")
	private IProjectService projectService;

	@ResponseBody
	@RequestMapping(params = "method=getProjects")
	public Object getProjectInfo(ProjectDomain projectDomain) {

		List<ProjectDomain> list = projectService.findByWhere(projectDomain);
		PageResult pageResult = new PageResult(projectDomain, list);
		return pageResult;
	}

	@ResponseBody
	@RequestMapping(params = "method=insert")
	public Object insert(ProjectDomain projectDomain) {
		int i = projectService.insert(projectDomain);
		if (i > 0) {
			return new HttpResult(HttpResult.SUCCESS, projectDomain.getProjectName() + "新增项目成功.");
		} else {
			return new HttpResult(HttpResult.ERROR, projectDomain.getProjectName() + "新增项目失败.");
		}

	}

}
