package ca.intelliware.java8lab.menu;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class MenuInquiryTest {

    MenuInquiry fixture;

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

        Map<MenuInquiry.CaloricLevel, List<Dish>> result = fixture.groupDishesByCaloricLevel();

        assertEquals(4, result.get(MenuInquiry.CaloricLevel.DIET).size());
        assertEquals(1, result.get(MenuInquiry.CaloricLevel.FAT).size());
        assertEquals(4, result.get(MenuInquiry.CaloricLevel.NORMAL).size());

        assertTrue(result.get(MenuInquiry.CaloricLevel.DIET).get(0).getCalories() <= 400);
        assertFalse(result.get(MenuInquiry.CaloricLevel.NORMAL).get(0).getCalories()  > 700);
        assertTrue(result.get(MenuInquiry.CaloricLevel.FAT).get(0).getCalories()  > 700);
    }

    @Test
    public void testGroupDishedByTypeAndCaloricLevel(){
        Map<Dish.Type, Map<MenuInquiry.CaloricLevel, List<Dish>>> result = fixture.groupDishedByTypeAndCaloricLevel();

        assertEquals(1, result.get(Dish.Type.FISH).get(MenuInquiry.CaloricLevel.NORMAL).size());
        assertEquals(1, result.get(Dish.Type.MEAT).get(MenuInquiry.CaloricLevel.DIET).size());
        assertNull(result.get(Dish.Type.OTHER).get(MenuInquiry.CaloricLevel.FAT));
    }


}
