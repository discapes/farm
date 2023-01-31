package dev.miikat.farm;

import java.util.ArrayList;

import com.google.inject.Inject;

import dev.miikat.farm.animals.Animal;
import dev.miikat.farm.animals.Cat;
import dev.miikat.farm.animals.Pig;
import dev.miikat.farm.animals.Animal.Gender;
import dev.miikat.farm.lambda.UnaryLambda;
import dev.miikat.farm.util.Pair;

public class AnimalFactory {
	@Inject
	private Console console;

	private final ArrayList<Pair<String, UnaryLambda<Animal, Farm>>> constructors = new ArrayList<>() {
		{
			add(new Pair<>("Cat", (farm) -> new Cat(console, farm, getName("cat"), getGender())));
			add(new Pair<>("Pig", (farm) -> new Pig(console, farm, getName("pig"), getGender())));
		}
	};

	private Gender getGender() {
		return console.pickMenuOption("Do you want a", new ArrayList<Pair<String, Gender>>() {
			{
				add(new Pair<>("Male", Gender.MALE));
				add(new Pair<>("Female", Gender.FEMALE));
			}
		});
	}

	private String getName(String species) {
		return console.askQuestion("What would you like to name your new " + species + "?");
	}

	public Animal fromPrompt(Farm farm) {
		return console.pickMenuOption("What kind of animal would you like?", constructors).run(farm);
	}
}
