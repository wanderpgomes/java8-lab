package ca.intelliware.java8lab.menu;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class MenuInquiryTest {

    private MenuInquiry fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new MenuInquiry();
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

    @Test
    public void testPartitionByVegeterian() {

        Map<Boolean, List<Dish>> result = fixture.partitionByVegeterian();

        assertEquals(5, result.get(FALSE).size());
        assertEquals("pork", result.get(FALSE).get(0).getName());
        assertEquals("beef", result.get(FALSE).get(1).getName());
        assertEquals("chicken", result.get(FALSE).get(2).getName());
        assertEquals("prawns", result.get(FALSE).get(3).getName());
        assertEquals("salmon", result.get(FALSE).get(4).getName());

        assertEquals(4, result.get(TRUE).size());
        assertEquals("french fries", result.get(TRUE).get(0).getName());
        assertEquals("rice", result.get(TRUE).get(1).getName());
        assertEquals("season fruit", result.get(TRUE).get(2).getName());
        assertEquals("pizza", result.get(TRUE).get(3).getName());
    }


    @Test
    public void testVegetarianDishesByType(){

        Map<Boolean, Map<Dish.Type, List<Dish>>> result = fixture.vegetarianDishesByType();

        Map<Dish.Type, List<Dish>> vegetarianDishes = result.get(TRUE);
        assertEquals(4, vegetarianDishes.get(Dish.Type.OTHER).size());
        assertEquals("french fries", vegetarianDishes.get(Dish.Type.OTHER).get(0).getName());
        assertEquals("rice", vegetarianDishes.get(Dish.Type.OTHER).get(1).getName());
        assertEquals("season fruit", vegetarianDishes.get(Dish.Type.OTHER).get(2).getName());
        assertEquals("pizza", vegetarianDishes.get(Dish.Type.OTHER).get(3).getName());

        Map<Dish.Type, List<Dish>> nonVegetarianDishes = result.get(FALSE);
        assertEquals(2, nonVegetarianDishes.get(Dish.Type.FISH).size());
        assertEquals("prawns", nonVegetarianDishes.get(Dish.Type.FISH).get(0).getName());
        assertEquals("salmon", nonVegetarianDishes.get(Dish.Type.FISH).get(1).getName());
        assertEquals(3, nonVegetarianDishes.get(Dish.Type.MEAT).size());
        assertEquals("pork", nonVegetarianDishes.get(Dish.Type.MEAT).get(0).getName());
        assertEquals("beef", nonVegetarianDishes.get(Dish.Type.MEAT).get(1).getName());
        assertEquals("chicken", nonVegetarianDishes.get(Dish.Type.MEAT).get(2).getName());
    }

    @Test
    public void testMostCaloricPartitionedByVegetarian(){

        Object result = fixture.mostCaloricPartitionedByVegetarian();

        assertTrue(result instanceof Map);
        assertEquals("pizza", ((Map<Boolean, Dish>)result).get(TRUE).getName());
        assertEquals("pork", ((Map<Boolean, Dish>)result).get(FALSE).getName());
    }
}
