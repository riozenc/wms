/**
 *    Auth:riozenc
 *    Date:2018年6月19日 下午3:22:08
 *    Title:cis.webapp.sys.domain.MenuDomain.java
 **/
package wms.webapp.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.riozenc.quicktool.annotation.ReflectionIgnore;
import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

/**
 * 菜单
 * 
 * @author riozenc
 *
 */
public class MenuDomain implements MybatisEntity {
	@TablePrimaryKey
	private Integer id;
	private String menuName;
	private String menuUrl;
	private Integer parentId;
	private Byte isLeaf;
	private Byte status;
	private Byte sortNo;

	@JsonIgnore
	@ReflectionIgnore
	private Long userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Byte getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Byte isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getSortNo() {
		return sortNo;
	}

	public void setSortNo(Byte sortNo) {
		this.sortNo = sortNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}
