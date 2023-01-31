package dev.miikat.farm;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.inject.util.Modules;

import dev.miikat.farm.animals.Animal.Gender;
import dev.miikat.farm.animals.Cat;

@ExtendWith(MockitoExtension.class)
public class SaveTest {

	@Bind
	@Mock
	PersistentStorage storage;

	@Bind
	@Mock
	Console console;

	@Bind
	Serializer serializer = new XStreamTestSerializer();

	@Inject
	SaveLoader saves;
	@Inject
	FarmFactory farmFactory;

	Injector injector;

	@BeforeEach
	public void setUp() {
		injector = Guice.createInjector(Modules.override(new GuiceModule()).with(BoundFieldModule.of(this)));
		injector.injectMembers(this);
	}

	@Test
	public void shouldSaveAndRestore() throws FileNotFoundException, IOException {
		Farm farm = farmFactory.create("foo");
		farm.addAnimal(new Cat(mock(Console.class), farm, "foo", Gender.MALE));
		reset(console);

		saves.save(farm, "foo");

		// set the mock to return the saved value on load
		var saveCapture = ArgumentCaptor.forClass(String.class);
		verify(storage).write(saveCapture.capture(), any());
		when(storage.read(any())).thenReturn(saveCapture.getValue());

		Farm farm2 = (Farm) saves.load("foo");
		injector.injectMembers(farm2);

		farm.seeAllAnimals();
		var printCapture = ArgumentCaptor.forClass(String.class);
		verify(console).showDialogue(printCapture.capture());
		reset(console);

		// farm2.addAnimal(new Cat(mock(Console.class), farm, "foso", Gender.MALE));
		reset(console);

		farm2.seeAllAnimals();

		verify(console).showDialogue(printCapture.getValue());
		verify(console).showDialogue(printCapture.getValue());
	}
}
