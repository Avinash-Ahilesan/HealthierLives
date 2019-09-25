package model;

import java.util.Date;

public class Food {


    private String name;


    private Date date;

    public int getCalories() {
        return calories;
    }

    private int calories;

    public Food(String name, Date date, int calorieCount) {
        this.name = name;
        this.date = date;
        this.calories = calorieCount;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

}
