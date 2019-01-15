/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午2:17:50
 *    Title:wms.webapp.rtm.service.impl.RewardTaskServiceImpl.java
 **/
package wms.webapp.rtm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.apache.shiro.SecurityUtils;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;
import com.riozenc.quicktool.shiro.Principal;

import wms.webapp.rtm.dao.RewardTaskDAO;
import wms.webapp.rtm.domain.RewardTaskDomain;
import wms.webapp.rtm.domain.RewardTaskUserDomain;
import wms.webapp.rtm.service.IRewardTaskService;
import wms.webapp.sys.domain.UserDomain;
import wms.webapp.wrk.dao.TaskDAO;
import wms.webapp.wrk.domain.TaskDomain;

@TransactionService
public class RewardTaskServiceImpl implements IRewardTaskService {

	@TransactionDAO
	private RewardTaskDAO rewardTaskDAO;
	@TransactionDAO
	private TaskDAO taskDAO;

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
	//..................................
		@Override
		public int updateStatus(int id) {
			// TODO Auto-generated method stub
			return rewardTaskDAO.updateStatus(id);
			
		}
		public List<RewardTaskDomain> getRewardTasks2() {
			// TODO Auto-generated method stub
			return rewardTaskDAO.getRewardTasks2();
			
		}
		public int insertRewardTaskUser(int id) {
			Principal user =   (Principal) SecurityUtils.getSubject().getPrincipal();
			RewardTaskUserDomain rewardTaskUserDomain =new RewardTaskUserDomain();
			List<RewardTaskDomain> list=rewardTaskDAO.findById(id);
			rewardTaskUserDomain.setRewardTaskId(id);
			rewardTaskUserDomain.setStatus(list.get(0).getStatus());
			rewardTaskUserDomain.setRemark(list.get(0).getRemark());
			rewardTaskUserDomain.setUserAccount(user.getUserAccount());
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String dateString = formatter.format(date);
			rewardTaskUserDomain.setStartDate(dateString);
			rewardTaskDAO.insertRewardTaskUser(rewardTaskUserDomain);
			return 0;
		}
		public List<RewardTaskUserDomain> getUndoTasks(){
			Principal user =   (Principal) SecurityUtils.getSubject().getPrincipal();
			String userAccount=user.getUserAccount();
			List<RewardTaskUserDomain> list=rewardTaskDAO.getUndoTasks(userAccount);
			System.out.println("list.size:"+list.size());
			System.out.println(list.get(0).getStatus());
			return list;
		}
		public List<RewardTaskUserDomain> getUnderReviewTasks(){
			Principal user =   (Principal) SecurityUtils.getSubject().getPrincipal();
			String userAccount=user.getUserAccount();
			List<RewardTaskUserDomain> list=rewardTaskDAO.getUnderReviewTasks(userAccount);
			System.out.println("list.size:"+list.size());
			System.out.println(list.get(0).getStatus());
			return list;
		}
		public List<RewardTaskUserDomain> getAccomplishedTasks(){
			Principal user =   (Principal) SecurityUtils.getSubject().getPrincipal();
			String userAccount=user.getUserAccount();
			List<RewardTaskUserDomain> list=rewardTaskDAO.getAccomplishedTasks(userAccount);
			System.out.println("list.size:"+list.size());
			System.out.println(list.get(0).getStatus());
			return list;
		}
		public int UpdateStatusForSubmit(int rewardTaskId) {
			
			 rewardTaskDAO.updateRewardTaskStatus3(rewardTaskId);
			 return rewardTaskDAO.updateRewardTaskUserStatus3(rewardTaskId);
		}
		public List<RewardTaskUserDomain> getReviewTask(RewardTaskUserDomain rewardTaskUserDomain) {
			List<RewardTaskUserDomain> list=rewardTaskDAO.getReviewTask(rewardTaskUserDomain);
			return list;
		}
		
		public int UpdateStatusForReview(int rewardTaskId) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			RewardTaskUserDomain rewardTaskUserDomain=new RewardTaskUserDomain();
			//Date d1=rewardTaskDAO.getstartDate(rewardTaskId).get(0).getStartDate();
			try {
				Date d1=df.parse(rewardTaskDAO.getstartDate(rewardTaskId).get(0).getStartDate());
				Date d2=new Date();
				long times=d2.getTime()-d1.getTime();
				int days=(int)times/(1000 * 60 * 60 * 24)+1;
				String date=df.format(d2);
				rewardTaskUserDomain.setActualDays(days);
				rewardTaskUserDomain.setEndDate(date);
				rewardTaskUserDomain.setRewardTaskId(rewardTaskId);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("鎶ラ敊");
				e.printStackTrace();
			}
			
			rewardTaskDAO.updateRewardTaskStatus4(rewardTaskUserDomain);
			return rewardTaskDAO.updateRewardTaskUserStatus4(rewardTaskUserDomain);
		}
		
		
		public List<RewardTaskUserDomain> getAllUserName(){
			return rewardTaskDAO.getAllUserName();
		}


}
