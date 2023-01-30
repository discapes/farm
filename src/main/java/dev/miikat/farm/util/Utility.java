package dev.miikat.farm.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class Utility {

	public static String capitalize(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String getErrorString(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		return exceptionAsString;
	}

}
