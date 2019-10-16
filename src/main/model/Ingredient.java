package model;

import java.util.Calendar;

public class Ingredient extends SimpleFood {

    public Ingredient(String name, int calorieCount) {
        super(name, null, calorieCount);
        this.calories = calorieCount;
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

}
