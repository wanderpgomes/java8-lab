package ca.intelliware.java8lab.car;

import java.util.*;

public class Person {

    private Optional<Car> car = Optional.empty();;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car){
        this.car = car;
    }
}
