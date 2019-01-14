/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午1:49:09
 *    Title:wms.webapp.rtm.dao.RewardTaskDAO.java
 **/
package wms.webapp.rtm.dao;

import java.util.List;
import java.util.Map;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.rtm.domain.RewardTaskDomain;
import wms.webapp.rtm.domain.RewardTaskUserDomain;

import java.util.Date;
import java.util.HashMap;

@TransactionDAO
public class RewardTaskDAO extends AbstractTransactionDAOSupport implements BaseDAO<RewardTaskDomain> {

	@Override
	public int insert(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public RewardTaskDomain findByKey(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<RewardTaskDomain> findByWhere(RewardTaskDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<RewardTaskDomain> getRewardTasks(RewardTaskDomain rewarkTaskDomain) {
		return getPersistanceManager().find(getNamespace() + ".getRewardTasks", rewarkTaskDomain);
	}
	
	//lyj	
		public int updateStatus(int id) {
			return getPersistanceManager().update(getNamespace() + ".updateStatus", id);
		}
		public List<RewardTaskDomain> getRewardTasks2() {
			return getPersistanceManager().find(getNamespace() + ".getRewardTasks2", null);
		}
		public List<RewardTaskDomain> findById(int id) {
			return  getPersistanceManager().find(getNamespace() + ".findById", id);
		}
		public int insertRewardTaskUser(RewardTaskUserDomain t) {
			// TODO Auto-generated method stub
			return getPersistanceManager().insert(getNamespace() + ".insertRewardTaskUser", t);
		}
		public List<RewardTaskUserDomain> getUndoTasks(String userId){
			return  getPersistanceManager().find(getNamespace() + ".getMyTasks", userId);
		}
		public List<RewardTaskUserDomain> getUnderReviewTasks(String userId){
			return  getPersistanceManager().find(getNamespace() + ".getMyTasks2", userId);
		}
		public List<RewardTaskUserDomain> getAccomplishedTasks(String userId){
			return  getPersistanceManager().find(getNamespace() + ".getMyTasks3", userId);
		}
		public int updateRewardTaskStatus3(int rewardTaskId) {
			return getPersistanceManager().update(getNamespace() + ".updateRewardTaskStatus3", rewardTaskId);
		}
		public int updateRewardTaskUserStatus3(int rewardTaskId) {
			return getPersistanceManager().update(getNamespace() + ".updateRewardTaskUserStatus3", rewardTaskId);
		}
		public List<RewardTaskUserDomain> getReviewTask(RewardTaskUserDomain rewardTaskUserDomain) {
			return getPersistanceManager().find(getNamespace() + ".getReviewTask", rewardTaskUserDomain);
		}
		
		public int updateRewardTaskStatus4(RewardTaskUserDomain rewardTaskUserDomain) {
			return getPersistanceManager().update(getNamespace() + ".updateRewardTaskStatus4", rewardTaskUserDomain);
		}
		public int updateRewardTaskUserStatus4(RewardTaskUserDomain rewardTaskUserDomain) {
			return getPersistanceManager().update(getNamespace() + ".updateRewardTaskUserStatus4", rewardTaskUserDomain);
		}
		public List<RewardTaskUserDomain> getstartDate(int rewardTaskId) {
			return getPersistanceManager().find(getNamespace() + ".getStartDate", rewardTaskId);
		}
		
		public List<RewardTaskUserDomain> getAllUserName(){
			return getPersistanceManager().find(getNamespace() + ".getAllUserName", null);
		}

}
