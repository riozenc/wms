/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午2:17:15
 *    Title:wms.webapp.rtm.service.IRewardTaskService.java
 **/
package wms.webapp.rtm.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import wms.webapp.rtm.domain.RewardTaskDomain;

public interface IRewardTaskService extends BaseService<RewardTaskDomain> {
	public List<RewardTaskDomain> getRewardTasks(RewardTaskDomain rewardTaskDomain);

}
