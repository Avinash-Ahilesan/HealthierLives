package placeholder.model;

import model.food.Food;
import model.food.FoodContainer;
import model.food.SimpleFood;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import model.TimeStamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodContainerTest {
    FoodContainer foodContainer;
    SimpleFood apple;
    SimpleFood banana;
    TimeStamp date1;
    TimeStamp date2;

    @BeforeEach
    public void setup() {
        foodContainer = new FoodContainer();
        date1 = new TimeStamp(2019, 5, 29);
        date2 = new TimeStamp(2019, 9, 2);
        apple = new SimpleFood("apple", date1, 100, 1);
        banana = new SimpleFood("banana", date2, 50, 1);
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

        Food kiwi = new SimpleFood("kiwi", date2, 25, 1);
        foodContainer.addFood(kiwi);
        assertEquals(75, foodContainer.getCalorieTotalByDate(date2)); //2 item test
    }

    @Test
    public void testAddFood() {
        assertEquals(0, foodContainer.getFoodCount());
        foodContainer.addFood(apple);
        assertEquals(1, foodContainer.getFoodCount());
        assertEquals(1, apple.getContainersIn());
        foodContainer.addFood(banana);
        assertEquals(2, foodContainer.getFoodCount());
    }

    @Test
    public void testGetFoodsEaten() {
        foodContainer.addFood(apple);
        foodContainer.addFood(banana);

        assertEquals("apple 100 29/5/2019\nbanana 50 2/9/2019\n", foodContainer.getFoodsEaten());
    }

    @Test
    public void testGetFoodsEatenList() {
        foodContainer.addFood(apple);
        foodContainer.addFood(banana);
        List<Food> foodList = new ArrayList<>();
        foodList.add(apple);
        foodList.add(banana);
        List<Food> foodsEatenList = foodContainer.getFoodsList();
        assertEquals(foodList ,foodsEatenList);

    }

    @Test
    public void testGetProteinCount(){
        apple.setMacroCountGrams(15,35,23);
        banana.setMacroCountGrams(25,54,53);

        foodContainer.addFood(apple);
        foodContainer.addFood(banana);
        assertEquals(40,foodContainer.getProteinTotalGrams());

    }
    @Test
    public void testGetFatCount(){
        apple.setMacroCountGrams(15,35,23);
        banana.setMacroCountGrams(25,54,53);
        foodContainer.addFood(apple);
        foodContainer.addFood(banana);
        assertEquals(76,foodContainer.getFatsTotalGrams());

    }
    @Test
    public void testGetCarbohydrateCount(){
        apple.setMacroCountGrams(15,35,23);
        banana.setMacroCountGrams(25,54,53);

        foodContainer.addFood(apple);
        foodContainer.addFood(banana);
        assertEquals(89,foodContainer.getCarbohydrateTotalGrams());
    }

    @Test
    public void testEqualsTrue(){
        foodContainer.addFood(apple);
        foodContainer.addFood(banana);

        FoodContainer equalContainer = new FoodContainer();
        equalContainer.addFood(apple);
        equalContainer.addFood(banana);

        assertTrue(foodContainer.equals(equalContainer));
    }

    @Test
    public void testEqualsFalse(){
        foodContainer.addFood(apple);
        foodContainer.addFood(banana);
        FoodContainer equalContainer = new FoodContainer();
        equalContainer.addFood(banana);

        assertFalse(foodContainer.equals(equalContainer));
    }

    @Test
    public void testGetFoodsEatenByDate() {
        TimeStamp ts = new TimeStamp(2019, 2, 5);
        Food food = new SimpleFood("apple", ts, 100, 1);
        foodContainer.addFood(food);
        List<Food> foodListEatenOnDate = foodContainer.getFoodsOnDate(ts);

        assertEquals(foodListEatenOnDate.get(0), food);
    }
}
