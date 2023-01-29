package dev.miikat.farm.animals;

import dev.miikat.farm.Console;
import dev.miikat.farm.Farm;

public class Cat extends Animal {

	public Cat(Farm farm, String name, Gender gender) {
		super(farm, name, gender, "Cat");
	}

	public void makeSound() {
		Console.showDialogue("Meow!");
	}
}
