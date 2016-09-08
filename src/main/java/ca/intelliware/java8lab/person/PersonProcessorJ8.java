package ca.intelliware.java8lab.person;


import java.util.HashMap;
import java.util.Optional;

public class PersonProcessorJ8 {


	Person person1 = new Person("Kabir", new Address(new City("Calgary")));
	Person person2 = new Person("Jean");

	Map<String, Person> personMap = new Map<>();


	private void process(){
		personMap.put("Kabir", person1);
		personMap.put("Jean", person2);

		processPerson("Kabir");
		processPerson("Jean");
		processPerson("Wander");
	}

	private void processPerson(String name){
		personMap.find(name)
				.flatMap(Person::getAddress)
				.flatMap(Address::getCity)
				.ifPresent(PersonProcessorJ8::processCity);

		//City city = personMap.get(name).getAddress().getCity();
		//processCity(city);
	}

	private static void processCity(City city){
		System.out.println("Processing city "+ city.getName() +"...");
	}

	public static void main(String... args){
		PersonProcessorJ8 processor = new PersonProcessorJ8();
		processor.process();
	}

	public class Map<T, U> extends HashMap<T, U> {
		public Optional<U> find(T key) {
			return Optional.ofNullable(super.get(key));
		}
	}

	private void processPersonAntiPattern(String name){
		Optional<Person> person = personMap.find(name);
		if (person.isPresent()) {
			Optional<Address> address = person.get().getAddress();
			if (address.isPresent()) {
				Optional<City> city = address.get().getCity();
				if (city.isPresent()) {
					processCity(city.get());
				}
			}
		}
	}
}
