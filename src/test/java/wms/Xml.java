/**
 *    Auth:riozenc
 *    Date:2018年9月4日 下午4:06:38
 *    Title:wms.Xml.java
 **/
package wms;

import java.io.IOException;

import com.riozenc.quicktool.common.util.ClassDAOXmlUtil;

import wms.webapp.wrk.domain.ProjectDomain;
import wms.webapp.wrk.domain.ProjectTaskDomain;

public class Xml {
	public static void main(String[] args) {
		try {
			ClassDAOXmlUtil.buildXML("E:\\photon-workspace\\wms\\src\\main\\java\\wms\\webapp\\wrk\\dao", ProjectTaskDomain.class,
					"PROJECT_TASK_REL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
