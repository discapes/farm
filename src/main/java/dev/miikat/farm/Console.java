package dev.miikat.farm;

import java.util.List;

import dev.miikat.farm.util.Pair;

public interface Console {

	public <T> T pickMenuOption(String text, List<Pair<String, T>> options);

	public void showDialogue(String dialogue);

	public String askQuestion(String q);

	public boolean confirm(String q);

	public void switchFromAltScreen();

	public void switchToAltScreen();
}
