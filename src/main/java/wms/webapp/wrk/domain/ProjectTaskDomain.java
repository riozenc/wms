/**
 *    Auth:riozenc
 *    Date:2018年9月6日 上午10:29:00
 *    Title:wms.webapp.wrk.domain.ProjectTaskDomain.java
 **/
package wms.webapp.wrk.domain;

import java.util.Date;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

public class ProjectTaskDomain implements MybatisEntity {
	@TablePrimaryKey
	private Long id;
	private Long projectId;
	private Long taskId;
	private Date createDate;
	private Integer operatorId;// Long
	private Byte status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
