/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午3:44:55
 *    Title:wms.webapp.sys.domain.TaskDomain.java
 **/
package wms.webapp.wrk.domain;

import java.util.Date;

import com.riozenc.quicktool.annotation.ReflectionIgnore;
import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

public class TaskDomain implements MybatisEntity {

	public enum TASK_STATUS {
		C((byte) 0), N((byte) 1), R((byte) 2);
		private final Byte status;

		private TASK_STATUS(Byte status) {
			this.status = status;
		}

		public Byte getStatus() {
			return status;
		}
	}

	@TablePrimaryKey
	private Long id;
	private String taskNo;
	private String taskName;
	private String userId;
	private Date startDate;
	private Date endDate;
	private Integer planDays;
	private Integer actualDays;
	private String remark;
	private Byte status;// 0失效 1录入 2发布中

	@ReflectionIgnore
	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPlanDays() {
		return planDays;
	}

	public void setPlanDays(Integer planDays) {
		this.planDays = planDays;
	}

	public Integer getActualDays() {
		return actualDays;
	}

	public void setActualDays(Integer actualDays) {
		this.actualDays = actualDays;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isNoR() {
		return this.status != TASK_STATUS.R.getStatus();
	}

}
