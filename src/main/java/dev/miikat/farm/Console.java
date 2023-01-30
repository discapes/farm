package dev.miikat.farm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dev.miikat.farm.util.Pair;

public abstract class Console {
	static Scanner scanner = new Scanner(System.in);
	final static String indent = "    ";

	private static int pickOptionIndex(String text, List<String> options) {
		Optional<Integer> chosenIndex = Optional.empty();
		clearScreen();

		do {
			StringBuilder message = new StringBuilder(text + "\n\n");
			for (int i = 0; i < options.size(); i++) {
				message.append((i + 1) + ": " + options.get(i));
				if (i + 1 < options.size())
					message.append("\n");
			}
			try {
				printQuestion(message.toString());
				// we dont use nextInt as it leaves a \n in the input buffer.
				int sel = Integer.parseInt(scanner.nextLine()) - 1;
				if (sel < 0 || sel >= options.size())
					throw new NumberFormatException();

				chosenIndex = Optional.of(sel);
			} catch (NumberFormatException e) {
				clearScreen();
				printMessage("Invalid! Type a number corresponding to an option.");
			}
		} while (chosenIndex.isEmpty());

		return chosenIndex.get();
	}

	private static void printMessage(String s) {
		System.out.print(s.replaceAll("\n", '\n' + indent) + "\n\n" + indent);
	}

	private static void printQuestion(String s) {
		printMessage(s);
		System.out.print("> ");
	}

	public static <T> T pickMenuOption(String text, List<Pair<String, T>> options) {
		int index = pickOptionIndex(text,
				options.stream().map((o) -> (o.a)).toList());

		return options.get(index).b;
	}

	public static void showDialogue(String dialogue) {
		clearScreen();
		printMessage(dialogue);
		scanner.nextLine();
	}

	public static String askQuestion(String q) {
		clearScreen();
		printQuestion(q);
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
		final String os = System.getProperty("os.name");

		try {
			if (os.contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				System.out.print("\033[H\033[2J");
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		System.out.print('\n' + indent);
	}

	static void switchFromAltScreen() {
		System.out.print("\033[?1049l");
	}

	static void switchToAltScreen() {
		System.out.print("\033[?1049h");
	}

}
