package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FoodContainer {
    private ArrayList<Food> foodList;

    public FoodContainer() {
        foodList = new ArrayList<>();
    }

    //EFFECTS: returns the number of foods in the food list
    public int getFoodCount() {
        return foodList.size();
    }

    //EFFECTS: returns calorie total of food in container
    public int getCalorieTotal() {
        int calorieTotal = 0;
        for (Food food : foodList) {
            calorieTotal += food.getCalories();
        }
        return calorieTotal;
    }

    //EFFECTS: returns total calories consumed on specific date
    public int getCalorieTotalByDate(Calendar date) {
        int calorieTotal = 0;
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                calorieTotal += food.getCalories();
            }
        }
        return calorieTotal;
    }

    //MODIFIES: this
    //EFFECTS: adds food to food container
    public void addFood(Food food) {
        foodList.add(food);
    }
}
