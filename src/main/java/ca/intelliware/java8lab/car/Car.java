package ca.intelliware.java8lab.car;

import java.util.*;

public class Car {

    private Optional<Insurance> insurance = Optional.empty();

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance){
        this.insurance = insurance;
    }
}
