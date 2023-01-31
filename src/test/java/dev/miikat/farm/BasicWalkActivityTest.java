package dev.miikat.farm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.inject.util.Modules;

import dev.miikat.farm.activities.BasicWalkActivity;
import dev.miikat.farm.animals.Animal;
import dev.miikat.farm.animals.Animal.Gender;
import dev.miikat.farm.animals.Cat;

@ExtendWith(MockitoExtension.class)
public class BasicWalkActivityTest {
	private Animal animal;
	private Farm farm;
	private BasicWalkActivity activity;

	@Mock
	@Bind
	private Console console;
	@Bind
	private Serializer ser = new XStreamTestSerializer();
	@Inject
	FarmFactory farmFactory;

	@BeforeEach
	public void setUp() {
		Guice.createInjector(Modules.override(new GuiceModule()).with(BoundFieldModule.of(this))).injectMembers(this);

		farm = farmFactory.create("foo");
		animal = new Cat(console, farm, "foo", Gender.MALE);
		farm.addAnimal(animal);
		activity = new BasicWalkActivity(console, animal);
	}

	@Test
	public void shouldBeAvailableInitially() {
		assertTrue(activity.isAvailable());
	}

	@Test
	public void shouldBeUnavailableAfterOne() {
		activity.doActivity();
		assertFalse(activity.isAvailable());
	}

	@Test
	public void shouldBeAvailableAfterRestDay() {
		farm.energy = 10;
		activity.doActivity();
		animal.passDay();
		animal.passDay();
		assertTrue(activity.isAvailable());
	}

	@Test
	public void shouldBeUnavailableWithoutEnergy() {
		farm.energy = 0;
		assertFalse(activity.isAvailable());
	}
}
