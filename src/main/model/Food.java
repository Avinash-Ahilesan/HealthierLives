package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Food implements Serializable {

    List<FoodContainer> foodsUsedInList;
    private String name;
    private Calendar date;

    public int getNumEaten() {
        return numEaten;
    }

    public void setNumEaten(int numEaten) {
        this.numEaten = numEaten;
    }

    private int numEaten;

    //EFFECTS: Returns the calories of the food
    public abstract int getCalories();

    public abstract int getProteinCountGrams();

    public abstract int getCarbohydrateCountGrams();

    public abstract int getFatCountGrams();

    public void addFoodContainer(FoodContainer foodContainer) {
        //bidirectional relationship
        if (!foodsUsedInList.contains(foodContainer)) {
            foodsUsedInList.add(foodContainer);
            foodContainer.addFood(this);
        }
    }

    protected int calories;

    public Food(String name, Calendar date, int quantityEaten) {
        this.name = name;
        this.date = date;
        this.numEaten = quantityEaten;
        foodsUsedInList = new ArrayList<>();
    }

    //EFFECTS: Returns the name of the food
    public String getName() {
        return name;
    }

    //EFFECTS: Returns the date the food was consumed
    public Calendar getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Food food = (Food) obj;
        if (calories == this.calories && date.equals(food.date) && name.equals(food.name)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return name + " " + calories;
    }
}
