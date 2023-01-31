package dev.miikat.farm.animals;

import dev.miikat.farm.Console;
import dev.miikat.farm.Farm;

public class Pig extends Animal {
	public Pig(Console console, Farm farm, String name, Gender gender) {
		super(console, farm, name, gender, "Pig");
	}

	@Override
	public void makeSound() {
		console.showDialogue("Oink Oink!");
	}

	@Override
	public String getGreeting() {
		return "Oink Oink I'm a pig.";
	}
}
