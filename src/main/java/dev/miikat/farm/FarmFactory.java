package dev.miikat.farm;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Optional;

import com.google.inject.Inject;

import dev.miikat.farm.util.Utility;

public class FarmFactory {
	@Inject
	private Console console;

	public Farm create(String name) {
		return new Farm(console, name);
	}

	public Optional<Farm> fromFile(String fileName) {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Farm f = (Farm) in.readObject();
			in.close();
			fileIn.close();
			return Optional.of(f);
		} catch (Exception i) {
			console.showDialogue("An error occured: \n\n" + Utility.getErrorString(i));
			return Optional.empty();
		}
	}
}
