package code;

public class Animal {

	String[] genders = { "male", "female" };
	String name;
	String specie;
	String gender;

	boolean stillHere = true;

	boolean isFedToday = false;

	int age = 0;

	int strength = 1;

	int speed = 1;

	public boolean getStillHere() {
		return stillHere;
	}

	public void setStillHere(boolean stillHere) {
		this.stillHere = stillHere;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String capName = Farm.makeUpperCase(name);
		this.name = capName;
	}

	public boolean getIsFedToday() {
		return isFedToday;
	}

	public void setIsFedToday(boolean isFedToday) {
		this.isFedToday = isFedToday;
	}

	public String getSpecie() {
		return specie;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public void incAge() {
		this.age++;
	}

	public double getStrength() {
		return strength;
	}

	public double getSpeed() {
		return speed;
	}

	public Animal(String name, String gender) {
		String capName = Farm.makeUpperCase(name);
		this.name = capName;
		this.gender = gender;
	}

	public void feed() {
		isFedToday = true;
		this.strength++;
		System.out.println("Yum!");
	}

	public void walk() {
		this.speed++;
		System.out.println("Yay!");
	}

	public void viewStats() {
		System.out.print(name);
		System.out.print(" (" + specie + ") ");
		System.out.print("(" + gender + ") ");
		System.out.print("(age: " + age + " days) ");
		System.out.print("(strength: " + strength + ") ");
		System.out.println("(speed: " + speed + ") ");
	}

	public void makeSound() {
	}

	public Animal(String name) {
		String capName = Farm.makeUpperCase(name);
		this.name = capName;
		this.gender = Randomgen.randomString(genders);
		this.speed = (int) (Math.random() * 20 + 1);
		this.strength = (int) (Math.random() * 20 + 1);
		this.age = (int) (Math.random() * 50 + 50);
	}

}
