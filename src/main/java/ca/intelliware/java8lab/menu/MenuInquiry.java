package ca.intelliware.java8lab.menu;


import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toSet;

public class MenuInquiry {

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

    public enum CaloricLevel { DIET, NORMAL, FAT };

    /**
     *  Find the name of all the dishes with less than 400 calories.
     */
    public List<String> getLowCaloricDishesNames(){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: menu){
            if(d.getCalories() > 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    /**
     *  Return the name of all the dishes separated by comma.
     */
    public String getShortMenuCommaSeparated() {
        String shortMenu = "";
        for(Dish dish : menu){
            shortMenu +=  dish.getName() + ", ";
        }
        int lastCommaIndex = shortMenu.lastIndexOf(", ");

        return shortMenu.substring(0, lastCommaIndex);
    }

    public  Map<Dish.Type, List<Dish>> groupDishesByType() {

        Map<Dish.Type, List<Dish>> dishesByType =  new HashMap();

        for (Dish dish: menu){
            if (!dishesByType.containsKey(dish.getType())){
                dishesByType.put(dish.getType(), new ArrayList());
            }
            dishesByType.get(dish.getType()).add(dish);
        }

        return dishesByType;
    }

    public Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel=  new HashMap();

        List<Dish> dietDishes = new ArrayList();
        List<Dish> normalDishes = new ArrayList();
        List<Dish> fatDishes = new ArrayList();

        for (Dish dish: menu){
            if (dish.getCalories() <= 400) {
                dietDishes.add(dish);
            } else if (dish.getCalories() <= 700) {
                normalDishes.add(dish);
            } else fatDishes.add(dish);
        }

        if (!dietDishes.isEmpty()) {
            dishesByCaloricLevel.put(CaloricLevel.DIET, dietDishes);
        }
        if (!normalDishes.isEmpty()) {
            dishesByCaloricLevel.put(CaloricLevel.NORMAL, normalDishes);
        }
        if (!fatDishes.isEmpty()) {
            dishesByCaloricLevel.put(CaloricLevel.FAT, fatDishes);
        }

        return dishesByCaloricLevel;
    }

    public Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = new HashMap();

        Map<Dish.Type, List<Dish>> dishesByType =  new HashMap();

        for (Dish dish: menu){
            if (!dishesByType.containsKey(dish.getType())){
                dishesByType.put(dish.getType(), new ArrayList());
            }
            dishesByType.get(dish.getType()).add(dish);
        }

        for(Dish.Type type : dishesByType.keySet()){
            List<Dish> dishes = dishesByType.get(type);

            Map<CaloricLevel, List<Dish>> dishesByCaloricLevel=  new HashMap();

            List<Dish> dietDishes = new ArrayList();
            List<Dish> normalDishes = new ArrayList();
            List<Dish> fatDishes = new ArrayList();

            for (Dish d: dishes){
                if (d.getCalories() <= 400) {
                    dietDishes.add(d);
                } else if (d.getCalories() <= 700) {
                    normalDishes.add(d);
                } else fatDishes.add(d);
            }

            if (!dietDishes.isEmpty()) {
                dishesByCaloricLevel.put(CaloricLevel.DIET, dietDishes);
            }
            if (!normalDishes.isEmpty()) {
                dishesByCaloricLevel.put(CaloricLevel.NORMAL, normalDishes);
            }
            if (!fatDishes.isEmpty()) {
                dishesByCaloricLevel.put(CaloricLevel.FAT, fatDishes);
            }

            dishesByTypeAndCaloricLevel.put(type, dishesByCaloricLevel);

        }
        return dishesByTypeAndCaloricLevel;
    }

    public Map<Dish.Type, Long> countDishesInGroups() {

        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    public static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    public static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
    }

    public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
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
