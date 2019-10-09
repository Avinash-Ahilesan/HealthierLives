package model;

import java.util.Calendar;

public class SimpleFood extends Food {
    private int proteins;
    private int carbohydrates;
    private int fats;




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

    public SimpleFood(String name, Calendar date, int calorieCount) {
        super(name, date);
        this.calories = calorieCount;
    }

    public void setMacroCountGrams(int proteinCount, int carboCount, int fatCount) {
        this.proteins = proteinCount;
        this.carbohydrates = carboCount;
        this.fats = fatCount;
    }

}
