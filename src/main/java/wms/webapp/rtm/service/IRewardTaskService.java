/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午2:17:15
 *    Title:wms.webapp.rtm.service.IRewardTaskService.java
 **/
package wms.webapp.rtm.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import wms.webapp.rtm.domain.RewardTaskDomain;
import wms.webapp.rtm.domain.RewardTaskUserDomain;

public interface IRewardTaskService extends BaseService<RewardTaskDomain> {
	public List<RewardTaskDomain> getRewardTasks(RewardTaskDomain rewardTaskDomain);
	public int updateStatus(int id);
	public List<RewardTaskDomain> getRewardTasks2();
	public int insertRewardTaskUser(int id);
	public List<RewardTaskUserDomain> getUndoTasks();
	public List<RewardTaskUserDomain> getUnderReviewTasks();
	public List<RewardTaskUserDomain> getAccomplishedTasks();
	public int UpdateStatusForSubmit(int rewardTaskId);
	public List<RewardTaskUserDomain> getReviewTask(RewardTaskUserDomain rewardTaskUserDomain);
	public int UpdateStatusForReview(int rewardTaskId);
	public List<RewardTaskUserDomain> getAllUserName();

}
