package dev.miikat.farm;

import java.util.ArrayList;

import dev.miikat.farm.animals.Animal;
import dev.miikat.farm.animals.Cat;
import dev.miikat.farm.animals.Pig;
import dev.miikat.farm.animals.Animal.Gender;
import dev.miikat.farm.lambda.UnaryLambda;
import dev.miikat.farm.util.Pair;

public class AnimalFactory {
	private static final ArrayList<Pair<String, UnaryLambda<Animal, Farm>>> constructors = new ArrayList<>() {
		{
			add(new Pair<>("Cat", (farm) -> new Cat(farm, getName("cat"), getGender())));
			add(new Pair<>("Pig", (farm) -> new Pig(farm, getName("pig"), getGender())));
		}
	};

	private static Gender getGender() {
		return Console.pickMenuOption("Do you want a", new ArrayList<Pair<String, Gender>>() {
			{
				add(new Pair<>("Male", Gender.MALE));
				add(new Pair<>("Female", Gender.FEMALE));
			}
		});
	}

	private static String getName(String species) {
		return Console.askQuestion("What would you like to name your new " + species + "?");
	}

	public static Animal fromPrompt(Farm farm) {
		return Console.pickMenuOption("What kind of animal would you like?", constructors).run(farm);
	}
}
