package dev.miikat.farm;

import java.util.ArrayList;
import java.util.List;

import dev.miikat.farm.animals.Animal;

public class Farm {
	private List<Animal> animals = new ArrayList<>();
	public String name;

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
		var dialogue = new StringBuilder("Number of animals: " + numberOfAnimals() + '\n');
		for (Animal animal : animals)
			dialogue.append(animal.getInfoMessage() + "\n");
		Console.showDialogue(dialogue.toString());
	}

	public boolean hasFeeder() {
		// TODO
		return true;
	}
}
