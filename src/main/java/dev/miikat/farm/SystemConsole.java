package dev.miikat.farm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dev.miikat.farm.util.Pair;

public class SystemConsole implements Console {
	static Scanner scanner = new Scanner(System.in);
	final static String indent = "    ";

	private int pickOptionIndex(String text, List<String> options) {
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

	private void printMessage(String s) {
		System.out.print(s.replaceAll("\n", '\n' + indent) + "\n\n" + indent);
	}

	private void printQuestion(String s) {
		printMessage(s);
		System.out.print("> ");
	}

	public <T> T pickMenuOption(String text, List<Pair<String, T>> options) {
		int index = pickOptionIndex(text,
				options.stream().map((o) -> (o.a)).toList());

		return options.get(index).b;
	}

	public void showDialogue(String dialogue) {
		clearScreen();
		printMessage(dialogue);
		scanner.nextLine();
	}

	public String askQuestion(String q) {
		clearScreen();
		printQuestion(q);
		String answer;
		do {
			answer = scanner.nextLine();
		} while (answer == null || answer.isEmpty());
		return answer;
	}

	public boolean confirm(String q) {
		return pickMenuOption(q, new ArrayList<Pair<String, Boolean>>() {
			{
				add(new Pair<>("Yes", true));
				add(new Pair<>("No", false));
			}
		});
	}

	private void clearScreen() {
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

	public void switchFromAltScreen() {
		System.out.print("\033[?1049l");
	}

	public void switchToAltScreen() {
		System.out.print("\033[?1049h");
	}
}
