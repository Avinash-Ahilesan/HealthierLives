package placeholder.model;

import model.Food;
import model.FoodContainer;
import model.SimpleFood;
import org.junit.jupiter.api.*;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleFoodTest {

    Food testFood;
    Calendar testDate;

    @BeforeEach
    public void setup() {
        testDate = new Calendar.Builder().setDate(2015, 5, 7).build();
        testFood = new SimpleFood("apple", testDate, 100);
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
        assertEquals(testDate, testFood.getDate());
    }
}
