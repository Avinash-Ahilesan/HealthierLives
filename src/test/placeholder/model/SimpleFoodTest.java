package placeholder.model;

import model.TimeStamp;
import model.food.SimpleFood;
import org.junit.jupiter.api.*;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleFoodTest {

    SimpleFood testFood;
    TimeStamp testDate;

    @BeforeEach
    public void setup() {
        testDate = new TimeStamp(2015, 5, 7);
        testFood = new SimpleFood("apple", testDate, 100, 1);
    }

    @Test
    public void testGetCalories() {
        assertEquals(100, testFood.getCalories());
    }

    @Test
    public void testGetName() {
        assertEquals("apple", testFood.getName());

    }

    @Test
    public void testGetDate() {
        System.out.println(testDate);
        System.out.println(testFood.getDate());
        assertEquals(testDate, testFood.getDate());
    }

    @Test
    public void testSetMacroCountAndGetMacro(){
        testFood.setMacroCountGrams(10,20,30);
        assertEquals(10, testFood.getProteinCountGrams());
        assertEquals(20, testFood.getCarbohydrateCountGrams());
        assertEquals(30, testFood.getFatCountGrams());
    }
}
