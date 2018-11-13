/**
 *    Auth:riozenc
 *    Date:2018年11月8日 下午5:49:16
 *    Title:wms.webapp.rtm.action.RewardTaskAction.java
 **/
package wms.webapp.rtm.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.webapp.rtm.domain.RewardTaskDomain;
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

}
