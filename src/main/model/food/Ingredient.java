package model.food;

import model.food.SimpleFood;

public class Ingredient extends SimpleFood {

    public Ingredient(String name, int calorieCount, int quantityEaten) {
        super(name, null, calorieCount, quantityEaten);
        this.calories = calorieCount;
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

}
