package ca.intelliware.java8lab.person;


import java.util.Optional;

public class Person {

	private String name;
	private Address address;


    public Person(String name){
		this.name = name;
	}

    public Person(String name, Address address){
		this.name = name;
		this.address = address;
	}

	public Optional<String> getName() {
		return Optional.of(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Optional<Address> getAddress() {
		return Optional.ofNullable(address);
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
