package common;

public class Person {
	private String name;
	private int age;
	
	public Person() {} //기본 생성자

	public Person(String name, int age) { //이거 만들려면 기본생정자 무조건 있어야함
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
