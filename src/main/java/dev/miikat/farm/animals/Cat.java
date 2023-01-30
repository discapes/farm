package dev.miikat.farm.animals;

import java.util.ArrayList;
import java.util.Arrays;

import dev.miikat.farm.Console;
import dev.miikat.farm.Farm;
import dev.miikat.farm.activities.AnimalActivity;
import dev.miikat.farm.util.Randomgen;

public class Cat extends Animal {
	private class PetActivity implements AnimalActivity {

		@Override
		public String getName() {
			return "Pet the cat";
		}

		@Override
		public boolean isAvailable() {
			return happiness < 12;
		}

		@Override
		public void doActivity() {
			if (happiness < 12)
				Console.showDialogue("YAAY Im beeing petted");
			else
				Console.showDialogue("YAAY Im beeing petted ! Pet me again !");

			happiness++;
		}
	}

	int happiness = 5;

	public Cat(Farm farm, String name, Gender gender) {
		super(farm, name, gender, "Cat");
		activities.add(new PetActivity());
	}

	@Override
	public void makeSound() {
		Console.showDialogue("Meow!");
	}

	@Override
	public String getGreeting() {
		if (happiness < 10 && Randomgen.passes(50))
			return "plz pet me i need pets";
		String[] greetings = { "Meow I am just a small cat.", "MIAAAAU", "Nice to meowt you!" };
		return Randomgen.randomString(greetings);
	}

	@Override
	public void dailyUpdate() {
		happiness -= 2;
	}
}
