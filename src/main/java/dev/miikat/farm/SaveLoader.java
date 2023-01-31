package dev.miikat.farm;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.inject.Inject;

public class SaveLoader {
	@Inject
	private Serializer serializer;
	@Inject
	private PersistentStorage storage;

	void save(Object o, String name) throws IOException {
		String xml = serializer.serialize(o);
		storage.write(xml, name);
	}

	Object load(String name) throws FileNotFoundException, IOException {
		String xml = storage.read(name);
		return serializer.deserialize(xml);
	}
}
