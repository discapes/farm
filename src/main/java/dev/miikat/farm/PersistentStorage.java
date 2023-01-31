package dev.miikat.farm;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PersistentStorage {
	void write(String data, String key) throws IOException;

	String read(String key) throws FileNotFoundException, IOException;
}
