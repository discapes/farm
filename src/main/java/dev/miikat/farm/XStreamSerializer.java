package dev.miikat.farm;

import com.thoughtworks.xstream.XStream;

public class XStreamSerializer implements Serializer {
	protected XStream xstream = createXStream();

	public XStreamSerializer() {
		xstream.allowTypesByWildcard(new String[] {
				"dev.miikat.**",
		});
	}

	protected XStream createXStream() {
		return new XStream();
	}

	@Override
	public String serialize(Object o) {
		return xstream.toXML(o);
	}

	@Override
	public Object deserialize(String data) {
		return xstream.fromXML(data);
	}

}
