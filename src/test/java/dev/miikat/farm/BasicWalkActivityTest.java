package dev.miikat.farm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.miikat.farm.animals.Animal;
import dev.miikat.farm.animals.Animal.Gender;
import dev.miikat.farm.animals.Cat;

public class BasicWalkActivityTest {
	private Animal animal;
	private static OutputStream writeToInput;
	private static InputStream readFromOutput;
	private static InputStream sysInBackup;
	private static PrintStream sysOutBackup;

	@Before
	public void setUp() {
		animal = new Cat(new Farm("tess"), "foo", Gender.MALE);
	}

	@After
	public void tearDown() {
		animal = null;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		sysInBackup = System.in;
		sysOutBackup = System.out;

		var input = new PipedInputStream();
		System.setIn(input);
		writeToInput = new PipedOutputStream(input);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.setIn(sysInBackup);
		System.setOut(sysOutBackup);
	}

	@Test
	public void walkTest() throws IOException {
		animal.doActivity();
		writeToInput.write("2\n".getBytes());
		writeToInput.flush();
	}
}
