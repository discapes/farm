package dev.miikat.farm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.miikat.farm.animals.Animal;
import dev.miikat.farm.util.Pair;
import dev.miikat.farm.util.Utility;

public class Farm implements Serializable {
	private List<Animal> animals = new ArrayList<>();
	public String name;
	public int energy = 3;

	public Farm(String name) {
		this.name = name;
	}

	public void addAnimal(Animal animal) {
		Console.showDialogue(animal.name + " is happy to come to your farm!");
		animals.add(animal);
	}

	public int numberOfAnimals() {
		return animals.size();
	}

	public void seeAllAnimals() {
		Console.showDialogue(String.join("\n",
				name,
				"Number of animals:" + numberOfAnimals(),
				"",
				String.join("\n", animals.stream().map((a) -> a.getInfoMessage()).toList())));
	}

	public boolean hasFeeder() {
		return true;
	}

	public void doActivity() {
		Animal animal = Console.pickMenuOption("Which animal would you like to select?",
				animals.stream().map((a) -> new Pair<>(a.name, a)).toList());

		animal.doActivity();
	}

	public void passDay() {
		energy++;
		Console.showDialogue("Good morning! You can now feed and walk the animals again!");
		for (Animal a : animals)
			a.passDay();
	}

	public String getFileName() {
		return getFileName(name);
	}

	public static String getFileName(String farmName) {
		return farmName.toLowerCase().replace(' ', '_') + ".ser";
	}

	public void saveToFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream(getFileName());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			Console.showDialogue("Serialized data is saved in " + getFileName() + ".\nIt can be loaded by specifying "
					+ name + " at the beginning.");
		} catch (IOException e) {
			Console.showDialogue("An error occured: \n\n" + Utility.getErrorString(e));
		}
	}

	public static Optional<Farm> FromFile(String fileName) {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Farm f = (Farm) in.readObject();
			in.close();
			fileIn.close();
			return Optional.of(f);
		} catch (Exception i) {
			Console.showDialogue("An error occured: \n\n" + Utility.getErrorString(i));
			return Optional.empty();
		}
	}
}
