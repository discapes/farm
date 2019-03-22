
package code;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Farm {

	private static ArrayList<Animal> animalArray = new ArrayList<Animal>();
	private static boolean hasWalkedToday = false;
	static String[] availableAnimals = { "cat", "pig" };
	private static int numberOfAnimals = 0;
	private static int animalsGivenAway;
	private static String randomSpecie;
	private static String adoptName;

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

		Console console = System.console();
		if (console == null && !GraphicsEnvironment.isHeadless()) {
			String filename = "Farm.jar";
			Runtime.getRuntime()
					.exec(new String[] { "cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\"" });
			System.exit(0);
		}

		String selectedIndex;
		String feedIndex = "feedError";
		String walkIndex = "walkError";
		Scanner userInput = new Scanner(System.in);

		System.out.println("Welcome to the farm!");

		while (true) {
			System.out.println("Main Menu. Press:");
			System.out.println("1. Get a new animal");
			System.out.println("2. Get a random animal");
			System.out.println("3. Go to sleep");
			System.out.println("4. View the guide and credits");
			if (numberOfAnimals != 0) {
				System.out.println("5. Select an animal");
				System.out.println("6. Get a list of all animals");
			}
			if (animalsGivenAway != 0) {
				System.out.println("7. Get a list of animals you have given away");
			}
			String reply = userInput.nextLine();

			if (reply.equals("1")) {

				System.out.println("What animal would you like?");
				System.out.println("Options are: ");

				for (String i : availableAnimals) {
					System.out.println(makeUpperCase(i));
				}

				reply = userInput.nextLine();
				if (reply.equalsIgnoreCase("pig")) {
					createAnimal(userInput, "pig");
				} else if (reply.equalsIgnoreCase("cat")) {
					createAnimal(userInput, "cat");
				} else if (reply.equals("")) {
					System.out.println("What?");
					continue;
				} else {
					System.out.println("Sorry, but we don't have any " + reply + "s here.");
				}

			} else if (reply.equals("6") && numberOfAnimals != 0) {
				listAnimals(true);

			} else if (reply.equals("2")) {
				randomSpecie = Randomgen.randomString(availableAnimals);
				System.out.println("What would you like to name your new " + randomSpecie + "?");
				adoptName = userInput.nextLine();
				if (adoptName.equals("")) {
					System.out.println("What?");
					continue;
				}
				if (selectAnimal(adoptName) == null) {
					if (randomSpecie.equalsIgnoreCase("pig")) {
						numberOfAnimals++;
						animalArray.add(new Pig(adoptName));
						Animal adopted = selectAnimal(adoptName);
						adopted.viewStats();
						System.out.println("is happy to come to your farm!");
						adopted = null;
					} else if (randomSpecie.equalsIgnoreCase("cat")) {
						numberOfAnimals++;
						animalArray.add(new Cat(adoptName));
						Animal adopted = selectAnimal(adoptName);
						adopted.viewStats();
						System.out.println("is happy to come to your farm!");
						adopted = null;
					}
				} else {
					System.out.println("You already have a " + selectAnimal(adoptName).getSpecie() + " named "
							+ selectAnimal(adoptName).getName() + ".");
				}
			} else if (reply.equals("5") && numberOfAnimals != 0) {
				System.out.println("Which animal would you like to select?");
				String selectAnimalInput = userInput.nextLine();
				if (selectAnimalInput.equals("")) {
					System.out.println("What?");
				} else {

					Animal selectedAnimal = selectAnimal(selectAnimalInput);
					if (selectedAnimal == null) {
						System.out.println("You don't have an animal named " + makeUpperCase(selectAnimalInput) + ".");
					} else {
						selectedAnimal.viewStats();
						System.out.println("What would you like to do with " + selectedAnimal.getName() + "?");
						selectedIndex = "1";
						System.out.println(selectedIndex + ". Rename");
						selectedIndex = "2";
						System.out.println(selectedIndex + ". Make sound");
						selectedIndex = "3";
						System.out.println(selectedIndex + ". Give to a new home");
						selectedIndex = "4";
						if (selectedAnimal.getIsFedToday() == false) {
							System.out.println(selectedIndex + ". Feed");
							feedIndex = selectedIndex;
							selectedIndex = "5";
						}
						if (hasWalkedToday == false) {
							System.out.println(selectedIndex + ". Take out for a walk");
							walkIndex = selectedIndex;
							selectedIndex = "6";
						}
						String selectedInput = userInput.nextLine();
						if (selectedInput.equals("1")) {
							renameAnimal(userInput, selectAnimalInput, selectedAnimal);
						} else if (selectedInput.equals(feedIndex) && selectedAnimal.isFedToday == false) {
							selectedAnimal.feed();
						} else if (selectedInput.equals("2")) {
							selectedAnimal.makeSound();
						} else if (selectedInput.equals(walkIndex) && hasWalkedToday == false) {
							selectedAnimal.walk();
							hasWalkedToday = true;
						} else if (selectedInput.equals("3")) {
							System.out.println("Are you sure you want to give away " + selectedAnimal.getName() + "?");
							String deleteConfirmation = userInput.nextLine();
							if (deleteConfirmation.equalsIgnoreCase("yes")) {
								markAsGone(selectedAnimal.getName());
							}
						}
					}
				}
			} else if (reply.equals("3")) {
				hasWalkedToday = false;
				for (Animal i : animalArray) {
					i.setIsFedToday(false);
					i.incAge();
				}
				System.out.println("You wake up feeling refreshed.");

			} else if (reply.equals("7") && animalsGivenAway != 0) {
				listAnimals(false);
			} else if (reply.equals("4")) {
				System.out.println("Welcome to the farm!");
				System.out.println("Here, you can get new animals and take care of them.");
				System.out.println("After you got your first animal, you can select them");
				System.out.println("and do different things with them.");
				System.out.println("You can feed every animal once a day to improve their strength");
				System.out.println("and take one animal for a walk each day to improve their speed.");
				System.out.println("If you want, you can rename your animals and give them to new homes.");
				System.out.println("Press 1 or 2 to get your first animal.");
				System.out.println("Made by Miika");
			}
		}

	}

	public static void renameAnimal(Scanner userInput, String selectAnimalInput, Animal selectedAnimal) {
		System.out.println("What would you like to rename " + selectedAnimal.getName() + " to?");
		String renameInput = userInput.nextLine();
		selectedAnimal.setName(renameInput);
		String capSelectAnimalInput = makeUpperCase(selectAnimalInput);
		System.out.println(capSelectAnimalInput + " renamed to " + selectedAnimal.getName() + ".");
	}

	private static void createAnimal(Scanner userInput, String specie) {
		System.out.println("What would you like to name your " + specie + "?");
		String inputName = userInput.nextLine();
		if (inputName.equals("")) {
			System.out.println("What?");
			return;
		}
		if (selectAnimal(inputName) == null) {
			System.out.println("Do you want a");
			System.out.println("1. Male");
			System.out.println("2. Female?");
			String inputGender = userInput.nextLine();
			String gender;
			if (inputGender.equals("1")) {
				gender = "male";
			} else if (inputGender.equals("2")) {
				gender = "female";
			} else {
				return;
			}
			numberOfAnimals++;
			if (specie == "pig") {
				animalArray.add(new Pig(inputName, gender));
				System.out.println(makeUpperCase(inputName) + " is happy to come to your farm!");
			} else if (specie == "cat") {
				animalArray.add(new Cat(inputName, gender));
				System.out.println(makeUpperCase(inputName) + " is happy to come to your farm!");
			}
		} else {
			System.out.println("You already have a " + selectAnimal(inputName).getSpecie() + " named "
					+ selectAnimal(inputName).getName() + ".");
		}
	}

	public static String makeUpperCase(String name) {
		String capName = name.substring(0, 1).toUpperCase() + name.substring(1);

		return capName;
	}

	public static Animal selectAnimal(String name) {
		for (Animal i : animalArray) {
			if (i.getName().equalsIgnoreCase(name) && i.getStillHere() == true) {
				return i;
			}
		}
		return null;
	}

	public static void markAsGone(String name) {
		animalsGivenAway++;
		for (Animal i : animalArray) {
			if (i.getName().equalsIgnoreCase(name)) {
				i.setStillHere(false);
				System.out.println(i.getName() + " found a new home!");
			}
		}

	}

	public static void deleteAnimal(String name) {
		Iterator<Animal> iter = animalArray.iterator();
		while (iter.hasNext()) {
			Animal currentAnimal = iter.next();
			if (currentAnimal.getName().equalsIgnoreCase(name)) {
				iter.remove();
			}
		}
	}

	public static void listAnimals(boolean stillHere) {
		if (stillHere == true) {
			System.out.println("Number of animals: " + numberOfAnimals);
		}
		if (stillHere == false) {
			System.out.println("Number of animals: " + animalsGivenAway);
		}
		for (Animal i : animalArray) {
			if (stillHere == i.getStillHere()) {
				i.viewStats();
			}
		}
	}
}
