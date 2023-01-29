package dev.miikat.farm.animals;

import dev.miikat.farm.Console;
import dev.miikat.farm.Farm;

public class Pig extends Animal {

	public Pig(Farm farm, String name, Gender gender) {
		super(farm, name, gender, "Pig");
	}

	public void makeSound() {
		Console.showDialogue("Oink Oink!");
	}
}
