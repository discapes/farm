package dev.miikat.farm;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

import dev.miikat.farm.animals.Cat;

@ExtendWith(MockitoExtension.class)
public class FarmTest {
    @Inject
    private FarmFactory farmFactory;

    @Mock
    @Bind
    private Console console;

    private Farm farm;

    @BeforeEach
    public void setUp() {
        Guice.createInjector(Modules.override(new GuiceModule()).with(BoundFieldModule.of(this))).injectMembers(this);
        farm = farmFactory.create("test");
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
