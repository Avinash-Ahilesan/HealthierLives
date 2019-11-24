package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import model.Person;
import model.TimeStamp;
import model.food.Food;
import model.food.SimpleFood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainUIController {
    private ObservableList<Food> foodEatenOnDate;
    private Person person;
    public Button btnAddFood;
    public Label lblName;
    public Label lblAge;
    public Label lblTargetCalories;
    public Label lblWeight;

    public DatePicker foodDateForDisplay;
    public TimeStamp currentTimeSelected = null;
    public TextField txtSimpleFoodName;
    public TextField txtSimpleFoodCalories;
    public TextField txtSimpleFoodQuantity;
    public ListView foodEatenList;

    public void initialize() {
    }

    public void onAddFoodClicked() {
        String foodName = txtSimpleFoodName.toString();
        TimeStamp ts = new TimeStamp(2019, 2, 1);
        int calories = Integer.parseInt(txtSimpleFoodCalories.getText());
        int quantity = Integer.parseInt(txtSimpleFoodQuantity.getText());

        SimpleFood food = new SimpleFood(foodName, ts, calories, quantity);
        person.addFood(food);
        updateListViewFoodAdded(food);
    }

    public void setPerson(Person p) {
        this.person = p;
        lblName.setText(lblName.getText() + " " + person.getName());
        lblAge.setText(lblAge.getText() + " " + person.getAge());
        lblTargetCalories.setText(lblTargetCalories.getText() + " " + person.getTargetCalories());
    }

    public void onDateChanged() {
        updateCurrentTimeSelected();
        updateListViewDateChanged();
    }

    public void updateCurrentTimeSelected() {
        LocalDate value = foodDateForDisplay.getValue();
        int year = value.getYear();
        int month = value.getMonthValue();
        int day = value.getDayOfMonth();
        currentTimeSelected = new TimeStamp(year, month, day);
    }

    public void updateListViewDateChanged() {
        LocalDate value = foodDateForDisplay.getValue();
        int year = value.getYear();
        int month = value.getMonthValue();
        int day = value.getDayOfMonth();
        TimeStamp timeStamp = new TimeStamp(year, month, day);
        List<Food> foodsEaten = person.getFoodsEaten(timeStamp);
        for (Food food: foodsEaten) {
            foodEatenOnDate.add(food);
        }

        foodEatenList.setItems(foodEatenOnDate);
    }

    public void updateListViewFoodAdded(Food food) {
        if (food.getDate().equals(currentTimeSelected)) {
            foodEatenOnDate.add(food);
        }
    }

}
