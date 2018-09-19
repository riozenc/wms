/**
 *    Auth:riozenc
 *    Date:2018年5月11日 上午8:57:00
 *    Title:cis.webapp.sys.service.IUserService.java
 **/
package wms.webapp.sys.service;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import wms.webapp.sys.domain.UserDomain;

public interface IUserService extends BaseService<UserDomain> {
	public String getPassword(UserDomain userDomain);
}
