package model;

import java.util.ArrayList;
import java.util.Date;

public class FoodContainer {
    private ArrayList<Food> foodList;

    public FoodContainer() {
        foodList = new ArrayList<Food>();
    }

    public int getCalorieTotal() {
        int calorieTotal = 0;
        for (Food food : foodList) {
            calorieTotal += food.getCalories();
        }
        return calorieTotal;
    }


    public int getCalorieTotalByDate(Date date) {
        int calorieTotal = 0;
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                calorieTotal += food.getCalories();
            }
        }
        return calorieTotal;
    }
}
