package model.food;

import model.TimeStamp;
import model.food.SimpleFood;

public class Ingredient extends SimpleFood {

    public Ingredient(String name, int calorieCount, int quantityEaten) {
        super(name, TimeStamp.getCurrentDate(), calorieCount, quantityEaten);
        this.calories = calorieCount;
        this.numEaten = quantityEaten;
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

}
