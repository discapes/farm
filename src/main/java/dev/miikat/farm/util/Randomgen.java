package dev.miikat.farm.util;

import java.util.Random;

public abstract class Randomgen {

	public static String randomString(String[] array) {
		int random = new Random().nextInt(array.length);
		return array[random];
	}

	public static boolean passes(int percentChance) {
		int random = new Random().nextInt(100);
		return random < percentChance;
	}

}
