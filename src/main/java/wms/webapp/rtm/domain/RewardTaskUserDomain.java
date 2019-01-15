package wms.webapp.rtm.domain;

import java.util.Date;

import com.riozenc.quicktool.mybatis.MybatisEntity;

public class RewardTaskUserDomain implements MybatisEntity {
	private int rewardTaskId;
	private String taskName;
	private String projectName;
	private String userName;
	private int id;
	private String userAccount;
	private String startDate;
	private String endDate;
	private int actualDays;
	private String remark;
	private int status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getRewardTaskId() {
		return rewardTaskId;
	}

	public void setRewardTaskId(int rewardTaskId) {
		this.rewardTaskId = rewardTaskId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getActualDays() {
		return actualDays;
	}

	public void setActualDays(int actualDays) {
		this.actualDays = actualDays;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
