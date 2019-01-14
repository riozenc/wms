/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午4:06:38
 *    Title:wms.Xml.java
 **/
package wms;

import java.io.IOException;

import com.riozenc.quicktool.common.util.ClassDAOXmlUtil;

import wms.webapp.rtm.domain.RewardTaskDomain;
import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.ProjectTaskDomain;

public class Xml {
	public static void main(String[] args) {
		try {
			ClassDAOXmlUtil.buildXML("C:\\Users\\riozenc\\git\\wms\\src\\main\\java\\wms\\webapp\\rtm\\dao", RewardTaskDomain.class,
					"REWARD_TASK_INFO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
