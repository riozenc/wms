package wms.webapp.rtm.domain;

import java.util.Date;

import com.riozenc.quicktool.mybatis.MybatisEntity;


public class RewardTaskUserDomain implements MybatisEntity{
	private int rewardTaskId;
	private String taskName;
	private String projectName;
	private String userName;
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
	private int id;
	
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
	private String userId;
	private String startDate;
	private String endDate;
	private int actualDays;
	private String remark;
	private int status;
	
	public int getRewardTaskId() {
		return rewardTaskId;
	}
	public void setRewardTaskId(int rewardTaskId) {
		this.rewardTaskId = rewardTaskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
