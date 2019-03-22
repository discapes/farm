package code;

public class Pig extends Animal {

	public Pig(String name, String gender) {
		super(name, gender);
		specie = "pig";
	}

	public Pig(String name) {
		super(name);
		specie = "pig";
	}
	
	public void makeSound() {
		System.out.println("Oink Oink!");
	}

}
