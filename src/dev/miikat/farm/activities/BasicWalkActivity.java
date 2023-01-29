package dev.miikat.farm.activities;

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
		System.out.println("Yay!");
	}

	@Override
	public String getName() {
		return "Walk";
	}
}
