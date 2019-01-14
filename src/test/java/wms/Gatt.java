/**
 *    Auth:riozenc
 *    Date:2018年9月26日 下午3:41:00
 *    Title:wms.Gatt.java
 **/
package wms;

import com.riozenc.quicktool.common.util.cryption.en.WebPasswordUtils;

public class Gatt {
	private String[] dimensions;
	private Object[] objs;

	public String[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(String[] dimensions) {
		this.dimensions = dimensions;
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setData(Object[] objs) {
		this.objs = objs;
	}

	public static void main(String[] args) {
		System.out.println(WebPasswordUtils.encodePassword("123456"));
	}
}
