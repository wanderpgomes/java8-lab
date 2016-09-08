package ca.intelliware.java8lab.car;

import java.util.*;

public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }


    public static void main(String ...args){
        OptionalMain optionalMain = new OptionalMain();

        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();

        System.out.println("Insurance: "+optionalMain.getCarInsuranceName(Optional.ofNullable(person)));

        person.setCar(Optional.of(car));
        System.out.println("Insurance: "+optionalMain.getCarInsuranceName(Optional.ofNullable(person)));

        car.setInsurance(Optional.of(insurance));
        System.out.println("Insurance: "+optionalMain.getCarInsuranceName(Optional.ofNullable(person)));

        insurance.setName("Kabir Insurance Inc.");
        System.out.println("Insurance: "+optionalMain.getCarInsuranceName(Optional.ofNullable(person)));

    }
}
