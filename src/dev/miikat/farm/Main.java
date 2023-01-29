
package dev.miikat.farm;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.miikat.farm.lambda.NullaryVoidLambda;
import dev.miikat.farm.util.Pair;

public abstract class Main {
	private static String guide = """
			Welcome to the farm!
			Here, you can get new animals and take care of them.
			After you got your first animal, you can select them
			and do different things with them.
			You can feed every animal once a day to improve their strength
			and take one animal for a walk each day to improve their speed.
			If you want, you can rename your animals and give them to new homes.
			Press 1 or 2 to get your first animal.
			Made by Miika
				""";

	public static void main(String[] args) {
		Console.switchToAltScreen();
		Console.showDialogue("Welcome to the farm! Press enter to continue.");

		AtomicBoolean shouldExit = new AtomicBoolean(false);
		Farm farm = new Farm(Console.askQuestion("What would you like to name your farm?"));

		while (!shouldExit.get()) {
			var opt = Console.pickMenuOption("""
					Main menu
					---
					What would you like to do?
					""", new ArrayList<Pair<String, NullaryVoidLambda>>() {
				{
					add(new Pair<>("Quit", () -> {
						if (Console.confirm("Are you sure you want to quit?"))
							shouldExit.set(true);
					}));
					add(new Pair<>("Get a new animal", () -> farm.addAnimal(AnimalFactory.fromPrompt(farm))));

					add(new Pair<>("View the guide and credits", () -> Console.showDialogue(guide)));
					add(new Pair<>("See all animals", () -> farm.seeAllAnimals()));
				}
			});
			opt.run();
		}

		Console.switchFromAltScreen();
		System.exit(0);
		shouldExit.set(true);
	}
}
