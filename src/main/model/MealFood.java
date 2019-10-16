package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MealFood extends Food {
    //TODO: Fix Calorie Counter
    private FoodContainer ingredients;


    @Override
    public int getCalories() {
        return ingredients.getCalorieTotal();
    }

    @Override
    public int getProteinCountGrams() {
        return ingredients.getProteinTotalGrams();
    }

    @Override
    public int getCarbohydrateCountGrams() {
        return ingredients.getCarbohydrateTotalGrams();
    }

    @Override
    public int getFatCountGrams() {
        return ingredients.getFatsTotalGrams();
    }

    public MealFood(String name, Calendar date) {
        super(name, date);
        ingredients = new FoodContainer();
    }

    public void addIngredient(SimpleFood food) {
        ingredients.addFood(food);
    }

    public String getIngredients() {
        return ingredients.getFoodsEaten();
    }

    public List<Food> getFoodsEatenList() {
        return ingredients.getFoodsList();
    }

    @Override
    public String toString() {
        return super.getName() + " " + this.getCalories();
    }
}
