package placeholder.model;

import model.Food;
import model.FoodContainer;
import model.SimpleFood;
import org.junit.jupiter.api.*;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class FoodContainerTest {
    FoodContainer foodContainer;
    Food apple;
    Food banana;
    Calendar date1;
    Calendar date2;

    @BeforeEach
    public void setup() {
        foodContainer = new FoodContainer();
        date1 = new Calendar.Builder().setDate(2019, 5, 29).build();
        date2 = new Calendar.Builder().setDate(2019, 9, 2).build();
        apple = new SimpleFood("apple", date1, 100);
        banana = new SimpleFood("banana", date2, 50);
    }

    @Test
    public void testGetFoodCount() {
        assertEquals(0, foodContainer.getFoodCount());
        foodContainer.addFood(apple);
        assertEquals(1, foodContainer.getFoodCount());
    }

    @Test
    public void testGetCalorieCount() {
        assertEquals(0, foodContainer.getCalorieTotal()); // No items added yet
        foodContainer.addFood(apple);
        assertEquals(100, foodContainer.getCalorieTotal()); // 1 item added
        foodContainer.addFood(banana);
        assertEquals(150, foodContainer.getCalorieTotal()); // check if 2 items add correctly

    }

    @Test
    public void testGetCalorieCountByDate() {
        assertEquals(0, foodContainer.getCalorieTotalByDate(date1));
        assertEquals(0, foodContainer.getCalorieTotalByDate(date2));
        foodContainer.addFood(apple);
        foodContainer.addFood(banana);
        assertEquals(100, foodContainer.getCalorieTotalByDate(date1)); //1 item test

        Food kiwi = new SimpleFood("kiwi", date2, 25);
        foodContainer.addFood(kiwi);
        assertEquals(75, foodContainer.getCalorieTotalByDate(date2)); //2 item test
    }

    @Test
    public void testAddFood() {
        assertEquals(0, foodContainer.getFoodCount());
        foodContainer.addFood(apple);
        assertEquals(1, foodContainer.getFoodCount());
        foodContainer.addFood(banana);
        assertEquals(2, foodContainer.getFoodCount());
    }
}
