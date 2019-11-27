package model.food;

import model.TimeStamp;

import java.util.Calendar;

public class SimpleFood extends Food {
    private int proteins;
    private int carbohydrates;
    private int fats;


    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public int getProteinCountGrams() {
        return proteins;
    }

    @Override
    public int getCarbohydrateCountGrams() {
        return carbohydrates;
    }

    @Override
    public int getFatCountGrams() {
        return fats;
    }

    public SimpleFood(String name, TimeStamp date, int calorieCount, int quantityEaten) {
        super(name, date, quantityEaten);
        this.calories = calorieCount;
        this.numEaten = quantityEaten;
    }

    //REQUIRES: above zero value for proteinCount, carbCount, fatCount
    //EFFECTS: sets the amount of proteins, carbs, and fats for this food
    public void setMacroCountGrams(int proteinCount, int carboCount, int fatCount) {
        this.proteins = proteinCount;
        this.carbohydrates = carboCount;
        this.fats = fatCount;
    }

}
