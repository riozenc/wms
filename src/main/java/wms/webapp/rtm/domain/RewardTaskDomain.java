/**
 *    Auth:riozenc
 *    Date:2018年11月9日 下午1:51:05
 *    Title:wms.webapp.rtm.domain.RewardTaskDomain.java
 **/
package wms.webapp.rtm.domain;

import java.util.Date;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

public class RewardTaskDomain implements MybatisEntity {
	@TablePrimaryKey
	private Long id;
	private String projectNo;// PROJECT_NO char
	private String taskNo;// TASK_NO char
	private String taskName;// TASK_NAME char
	private Date createDate;// CREATE_DATE datetime
	private Integer planDays;// PLAN_DAYS int
	private String remark;// REMARK varchar
	private Byte status;// STATUS int

	// REL
	private String projectName;//
	private String userName;//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getPlanDays() {
		return planDays;
	}

	public void setPlanDays(Integer planDays) {
		this.planDays = planDays;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
