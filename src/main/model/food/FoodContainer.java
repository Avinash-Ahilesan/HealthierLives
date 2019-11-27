package model.food;

import javafx.collections.ObservableList;
import model.TimeStamp;

import java.io.Serializable;
import java.util.*;


public class FoodContainer implements Serializable {
    private ArrayList<Food> foodList;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returmns total protein in all foods in foodList
    public int getProteinTotalGrams() {
        int proteinCount = 0;
        for (Food food : foodList) {
            proteinCount += food.getProteinCountGrams();
        }
        return proteinCount;
    }

    //EFFECTS: returns total carbohydrates in all foods in foodlist
    public int getCarbohydrateTotalGrams() {
        int carbCount = 0;
        for (Food food : foodList) {
            carbCount += food.getCarbohydrateCountGrams();
        }
        return carbCount;
    }

    //EFFECTS: returns total fats in all foods in foodlist
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
            calorieTotal += food.getCalories() * food.getNumEaten();
        }
        return calorieTotal;
    }

    //EFFECTS: returns total calories consumed on specific date
    public int getCalorieTotalByDate(TimeStamp date) {
        int calorieTotal = 0;
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                calorieTotal += food.getCalories() * food.getNumEaten();
            }
        }
        return calorieTotal;
    }


    //EFFECTS: returns true if two foodcontainers are same, false otherwise
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

    //EFFECTS: returns a string form of all foods eaten
    public String getFoodsEaten() {
        StringBuilder builder = new StringBuilder();
        for (Food food : foodList) {
            builder.append(food.toString() + "\n");
        }
        return builder.toString();
    }

    //EFFECTS: returns an unmodifiable list of all foods eaten
    public List<Food> getFoodsList() {
        return Collections.unmodifiableList(foodList);
    }

    //EFFECTS: returns a list of all foods eaten on a certain date
    public List<Food> getFoodsOnDate(TimeStamp timeStamp) {
        List<Food> foodListDate = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getDate().equals(timeStamp)) {
                foodListDate.add(food);
            }
        }
        return foodListDate;
    }
}
