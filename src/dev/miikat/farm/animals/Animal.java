package dev.miikat.farm.animals;

import dev.miikat.farm.Farm;
import dev.miikat.farm.Utility;

public abstract class Animal {
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
	}

	public String getInfoMessage() {
		return String.format("%s (%d-day-old %s %s) [strength : %d] [speed: %d]", name, age,
				gender.toString().toLowerCase(), species, strength,
				speed);
	}

	public abstract void makeSound();
}
