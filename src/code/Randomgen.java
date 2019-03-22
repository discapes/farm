package code;

import java.util.Random;

public class Randomgen {

	public static String randomString(String[] array) {
		int random = new Random().nextInt(array.length);
		return array[random];
	}

}
