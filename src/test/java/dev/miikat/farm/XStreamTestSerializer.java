package dev.miikat.farm;

import com.thoughtworks.xstream.security.AnyTypePermission;

public class XStreamTestSerializer extends XStreamSerializer {
	public XStreamTestSerializer() {
		super();
		xstream.addPermission(AnyTypePermission.ANY);
	}

}
