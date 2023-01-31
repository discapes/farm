package dev.miikat.farm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import dev.miikat.farm.animals.Animal;
import dev.miikat.farm.util.Pair;

public class Farm implements Serializable {
	private List<Animal> animals = new ArrayList<>();
	public String name;
	public int energy = 3;

	@Inject
	private Console console;

	@Inject
	public Farm(@Assisted String name) {
		this.name = name;
	}

	public void addAnimal(Animal animal) {
		console.showDialogue(animal.name + " is happy to come to your farm!");
		animals.add(animal);
	}

	public int numberOfAnimals() {
		return animals.size();
	}

	public void seeAllAnimals() {
		console.showDialogue(String.join("\n",
				name,
				"Number of animals: " + numberOfAnimals(),
				"",
				String.join("\n", animals.stream().map((a) -> a.getInfoMessage()).toList())));
	}

	public boolean hasFeeder() {
		return true;
	}

	public void doActivity() {
		Animal animal = console.pickMenuOption("Which animal would you like to select?",
				animals.stream().map((a) -> new Pair<>(a.name, a)).toList());

		animal.doActivity();
	}

	public void passDay() {
		energy++;
		console.showDialogue("Good morning! You can now feed and walk the animals again!");
		for (Animal a : animals)
			a.passDay();
	}

	public String getFileName() {
		return getFileName(name);
	}

	public static String getFileName(String farmName) {
		return farmName.toLowerCase().replace(' ', '_') + ".farm";
	}
}
