package ca.intelliware.java8lab.menu;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


public class MenuInquiryJ8 {

    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("french fries", true, 530, Dish.Type.OTHER),
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("season fruit", true, 120, Dish.Type.OTHER),
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 400, Dish.Type.FISH),
                    new Dish("salmon", false, 450, Dish.Type.FISH));


    /**
     *  Find the name of all the dishes with less than 400 calories.
     */
    public List<String> getLowCaloricDishesNames(){
        return menu.stream()
                .filter(d -> d.getCalories() > 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    /**
     *  Return the name of all the dishes separated by comma.
     */
    public String getShortMenuCommaSeparated() {
        return menu.stream()
                   .map(Dish::getName)
                   .collect(joining(", "));
    }

    public  Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream()
                   .collect(groupingBy(Dish::getType));
    }

    public Map<Dish.CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                } ));
    }

    public Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy((Dish dish) -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        } )
                )
        );
    }

    public Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    public static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    public static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                Optional::get)));
    }

    public static Map<Dish.Type, Long> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                summingLong(Dish::getCalories)));
    }

    public static Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                        else return Dish.CaloricLevel.FAT; },
                        toSet() )));
    }

    public Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    public Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    public Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}
