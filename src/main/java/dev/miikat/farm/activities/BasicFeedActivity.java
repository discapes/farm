package dev.miikat.farm.activities;

import com.google.inject.Inject;

import dev.miikat.farm.Console;
import dev.miikat.farm.animals.Animal;

public class BasicFeedActivity implements AnimalActivity {
	Animal animal;
	int lastDoneAge;

	private Console console;

	public BasicFeedActivity(Console console, Animal animal) {
		this.console = console;
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
		console.showDialogue("Yum! Strength +1");
	}

	@Override
	public String getName() {
		return "Feed";
	}

}
