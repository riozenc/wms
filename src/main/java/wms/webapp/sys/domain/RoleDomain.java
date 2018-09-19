/**
 *    Auth:riozenc
 *    Date:2018年8月13日 下午2:32:52
 *    Title:cis.webapp.sys.domain.RoleDomain.java
 **/
package wms.webapp.sys.domain;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

/**
 * 角色表
 * 
 * @author riozenc
 *
 */
public class RoleDomain implements MybatisEntity{
	@TablePrimaryKey
	private Long id;
	private String roleNo;
	private String roleName;
	private String remark;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
