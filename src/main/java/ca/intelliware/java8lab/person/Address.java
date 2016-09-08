package ca.intelliware.java8lab.person;


import java.util.Optional;

public class Address {

	public City city;


	public Address(City city) {
		this.city = city;
	}

	public Optional<City> getCity() {
		return Optional.ofNullable(city);
	}
}
