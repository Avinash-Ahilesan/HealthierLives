package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class FoodContainer implements Serializable {
    private ArrayList<Food> foodList;

    public int getProteinTotalGrams() {
        int proteinCount = 0;
        for (Food food : foodList) {
            proteinCount += food.getProteinCountGrams();
        }
        return proteinCount;
    }

    public int getCarbohydrateTotalGrams() {
        int carbCount = 0;
        for (Food food : foodList) {
            carbCount += food.getCarbohydrateCountGrams();
        }
        return carbCount;
    }

    public int getFatsTotalGrams() {
        int fatCount = 0;
        for (Food food : foodList) {
            fatCount += food.getFatCountGrams();
        }
        return fatCount;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        FoodContainer container = (FoodContainer) obj;
        if (foodList.equals(container.foodList)) {
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: adds food to food container, and adds this to food's food container
    //         creating a bi-directional relationship (1 food: many containers)
    public void addFood(Food food) {
        if (!foodList.contains(food)) {
            foodList.add(food);
            food.addFoodContainer(this);
        }
    }

    public String getFoodsEaten() {
        StringBuilder builder = new StringBuilder();
        for (Food food : foodList) {
            builder.append(food.toString() + "\n");
        }
        return builder.toString();
    }

    public List<Food> getFoodsList() {
        return Collections.unmodifiableList(foodList);
    }
}
