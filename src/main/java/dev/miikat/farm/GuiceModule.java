package dev.miikat.farm;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Console.class).to(SystemConsole.class);
		bind(PersistentStorage.class).to(FileStorage.class);
		bind(Serializer.class).to(XStreamSerializer.class);
		install(new FactoryModuleBuilder()
				.build(FarmFactory.class));
	}
}
