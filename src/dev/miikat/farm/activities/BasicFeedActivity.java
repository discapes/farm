package dev.miikat.farm.activities;

import dev.miikat.farm.animals.Animal;

public class BasicFeedActivity implements AnimalActivity {
	Animal animal;
	int lastDoneAge;

	public BasicFeedActivity(Animal animal) {
		this.animal = animal;
	}

	@Override
	public boolean isAvailable() {
		return animal.farm.hasFeeder() && lastDoneAge < animal.age;
	}

	@Override
	public void doActivity() {
		lastDoneAge = animal.age;
		animal.strength++;
		System.out.println("Yum!");
	}

	@Override
	public String getName() {
		return "Feed";
	}

}
