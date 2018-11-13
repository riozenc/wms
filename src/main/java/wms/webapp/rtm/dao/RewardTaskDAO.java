/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午1:49:09
 *    Title:wms.webapp.rtm.dao.RewardTaskDAO.java
 **/
package wms.webapp.rtm.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import wms.webapp.rtm.domain.RewardTaskDomain;

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

}
