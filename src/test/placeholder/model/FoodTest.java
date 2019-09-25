package placeholder.model;
import model.Food;
import model.FoodContainer;
import org.junit.jupiter.api.*;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    Food testFood;
    Calendar testDate;
    @BeforeEach
    public void setup(){
        testDate = new Calendar.Builder().setDate(2015, 5, 7).build();
        testFood = new Food("apple", testDate, 100);
    }

    @Test
    public void testGetCalories(){
        assertEquals(100, testFood.getCalories());
    }

    @Test
    public void testGetName(){
        assertEquals("apple" ,testFood.getName());

    }

    @Test
    public void testGetDate(){
        assertEquals(testDate, testFood.getDate());
    }
}
