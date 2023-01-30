package dev.miikat.farm.activities;

import dev.miikat.farm.Console;
import dev.miikat.farm.Farm;
import dev.miikat.farm.animals.Animal;

public class BasicWalkActivity implements AnimalActivity {
	Animal animal;
	Farm farm;
	int lastDoneAge;

	public BasicWalkActivity(Animal animal) {
		this.animal = animal;
	}

	@Override
	public boolean isAvailable() {
		return lastDoneAge < animal.age;
	}

	@Override
	public void doActivity() {
		lastDoneAge = animal.age;
		animal.speed++;
		Console.showDialogue("Yay! Speed +1");
	}

	@Override
	public String getName() {
		return "Walk";
	}
}
