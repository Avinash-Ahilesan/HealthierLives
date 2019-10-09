package model;

import java.util.Calendar;

public class MealFood extends Food {
    //TODO: Fix Calorie Counter
    private FoodContainer ingredients;



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
        this.calories += food.calories;
    }
}
