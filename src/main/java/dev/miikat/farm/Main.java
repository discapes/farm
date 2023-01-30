
package dev.miikat.farm;

import java.io.File;
import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Optional;
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
		Optional<Farm> farmResult = getFarm();
		if (farmResult.isPresent()) {
			Farm farm = farmResult.get();

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
						if (farm.numberOfAnimals() != 0)
							add(new Pair<>("Do an activity", () -> farm.doActivity()));
						add(new Pair<>("See all animals", () -> farm.seeAllAnimals()));
						add(new Pair<>("Go to sleep", () -> farm.passDay()));
						add(new Pair<>("Save to " + farm.getFileName(), () -> farm.saveToFile()));
					}
				});
				opt.run();
			}
		}

		Console.switchFromAltScreen();
		System.exit(0);
		shouldExit.set(true);
	}

	private static Optional<Farm> getFarm() {
		String farmName = Console.askQuestion("What is your farm's name?");
		String fileName = Farm.getFileName(farmName);

		Optional<Farm> farm = Optional.empty();

		if (!(new File(fileName).exists()))
			farm = Optional.of(new Farm(farmName));

		if (farm.isEmpty() && Console
				.confirm("A existing farm was found with that name (" + fileName + "). Use it?"))
			farm = Farm.FromFile(fileName);

		if (farm.isEmpty() && Console.confirm("Do you want to create a new farm with the name " + farmName
				+ "? Saving it will overwrite the old one!"))
			farm = Optional.of(new Farm(farmName));

		return farm;
	}
}
