/**
 *    Auth:riozenc
 *    Date:2018年11月8日 下午5:49:16
 *    Title:wms.webapp.rtm.action.RewardTaskAction.java
 **/
package wms.webapp.rtm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.util.json.HTTP;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.webapp.rtm.domain.RewardTaskDomain;
import wms.webapp.rtm.domain.RewardTaskUserDomain;
import wms.webapp.rtm.service.IRewardTaskService;

@ControllerAdvice(assignableTypes = RewardTaskAction.class)
@RequestMapping("rewardTask")
public class RewardTaskAction extends BaseAction {

	@Autowired
	@Qualifier("rewardTaskServiceImpl")
	private IRewardTaskService rewardTaskService;

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "rewardTask.jsp";
	}

	@ResponseBody
	@RequestMapping(params = "method=getTasks")
	public List<RewardTaskDomain> getTasks(RewardTaskDomain rewardTaskDomain) {
		List<RewardTaskDomain> list = rewardTaskService.getRewardTasks(rewardTaskDomain);
		return list;
	}
	
	//liu
	@ResponseBody
	@RequestMapping(params = "method=acceptTask")
	public int acceptTask(HttpServletRequest req) throws  IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		rewardTaskService.updateStatus(id);
		rewardTaskService.insertRewardTaskUser(id);
		return 0;
		
	}
	@RequestMapping(params = "method=getMyTask")
	public String getMyTask() {
		return "myTask.jsp";
	}
	@RequestMapping(params = "method=getReviewTask")
	public String getReviewTask() {
		return "reviewTask.jsp";
	}
	@ResponseBody
	@RequestMapping(params="method=getUndoTasks")
	public List<RewardTaskUserDomain> getUndoTasks(){
		List<RewardTaskUserDomain> list= rewardTaskService.getUndoTasks();
		return list;
	}
	@ResponseBody
	@RequestMapping(params="method=getUnderReviewTasks")
	public List<RewardTaskUserDomain> getUnderReviewTasks(){
		
		List<RewardTaskUserDomain> list= rewardTaskService.getUnderReviewTasks();
		return list;
	}
	@ResponseBody
	@RequestMapping(params="method=getAccomplishedTasks")
	public List<RewardTaskUserDomain> getAccomplishedTasks(){
		List<RewardTaskUserDomain> list= rewardTaskService.getAccomplishedTasks();
		return list;
	}
	@ResponseBody
	@RequestMapping(params="method=getReviewTasks")
	public List<RewardTaskUserDomain> getReviewTasks(HttpServletRequest req){
		RewardTaskUserDomain rewardTaskUserDomain=new RewardTaskUserDomain();
		rewardTaskUserDomain.setUserAccount(req.getParameter("userId"));
		rewardTaskUserDomain.setUserName(req.getParameter("userName"));
		//rewardTaskUserDomain.setProjectName(req.getParameter("projectName"));
		List<RewardTaskUserDomain> list= rewardTaskService.getReviewTask(rewardTaskUserDomain);
		return list;
	}
	@RequestMapping(params="method=submitTask")
	public int submitTask(HttpServletRequest req) {
		int rewardTaskId=Integer.parseInt(req.getParameter("rewardTaskId"));
		return rewardTaskService.UpdateStatusForSubmit(rewardTaskId);
	}
	@ResponseBody
	@RequestMapping(params="method=approved")
	public int approved(HttpServletRequest req) {
		int rewardTaskId=Integer.parseInt(req.getParameter("rewardTaskId"));
		System.out.println(rewardTaskService.UpdateStatusForReview(rewardTaskId));
		
		return rewardTaskService.UpdateStatusForReview(rewardTaskId);
		
		
	}
	@ResponseBody
	@RequestMapping(params="method=getAllUserName")
	public List<RewardTaskUserDomain> getAllUserName(){
		return rewardTaskService.getAllUserName();
	}
	
	//交付悬赏
	public Object deliver() {
		return null;
	}

}
