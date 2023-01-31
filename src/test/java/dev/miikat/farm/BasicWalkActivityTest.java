package dev.miikat.farm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockingDetails;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	private Console console;

	@BeforeEach
	public void setUp() {
		farm = new Farm(console, "tess");
		animal = new Cat(console, farm, "foo", Gender.MALE);
		farm.addAnimal(animal);
		activity = new BasicWalkActivity(console, animal);
	}

	@AfterEach
	public void tearDown() {
		animal = null;
		farm = null;
		activity = null;
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
