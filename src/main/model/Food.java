package model;

import java.util.Calendar;

public class Food {


    private String name;
    private Calendar date;


    //EFFECTS: Returns the calories of the food
    public int getCalories() {
        return calories;
    }

    private int calories;

    public Food(String name, Calendar date, int calorieCount) {
        this.name = name;
        this.date = date;
        this.calories = calorieCount;
    }

    //EFFECTS: Returns the name of the food
    public String getName() {
        return name;
    }

    //EFFECTS: Returns the date the food was consumed
    public Calendar getDate() {
        return date;
    }

}
