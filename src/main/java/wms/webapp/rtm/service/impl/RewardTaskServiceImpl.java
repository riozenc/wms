/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午2:17:50
 *    Title:wms.webapp.rtm.service.impl.RewardTaskServiceImpl.java
 **/
package wms.webapp.rtm.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;

import wms.webapp.rtm.dao.RewardTaskDAO;
import wms.webapp.rtm.domain.RewardTaskDomain;
import wms.webapp.rtm.service.IRewardTaskService;

@TransactionService
public class RewardTaskServiceImpl implements IRewardTaskService {

	@TransactionDAO
	private RewardTaskDAO rewardTaskDAO;

	@Override
	public int insert(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return rewardTaskDAO.insert(t);
	}

	@Override
	public int delete(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return rewardTaskDAO.delete(t);
	}

	@Override
	public int update(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return rewardTaskDAO.update(t);
	}

	@Override
	public RewardTaskDomain findByKey(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return rewardTaskDAO.findByKey(t);
	}

	@Override
	public List<RewardTaskDomain> findByWhere(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return rewardTaskDAO.findByWhere(t);
	}

	@Override
	public List<RewardTaskDomain> getRewardTasks(RewardTaskDomain rewardTaskDomain) {
		// TODO Auto-generated method stub
		return rewardTaskDAO.getRewardTasks(rewardTaskDomain);
	}

}
