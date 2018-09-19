/**
 *    Auth:riozenc
 *    Date:2018年5月10日 下午5:43:24
 *    Title:cis.webapp.sys.domain.UserDomain.java
 **/
package wms.webapp.sys.domain;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

/**
 * 用户
 * 
 * @author czy
 *
 */
public class UserDomain implements MybatisEntity {
	@TablePrimaryKey
	private Long id;// ID 用户号 char(20) 20 TRUE FALSE TRUE
	@TablePrimaryKey
	private String userId;// USER_ID 用户自定义ID char(20) 20 TRUE FALSE TRUE
	private String password;// 密码
	private String phone;// PHONE 电话 char(11) 11 FALSE FALSE FALSE
	private String mailAddress;// MAIL_ADDRESS 邮箱地址 char(20) 20 FALSE FALSE
								// FALSE
	private String userName;// USER_NAME 用户名称 char(15) 15 FALSE FALSE FALSE
	private Integer sex;// SEX 性别 int FALSE FALSE FALSE
	private String imageUrl;// IMAGE_URL 头像 varchar(20) 20 FALSE FALSE FALSE
	private String remark;// 备注
	private Integer status;// STATUS 状态 int FALSE FALSE FALSE

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
