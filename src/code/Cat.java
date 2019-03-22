package code;

public class Cat extends Animal {

	public Cat(String name, String gender) {
		super(name, gender);
		specie = "cat";
	}
	public Cat(String name) {
		super(name);
		specie = "cat";
	}

	public void makeSound() {
		System.out.println("Meow!");
	}
}
