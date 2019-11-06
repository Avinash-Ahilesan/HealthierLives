package model.food;

import model.TimeStamp;

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

    public MealFood(String name, TimeStamp date, int quantityEaten) {
        super(name, date, quantityEaten);
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
