package dev.miikat.farm;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.miikat.farm.animals.Cat;

@ExtendWith(MockitoExtension.class)
public class FarmTest {
    private Farm farm;
    @Mock
    private Console console;

    @BeforeEach
    public void setUp() {
        farm = new Farm(console, "test");
    }

    @AfterEach
    public void tearDown() {
        farm = null;
    }

    @Test
    public void shouldDisplayAnimalCount() {
        farm.seeAllAnimals();
        verify(console).showDialogue(argThat(s -> s.contains("Number of animals: 0")));
        farm.addAnimal(mock(Cat.class));
        farm.seeAllAnimals();
        verify(console).showDialogue(argThat(s -> s.contains("Number of animals: 1")));
    }
}
