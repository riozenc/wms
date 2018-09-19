/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午4:03:40
 *    Title:wms.webapp.sys.domain.ProjectDomain.java
 **/
package wms.webapp.wrk.domain;

import java.util.Date;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;
import com.riozenc.quicktool.mybatis.persistence.Page;

public class ProjectDomain extends Page implements MybatisEntity{
	@TablePrimaryKey
	private Long id;
	private String projectNo;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private Integer planDays;
	private Integer actualDays;
	private String remark;
	private Byte status;

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

}
