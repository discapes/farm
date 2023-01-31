
package dev.miikat.farm;

import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GuiceModule());
		// FarmGame game = injector.getInstance(FarmGame.class);
		FarmGame game = new FarmGame();
		injector.injectMembers(game);
		game.run();
		System.exit(0);
	}
}
