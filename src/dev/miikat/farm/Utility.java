package dev.miikat.farm;

public abstract class Utility {

	public static String capitalize(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}
