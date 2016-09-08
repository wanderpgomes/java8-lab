package ca.intelliware.java8lab.apple;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AppleFilterJ8 {
    public static void main(String ...args){

        // Simple example
        Runnable r = () -> System.out.println("Hello!");
        r.run();

        // Filtering with lambdas
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

        List<Apple> greenApples = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples);


        Predicate<Apple> predicate = a -> {
            boolean isBig = a.getWeight() > 100;
            return "green".equals(a.getColor()) && isBig;
        };

        List<Apple> bigGreenApples = filter(inventory, predicate);
        System.out.println(bigGreenApples);
    }

    public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
