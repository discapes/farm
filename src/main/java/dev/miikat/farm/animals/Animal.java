package dev.miikat.farm.animals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.miikat.farm.Console;
import dev.miikat.farm.Farm;
import dev.miikat.farm.activities.AnimalActivity;
import dev.miikat.farm.activities.BasicFeedActivity;
import dev.miikat.farm.activities.BasicWalkActivity;
import dev.miikat.farm.util.Pair;
import dev.miikat.farm.util.Utility;

public abstract class Animal implements Serializable {
	public enum Gender {
		MALE, FEMALE
	}

	public int age = 0;
	public int strength = 1;
	public int speed = 1;

	public Farm farm;
	public String name;
	public String species;
	public Gender gender;
	public List<AnimalActivity> activities = new ArrayList<>();

	public void setName(String name) {
		String capName = Utility.capitalize(name);
		this.name = capName;
	}

	public Animal(Farm farm, String name, Gender gender, String species) {
		this.name = Utility.capitalize(name);
		this.farm = farm;
		this.gender = gender;
		this.species = species;
		this.speed = (int) (Math.random() * 20 + 1);
		this.strength = (int) (Math.random() * 20 + 1);
		this.age = (int) (Math.random() * 50 + 50);
		activities.add(new BasicFeedActivity(this));
		activities.add(new BasicWalkActivity(this));
	}

	public String getInfoMessage() {
		return String.format("%s (%d-day-old %s %s) [strength : %d] [speed: %d]", name, age,
				gender.toString().toLowerCase(), species, strength,
				speed);
	}

	public abstract void makeSound();

	public abstract String getGreeting();

	public void doActivity() {

		AnimalActivity activity = Console.pickMenuOption(getGreeting() + "\n\nWhich activity would you like to select?",
				activities.stream().filter((a) -> a.isAvailable()).map((a) -> new Pair<>(a.getName(), a)).toList());
		activity.doActivity();
	}

	public void dailyUpdate() {

	}

	public final void passDay() {
		age++;
		dailyUpdate();
	}
}
