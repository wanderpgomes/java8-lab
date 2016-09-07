package ca.intelliware.java8lab.person;


import java.util.HashMap;
import java.util.Optional;

public class PersonProcessorJ8 {


	Person person1 = new Person("Kabir", new Address(new City("Calgary")));
	Person person2 = new Person("Anton", new Address(new City("Moscow")));
	Person person3 = new Person("Jean", new Address(new City("Paris")));



	private static void processCity(Optional<City> city){

		System.out.println("Processing city "+city.get().getName()+"...");
	}

	private void processPerson(){

		Map<String, Person> personMap = new Map<>();
		personMap.put("Kabir", person1);


		personMap.find("Kabir")
				.flatMap(Person::getAddress)
				.map(Address::getCity)
				.ifPresent(PersonProcessorJ8::processCity);

		personMap.put("Anton", person2);

		
		personMap.put("Jean", person3);

	}

	public static void main(String[] args){
		PersonProcessorJ8 processor = new PersonProcessorJ8();
		processor.processPerson();

	}



	public class Map<T, U> extends HashMap<T, U> {
		public Optional<U> find(T key) {
			return Optional.ofNullable(super.get(key));
		}
	}
}
