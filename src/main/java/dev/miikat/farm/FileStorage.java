package dev.miikat.farm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileStorage implements PersistentStorage {

	@Override
	public void write(String data, String key) throws IOException {
		// console.showDialogue("An error occured: \n\n" + Utility.getErrorString(i)
		FileOutputStream fileOut = new FileOutputStream(key);
		fileOut.write(data.getBytes());
		fileOut.close();
	}

	@Override
	public String read(String saveName) throws FileNotFoundException, IOException {
		FileInputStream fileIn = new FileInputStream(saveName);
		String result = new String(fileIn.readAllBytes(), StandardCharsets.UTF_8);
		fileIn.close();
		return result;
	}
}
