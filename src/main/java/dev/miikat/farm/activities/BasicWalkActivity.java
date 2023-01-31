package dev.miikat.farm.activities;

import dev.miikat.farm.Console;
import dev.miikat.farm.animals.Animal;

public class BasicWalkActivity implements AnimalActivity {
	Animal animal;
	int lastDoneAge;

	private Console console;

	public BasicWalkActivity(Console console, Animal animal) {
		this.console = console;
		this.animal = animal;
	}

	@Override
	public boolean isAvailable() {
		return (animal.age - lastDoneAge) >= 2 && animal.farm.energy > 0;
	}

	@Override
	public void doActivity() {
		lastDoneAge = animal.age;
		animal.speed++;
		animal.farm.energy--;
		console.showDialogue("Yay! Speed +1");
	}

	@Override
	public String getName() {
		return "Walk";
	}
}
