package dev.miikat.farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dev.miikat.farm.util.Pair;

public abstract class Console {
	static Scanner scanner = new Scanner(System.in);

	private static int pickOptionIndex(String text, List<String> options) {
		Optional<Integer> chosenIndex = Optional.empty();
		clearScreen();

		do {
			System.out.println(text);
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + ": " + options.get(i));
			}
			try {
				// we dont use nextInt as it leaves a \n in the input buffer.
				int sel = Integer.parseInt(scanner.nextLine()) - 1;
				if (sel < 0 || sel >= options.size())
					throw new NumberFormatException();

				chosenIndex = Optional.of(sel);
			} catch (NumberFormatException e) {
				clearScreen();
				System.out.println("Invalid! Type a number corresponding to an option.\n");
			}
		} while (chosenIndex.isEmpty());

		return chosenIndex.get();
	}

	public static <T> T pickMenuOption(String text, List<Pair<String, T>> options) {
		int index = pickOptionIndex(text,
				options.stream().map((o) -> (o.a)).toList());

		return options.get(index).b;
	}

	public static void showDialogue(String dialogue) {
		clearScreen();
		System.out.println(dialogue);
		scanner.nextLine();
	}

	public static String askQuestion(String q) {
		clearScreen();
		System.out.println(q);
		String answer;
		do {
			answer = scanner.nextLine();
		} while (answer == null || answer.isEmpty());
		return answer;
	}

	public static boolean confirm(String q) {
		return pickMenuOption(q, new ArrayList<Pair<String, Boolean>>() {
			{
				add(new Pair<>("Yes", true));
				add(new Pair<>("No", false));
			}
		});
	}

	static void clearScreen() {
		System.out.print("\033[H\033[2J");
	}

	static void switchFromAltScreen() {
		System.out.print("\u001B[?1049l");
	}

	static void switchToAltScreen() {
		System.out.print("\u001B[?1049h");
	}

}
