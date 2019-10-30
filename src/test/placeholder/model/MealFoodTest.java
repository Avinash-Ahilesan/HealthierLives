package placeholder.model;

import model.Ingredient;
import model.MealFood;
import model.SimpleFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealFoodTest {

    MealFood testMealFood;
    Calendar date1;
    SimpleFood testFood;
    @BeforeEach
    public void setup(){
        date1 =  new Calendar.Builder().setDate(2019, 5, 1).build();
        testMealFood = new MealFood("test meal", date1, 1);
        testFood = new SimpleFood("Peaches", date1, 20, 1);
    }

    @Test
    public void testAddFood(){
        assertEquals(0, testMealFood.getFoodsEatenList().size());
        testMealFood.addIngredient(testFood);
        assertEquals(1, testMealFood.getFoodsEatenList().size());
        assertEquals(testFood, testMealFood.getFoodsEatenList().get(0));
    }

    @Test
    public void testGetCalories(){
        testMealFood.addIngredient(testFood);
        assertEquals(20, testMealFood.getCalories());
    }

    @Test
    public void testAddMultipleFoodsGetCalories(){

        testMealFood.addIngredient(testFood);
        Ingredient ingredient = new Ingredient("testIngredeint", 10, 1);
        testMealFood.addIngredient(ingredient);
        assertEquals(30, testMealFood.getCalories());

    }

    @Test
    public void testGetProteinGrams(){
        testFood.setMacroCountGrams(10, 20, 30);
        testMealFood.addIngredient(testFood);
        assertEquals(10, testMealFood.getProteinCountGrams());
    }

    @Test
    public void testGetFatsGrams(){

        testFood.setMacroCountGrams(10, 20, 30);
        testMealFood.addIngredient(testFood);
        assertEquals(30, testMealFood.getFatCountGrams());

    } @Test
    public void testGetCarbohydrateGrams(){

        testFood.setMacroCountGrams(10, 20, 30);
        testMealFood.addIngredient(testFood);
        assertEquals(20, testMealFood.getCarbohydrateCountGrams());

    }

}
