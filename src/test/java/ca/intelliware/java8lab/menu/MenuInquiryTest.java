package ca.intelliware.java8lab.menu;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class MenuInquiryTest {

    MenuInquiryJ8 fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new MenuInquiryJ8();
    }

    @Test
    public void testGetLowCaloricDishesNames(){

        List<String> expected = Arrays.asList("salmon", "french fries", "pizza", "beef", "pork");

        List<String> result = fixture.getLowCaloricDishesNames();

        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testGetShortMenuCommaSeparated() {

        String result = fixture.getShortMenuCommaSeparated();

        assertEquals("pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon", result);
    }

    @Test
    public void testGroupDishesByType() {

        Map<Dish.Type, List<Dish>> result = fixture.groupDishesByType();

        assertEquals(2, result.get(Dish.Type.FISH).size());
        assertEquals(3, result.get(Dish.Type.MEAT).size());
        assertEquals(4, result.get(Dish.Type.OTHER).size());

        assertEquals(Dish.Type.FISH, result.get(Dish.Type.FISH).get(0).getType());
        assertEquals(Dish.Type.MEAT, result.get(Dish.Type.MEAT).get(0).getType());
        assertEquals(Dish.Type.OTHER, result.get(Dish.Type.OTHER).get(0).getType());
    }

    @Test
    public void testGroupDishesByCaloricLevel() {

        Map<Dish.CaloricLevel, List<Dish>> result = fixture.groupDishesByCaloricLevel();

        assertEquals(4, result.get(Dish.CaloricLevel.DIET).size());
        assertEquals(1, result.get(Dish.CaloricLevel.FAT).size());
        assertEquals(4, result.get(Dish.CaloricLevel.NORMAL).size());

        assertTrue(result.get(Dish.CaloricLevel.DIET).get(0).getCalories() <= 400);
        assertFalse(result.get(Dish.CaloricLevel.NORMAL).get(0).getCalories()  > 700);
        assertTrue(result.get(Dish.CaloricLevel.FAT).get(0).getCalories()  > 700);
    }

    @Test
    public void testGroupDishedByTypeAndCaloricLevel(){

        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> result = fixture.groupDishedByTypeAndCaloricLevel();

        assertEquals(1, result.get(Dish.Type.FISH).get(Dish.CaloricLevel.NORMAL).size());
        assertEquals(1, result.get(Dish.Type.MEAT).get(Dish.CaloricLevel.DIET).size());
        assertNull(result.get(Dish.Type.OTHER).get(Dish.CaloricLevel.FAT));
    }

    @Test
    public void testCountDishesInGroups() {

        Map<Dish.Type, Long> result = fixture.countDishesInGroups();

        assertEquals(new Long(2), result.get(Dish.Type.FISH));
        assertEquals(new Long(3), result.get(Dish.Type.MEAT));
        assertEquals(new Long(4), result.get(Dish.Type.OTHER));
    }

    @Test
    public void testSumCaloriesByType(){

        Map<Dish.Type, Long> result = fixture.sumCaloriesByType();

        assertEquals(new Long(850), result.get(Dish.Type.FISH));
        assertEquals(new Long(1900), result.get(Dish.Type.MEAT));
        assertEquals(new Long(1550), result.get(Dish.Type.OTHER));
    }

    @Test
    public void testCaloricLevelsByType(){

        Map<Dish.Type, Set<Dish.CaloricLevel>> result = fixture.caloricLevelsByType();

        assertEquals(2, result.get(Dish.Type.FISH).size());
        assertEquals(3, result.get(Dish.Type.MEAT).size());
        assertEquals(2, result.get(Dish.Type.OTHER).size());

        assertTrue(result.get(Dish.Type.FISH).contains(Dish.CaloricLevel.DIET));
        assertTrue(result.get(Dish.Type.FISH).contains(Dish.CaloricLevel.NORMAL));

        assertTrue(result.get(Dish.Type.MEAT).contains(Dish.CaloricLevel.NORMAL));
        assertTrue(result.get(Dish.Type.MEAT).contains(Dish.CaloricLevel.DIET));
        assertTrue(result.get(Dish.Type.MEAT).contains(Dish.CaloricLevel.FAT));

        assertTrue(result.get(Dish.Type.OTHER).contains(Dish.CaloricLevel.DIET));
        assertTrue(result.get(Dish.Type.OTHER).contains(Dish.CaloricLevel.NORMAL));
    }


}
